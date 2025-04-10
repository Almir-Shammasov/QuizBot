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

        List<String> optionals = new ArrayList<>();
        int randomQuestion = mapOfQuestions.get(chatId).removeFirst();
        QuizObject randomQuiz = quizService.getById(randomQuestion);
        if(randomQuiz.getOptionOne() != null) {
            optionals.add(randomQuiz.getOptionOne());
        }
        if(randomQuiz.getOptionTwo() != null) {
            optionals.add(randomQuiz.getOptionTwo());
        }
        if(randomQuiz.getOptionThree() != null) {
            optionals.add(randomQuiz.getOptionThree());
        }
        if(randomQuiz.getOptionFour() != null) {
            optionals.add(randomQuiz.getOptionFour());
        }
        if(randomQuiz.getOptionFive() != null) {
            optionals.add(randomQuiz.getOptionFive());
        }
        if(randomQuiz.getOptionSix() != null) {
            optionals.add(randomQuiz.getOptionSix());
        }

        int trueOptional = randomQuiz.getCorrectOptional();
        Map<String, Boolean> mapOptionals = new HashMap<>();
        for(int i = 0; i < optionals.size(); i++) {
            if (i == trueOptional) {
                mapOptionals.put(optionals.get(i), true);
            } else {
                mapOptionals.put(optionals.get(i), false);
            }
        }
        Collections.shuffle(optionals);
        for(int i = 0; i < optionals.size(); i++) {
            if(mapOptionals.containsKey(optionals.get(i)) && mapOptionals.get(optionals.get(i))) {
                trueOptional = i;
            }
        }


        SendPoll quiz = TelegramUtils.createQuiz(chatId, randomQuiz.getQuestionText(), optionals, trueOptional);
        telegramService.sendPoll(quiz);
    }
}