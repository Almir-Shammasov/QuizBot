package com.telegram.quiz.quizbot.command;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class CommandRegistry {
    private final Map<String, Command> commandMap = new HashMap<>();

    public void registerCommand(Command command) {
        commandMap.put(command.getCommandName(), command);
        log.info("Command registered: {}", command.getCommandName());
    }

    public Command getCommand(String commandName) {
        return commandMap.get(commandName);
    }

    public void executeCommand(String commandName, Message message) {
        Command command = getCommand(commandName);
        if (command != null) {
            command.execute(message);
            log.info("Command executed: {}", commandName);
        } else {
            log.warn("Command not found: {}", commandName);
        }
    }
}
