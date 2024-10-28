package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.service.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class CommandList implements Command {
    private final TelegramService telegramService;

    @Override
    public String getCommandName() {
        return "/commandlist";
    }

    @Override
    public void execute(Message message) {
        String listCommands = """
                Список всех команд бота:
                Тип: /help справочная информация
                Тип: /quiz сыграть в квиз(получить случайный вопрос из квиза)
                Тип: /getmemes получить рандомный мемчик """;
        telegramService.sendTextMessage(listCommands, message.getChatId());
    }
}
