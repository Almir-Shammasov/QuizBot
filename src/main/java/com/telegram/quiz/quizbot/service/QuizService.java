package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.entity.QuizObject;

import java.util.List;

/**
 * This interface provides methods for retrieving questions for a quiz
 */
public interface QuizService {
    /**
     * Returns the question by its identifier
     * @return returns a QuizObject type
     * @param id
     */
    QuizObject getById(int id);
}
