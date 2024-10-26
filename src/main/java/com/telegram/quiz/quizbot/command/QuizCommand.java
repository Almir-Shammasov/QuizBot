package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.entity.QuizObject;
import com.telegram.quiz.quizbot.service.DbQuizService;
import com.telegram.quiz.quizbot.service.TelegramService;
import com.telegram.quiz.quizbot.utils.TelegramUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizCommand implements Command {
    private final TelegramService telegramService;
    private final DbQuizService dbQuizService;
    private final List<Integer> questions = new ArrayList<>();


    public void fillQuestions() {
        int quizSize = dbQuizService.getAllQuestions().size();
        for (int i = 1; i <= quizSize; i++) {
            questions.add(i);
        }
        Collections.shuffle(questions);
    }

    @Override
    public String getCommandName() {
        return "/quiz";
    }

    @Override
    @Transactional
    public void execute(Message message) {
        Long chatId = message.getChatId();
        if (questions.isEmpty()) {
            fillQuestions();
        }
        int randomQuestion = questions.removeFirst();
        QuizObject randomQuiz = dbQuizService.getById(randomQuestion);
        SendPoll quiz = TelegramUtils.createQuiz(chatId, randomQuiz.getTitle(), randomQuiz.getAnswers(), randomQuiz.getCorrectindex());
        telegramService.sendPoll(quiz);
    }
}
