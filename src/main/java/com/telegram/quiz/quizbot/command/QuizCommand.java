package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.entity.QuizObject;
import com.telegram.quiz.quizbot.service.impl.QuizServiceImpl;
import com.telegram.quiz.quizbot.service.impl.TelegramService;
import com.telegram.quiz.quizbot.utils.TelegramUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.*;

@Component
@RequiredArgsConstructor
public class QuizCommand implements Command {
    private final TelegramService telegramService;
    private final QuizServiceImpl quizService;
    private final Map<Long, List<Integer>> mapOfQuestions = new HashMap<>();

    @Override
    public String getCommandName() {
        return "/quiz";
    }

    @Override
    @Transactional
    public void execute(Message message) {
        Long chatId = message.getChatId();

        if(!mapOfQuestions.containsKey(chatId)) {
            int questions = quizService.getCount();
            List<Integer> listQuestions = new ArrayList<>();
            for (int i = 1; i <= questions; i++) {
                listQuestions.add(i);
            }
            Collections.shuffle(listQuestions);
            mapOfQuestions.put(chatId, listQuestions);
        } else if (mapOfQuestions.containsKey(chatId) && mapOfQuestions.get(chatId).isEmpty()) {
            int questions = quizService.getCount();
            for (int i = 1; i <= questions; i++) {
                mapOfQuestions.get(chatId).add(i);
            }
            Collections.shuffle(mapOfQuestions.get(chatId));
        }

        int randomQuestion = mapOfQuestions.get(chatId).removeFirst();
        QuizObject randomQuiz = quizService.getById(randomQuestion);
        SendPoll quiz = TelegramUtils.createQuiz(chatId, randomQuiz.getTitle(), randomQuiz.getAnswers(), randomQuiz.getCorrectindex());
        telegramService.sendPoll(quiz);
    }

    //TODO Преобразовывать ответы в лист тут перед предачей в метод createQuiz
}
