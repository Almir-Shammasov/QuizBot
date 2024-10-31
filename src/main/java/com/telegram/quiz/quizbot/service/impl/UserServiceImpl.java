package com.telegram.quiz.quizbot.service.impl;

import com.telegram.quiz.quizbot.db.UserRepository;
import com.telegram.quiz.quizbot.entity.TelegramUser;
import com.telegram.quiz.quizbot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public void writeUser(TelegramUser user) {
        repository.save(user);
        log.info("User: {} отмечен в базе", user.getFirstname());
    }

    //TODO обновлять пользователей в базе данных а не добавлять каждый раз нового
    //заменить sendtime на lastactiv, обновлять время
    //добавить колонку created_at
    //Разделит ьтаблицу юзер на отдельные таблицы, в одной чисто данные юзера, в других данные
    //в таблице квиз заменить массив на отдельные колонки optional 1, optional 2 ...
}
