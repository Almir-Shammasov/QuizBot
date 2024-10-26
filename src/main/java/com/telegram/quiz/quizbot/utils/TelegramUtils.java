package com.telegram.quiz.quizbot.utils;

import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;

import java.util.List;

public class TelegramUtils {
    public static SendPoll createQuiz(Long chatId, String title, List<String> answers, int correctIndex) {
        String pollType = "quiz";
        SendPoll sendPoll = new SendPoll();
        sendPoll.setChatId(chatId);
        sendPoll.setQuestion(title);
        sendPoll.setOptions(answers);
        sendPoll.setCorrectOptionId(correctIndex);
        sendPoll.setType(pollType);

        return sendPoll;
    }
}
