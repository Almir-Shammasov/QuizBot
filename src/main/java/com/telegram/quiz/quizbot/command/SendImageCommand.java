package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.service.impl.ImageServiceImpl;
import com.telegram.quiz.quizbot.service.impl.TelegramService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SendImageCommand implements Command {
    private final TelegramService telegramService;
    private final ImageServiceImpl imageService;
    private final List<Integer> imagesCount = new ArrayList<>();

    public void fillImagesCount() {
        int imagesSize = imageService.getAllImages().size();
        for (int i = 1; i <= imagesSize; i++) {
            imagesCount.add(i);
        }
        Collections.shuffle(imagesCount);
    }

    @Override
    public String getCommandName() {
        return "/getmemes";
    }

    @Override
    @Transactional
    public void execute(Message message) {
        Long chatId = message.getChatId();
        if(imagesCount.isEmpty()) {
            fillImagesCount();
        }
        int randomImage = imagesCount.removeFirst();
        byte[] image = imageService.getImage(randomImage);
        InputFile inputFile = new InputFile(new ByteArrayInputStream(image),  randomImage +".jpg");
        telegramService.sendPhoto(chatId, inputFile);
    }
}
