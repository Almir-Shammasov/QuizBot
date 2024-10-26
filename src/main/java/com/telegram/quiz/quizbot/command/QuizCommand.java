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

import java.util.List;

@Component
@RequiredArgsConstructor
public class QuizCommand implements Command {
    private final TelegramService telegramService;
    private final DbQuizService dbQuizService;
    private List<Integer> questions;

    @Override
    public String getCommandName() {
        return "/quiz";
    }

    @Override
    @Transactional
    public void execute(Message message) {
        Long chatId = message.getChatId();
        int quizSize = dbQuizService.getAllQuestions().size() - 1;
        int randomQuestion = (int) (Math.random() * ++quizSize) + 1;
        QuizObject randomQuiz = dbQuizService.getById(randomQuestion);
        SendPoll quiz = TelegramUtils.createQuiz(chatId, randomQuiz.getTitle(), randomQuiz.getAnswers(), randomQuiz.getCorrectindex());
        telegramService.sendPoll(quiz);
    }
}
