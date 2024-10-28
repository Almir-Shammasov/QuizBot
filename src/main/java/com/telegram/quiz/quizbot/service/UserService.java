package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.entity.TelegramUser;

/**
 * This interface provides methods for user experience
 */
public interface UserService {
    /**
     * Logs the user into the database
     * @param user
     */
    void writeUser(TelegramUser user);
}
