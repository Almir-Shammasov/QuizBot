package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.command.CommandRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
@Slf4j
@RequiredArgsConstructor
public class TelegramService extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String token;
    @Value("${bot.username}")
    private String botName;
    @Value("${bot.parsemode}")
    private String parseMode;
    private final CommandRegistry commandRegistry;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String textMessage = update.getMessage().getText();
            log.info("Вы отправили сообщение: " + update.getMessage().getText());
            commandRegistry.executeCommand(textMessage, update.getMessage());
        }
    }

    public void sendTextMessage(String text, Long chatId) {
        try {
            execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(text)
                    .parseMode(parseMode)
                    .build());
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    public void sendPoll(SendPoll poll) {
        try {
            execute(poll);
        } catch (TelegramApiException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return token;
    }
}
