package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.entity.UserData;

public interface UserDataService {
    void writeUserData(UserData data);
    UserData updateData(UserData data);
}
