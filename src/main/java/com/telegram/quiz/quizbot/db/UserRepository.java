package com.telegram.quiz.quizbot.db;

import com.telegram.quiz.quizbot.entity.TelegramUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<TelegramUser, Integer> {
}
