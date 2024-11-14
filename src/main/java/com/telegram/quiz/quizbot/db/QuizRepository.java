package com.telegram.quiz.quizbot.db;

import com.telegram.quiz.quizbot.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Integer> {
}
