package com.telegram.quiz.quizbot.service.impl;

import com.telegram.quiz.quizbot.command.CommandRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import org.telegram.telegrambots.meta.api.methods.polls.SendPoll;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;


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
    private final ImageServiceImpl imageService;

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String textMessage = update.getMessage().getText();
            if(textMessage.startsWith("/")) {
                commandRegistry.executeCommand(textMessage, update.getMessage());
            } else {
                sendTextMessage("Я не могу отвечать вам на сообщения, но я могу выполнять команды на которые меня запрограммировали, " +
                        "список команд вы можете посмотреть нажав сюда /commandlist", update.getMessage().getChatId());
                log.info("Получено сообщение: " + update.getMessage().getText() + "\n" +
                        " От пользователя: " + update.getMessage().getChat().getUserName() + " с Id: " + update.getMessage().getChat().getId());
            }
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            sendTextMessage("О, вы прислали картинку, спасибо. Но я не могу распознавать изображения\n" +
                    "Надеюсь там что то смешное!)", update.getMessage().getChatId());
        } else if (update.hasMessage() && update.getMessage().hasVideo()) {
            sendTextMessage("Кажется вы прислали видео. Я не могу распознавать видеофайлы\n" +
                    "Но надеюсь там нет ничего запрещенного! ;)", update.getMessage().getChatId());
        } else if (update.hasMessage() && update.getMessage().hasVoice()) {
            sendTextMessage("О нееет, только не голосовухи!", update.getMessage().getChatId());
        } else if (update.hasMessage() && update.getMessage().hasSticker()) {
            sendTextMessage("Не могу распознавать стикеры", update.getMessage().getChatId());
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

    public void saveImageFromDirectiory(String path) {
        try {
            imageService.saveImage(path);
        } catch (IOException e) {
            log.error("Error occurred: " + e.getMessage());
        }
    }

    public void sendPhoto(Long chatId, InputFile inputFile) {
        SendPhoto photo = SendPhoto.builder().chatId(chatId).photo(inputFile).build();
        try {
            execute(photo);
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
