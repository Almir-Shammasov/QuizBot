package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.entity.Image;

import java.io.IOException;
import java.util.List;

/**
 * This interface is for uploading and retrieving images from the database
 */
public interface ImageService {
    /**
     * Returns the image representation as a byte array
     * @return returns an array of byte type
     * @param id
     */
    public byte[] getImage(int id);

    /**
     * Returns a list of all images contained in the database
     * @return returns a List of Image type
     */
    List<Image> getAllImages();

    /**
     * Saves the picture to the database
     * @param directoryPath
     * @throws IOException
     */
    void saveImage(String directoryPath) throws IOException;
}
