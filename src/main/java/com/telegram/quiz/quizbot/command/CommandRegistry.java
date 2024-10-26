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
        log.info("Команда " + command.getCommandName() + " зарегистрирована");
    }
    public void executeCommand(String commandName, Message message) {
        Command command = commandMap.get(commandName);
        if(command != null) {
            command.execute(message);
            log.info("Выполнилась команда: " + commandName);
        } else {
            log.warn("Command not found: " + commandName);
        }
    }

}
