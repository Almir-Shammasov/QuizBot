package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.service.impl.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
@RequiredArgsConstructor
public class HelpCommand implements Command {
    private final TelegramService telegramService;

    @Override
    public String getCommandName() {
        return "/help";
    }

    @Override
    public void execute(Message message) {
        telegramService.sendTextMessage("""
                Справочная информация: 
                Этот бот создан для развлекательных целей :3
                В нем вы можете сыграть в квиз выполнив команду /quiz
                Или получить рандомный мемасик выполнив команду /getmemes
                Список всех команд: /commandlist""", message.getChatId());
    }
}
