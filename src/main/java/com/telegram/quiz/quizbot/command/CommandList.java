package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.service.impl.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class CommandList implements Command {
    private static final String COMMAND_LIST_TEXT = """
            Список всех команд бота:
            /help - справочная информация
            /quiz - сыграть в квиз (случайный вопрос)
            /getmemes - получить случайный мем""";

    private final TelegramService telegramService;

    @Override
    public String getCommandName() {
        return "/commandlist";
    }

    @Override
    public void execute(Message message) {
        telegramService.sendTextMessage(COMMAND_LIST_TEXT, message.getChatId());
    }
}
