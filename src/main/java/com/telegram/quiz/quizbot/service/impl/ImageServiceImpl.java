package com.telegram.quiz.quizbot.service.impl;

import com.telegram.quiz.quizbot.db.ImageRepository;
import com.telegram.quiz.quizbot.entity.Image;
import com.telegram.quiz.quizbot.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    public byte[] getImage(int id) {
        return imageRepository.getById(id).getData();
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public void saveImage(String directoryPath) throws IOException {
        File folder = new File(directoryPath);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".jpg"));
        if(files != null) {
            for(File file : files) {
                Image image = new Image();
                image.setName(file.getName());
                image.setType(".jpg");
                image.setData(getFileData(file));

                imageRepository.save(image);
            }
        }
    }

    public byte[] getFileData(File file) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return fileInputStream.readAllBytes();
        }
    }
}
