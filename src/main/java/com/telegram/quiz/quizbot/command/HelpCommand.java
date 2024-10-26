package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.entity.TelegramUser;
import com.telegram.quiz.quizbot.service.DbUserService;
import com.telegram.quiz.quizbot.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class HelpCommand implements Command {
    private final TelegramService telegramService;
    private final DbUserService dbUserService;

    @Override
    public String getCommandName() {
        return "/help";
    }

    @Override
    public void execute(Message message) {
        telegramService.sendTextMessage(
                "Справочная информация: " +
                        "Этот бот создан в рамках изучения библиотеки TelegramLongPollingBot. " +
                        "Здесь будет предоставлен квиз. Что бы сыграть в квиз нужно отправить команду /quiz. " +
                        "Что бы посмотреть все команды наберите /commandlist", message.getChatId());

        dbUserService.writeUser(new TelegramUser(
                message.getChat().getFirstName(),
                message.getChat().getUserName(),
                message.getChat().getId(),
                message.getText(),
                LocalDateTime.now().toString()));
    }
}
