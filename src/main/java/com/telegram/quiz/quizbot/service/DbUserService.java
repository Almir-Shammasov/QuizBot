package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.db.UserRepository;
import com.telegram.quiz.quizbot.entity.TelegramUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbUserService {
    private final UserRepository repository;

    public void writeUser(TelegramUser user) {
        repository.save(user);
        log.info("User: {} отмечен в базе", user.getFirstname());
    }
}
