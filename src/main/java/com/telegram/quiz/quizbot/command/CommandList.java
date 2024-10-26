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
                List of bot commands:
                Type /help to show background information
                Type /quiz to play quiz""";
        telegramService.sendTextMessage(listCommands, message.getChatId());
    }
}
