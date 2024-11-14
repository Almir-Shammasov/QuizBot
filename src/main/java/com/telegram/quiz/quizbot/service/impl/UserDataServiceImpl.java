package com.telegram.quiz.quizbot.service.impl;

import com.telegram.quiz.quizbot.db.UserDataRepository;
import com.telegram.quiz.quizbot.entity.UserData;
import com.telegram.quiz.quizbot.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDataServiceImpl implements UserDataService {
    private final UserDataRepository repository;
    @Override
    public void writeUserData(UserData data) {
        repository.save(data);
    }

    @Override
    public UserData updateData(UserData data) {
        return repository.save(data);
    }
}
