package com.telegram.quiz.quizbot.db;

import com.telegram.quiz.quizbot.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
