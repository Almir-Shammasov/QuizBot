package com.telegram.quiz.quizbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;

public interface Command {
    String getCommandName();
    void execute(Message message);
}
