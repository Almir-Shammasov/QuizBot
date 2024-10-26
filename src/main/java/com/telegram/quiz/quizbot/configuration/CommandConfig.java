package com.telegram.quiz.quizbot.configuration;

import com.telegram.quiz.quizbot.command.Command;
import com.telegram.quiz.quizbot.command.CommandRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CommandConfig {
    private final CommandRegistry commandRegistry;
    private final List<Command> commands;

    @PostConstruct
    public void init() {
        commands.forEach(commandRegistry::registerCommand);
    }
}
