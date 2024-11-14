package com.telegram.quiz.quizbot.db;

import com.telegram.quiz.quizbot.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Integer> {
}
