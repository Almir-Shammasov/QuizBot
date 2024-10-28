package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.service.ImageService;
import com.telegram.quiz.quizbot.service.TelegramService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;

@Component
@RequiredArgsConstructor
public class SendImageCommand implements Command {
    private final TelegramService telegramService;
    private final ImageService imageService;

    @Override
    public String getCommandName() {
        return "/getmemes";
    }

    @Override
    @Transactional
    public void execute(Message message) {
        byte[] image = imageService.getImage(1);
        InputFile inputFile = new InputFile(new ByteArrayInputStream(image),  1 +".jpg");
        telegramService.sendPhoto(message.getChatId(), inputFile);
    }
}
