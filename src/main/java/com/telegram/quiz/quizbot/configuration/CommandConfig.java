package com.telegram.quiz.quizbot.configuration;

import com.telegram.quiz.quizbot.command.Command;
import com.telegram.quiz.quizbot.command.CommandRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CommandConfig {
    private final CommandRegistry commandRegistry;
    private final List<Command> commands;

    public void init() {
        commands.forEach(commandRegistry::registerCommand);
    }
}
