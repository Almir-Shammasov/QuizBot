package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.service.impl.ImageServiceImpl;
import com.telegram.quiz.quizbot.service.impl.TelegramService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.ByteArrayInputStream;
import java.util.*;

@Component
@RequiredArgsConstructor
public class SendImageCommand implements Command {
    private final TelegramService telegramService;
    private final ImageServiceImpl imageService;
    private final Map<Long, List<Integer>> mapOfImages = new HashMap<>();

    @Override
    public String getCommandName() {
        return "/getmemes";
    }

    @Override
    @Transactional
    public void execute(Message message) {
        Long chatId = message.getChatId();

        if(!mapOfImages.containsKey(chatId)) {
            int images = imageService.getCount();
            List<Integer> imagesCount = new ArrayList<>();
            for (int i = 1; i <= images; i++) {
                imagesCount.add(i);
            }
            Collections.shuffle(imagesCount);
            mapOfImages.put(chatId, imagesCount);
        } else {
            if(mapOfImages.containsKey(chatId) && mapOfImages.get(chatId).isEmpty()) {
                int images = imageService.getCount();
                for (int i = 1; i <= images; i++) {
                    mapOfImages.get(chatId).add(i);
                }
                Collections.shuffle(mapOfImages.get(chatId));
            }
        }


        int randomImage = mapOfImages.get(chatId).removeFirst();
        byte[] image = imageService.getImage(randomImage);
        InputFile inputFile = new InputFile(new ByteArrayInputStream(image),  randomImage +".jpg");
        telegramService.sendPhoto(chatId, inputFile);
    }
}
