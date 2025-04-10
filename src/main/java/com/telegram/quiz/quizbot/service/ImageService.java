package com.telegram.quiz.quizbot.service;

import java.io.IOException;

/**
 * This interface is for uploading and retrieving images from the database
 */
public interface ImageService {
    /**
     * Returns the image representation as a byte array
     * @return returns an array of byte type
     * @param id
     */
    byte[] getImage(int id);

    /**
     * Saves the picture to the database
     * @param directoryPath
     * @throws IOException
     */
    void saveImage(String directoryPath) throws IOException;
}
