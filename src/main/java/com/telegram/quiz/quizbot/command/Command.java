package com.telegram.quiz.quizbot.command;

import org.telegram.telegrambots.meta.api.objects.Message;

/**
 * Represents a bot command that can be executed based on a Telegram message.
 */
public interface Command {
    /**
     * Returns the name of the command (e.g., "/help").
     * @return command name
     */
    String getCommandName();
    /**
     * Executes the command logic based on the provided message.
     * @param message the Telegram message triggering the command
     */
    void execute(Message message);
}
