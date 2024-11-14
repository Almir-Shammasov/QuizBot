package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.entity.Quiz;

import java.util.List;

/**
 * This interface provides methods for retrieving questions for a quiz
 */
public interface QuizService {
    /**
     * Returns all questions contained in the database
     * @return returns a List of QuizObject type
     */
    List<Quiz> getAllQuestions();

    /**
     * Returns the question by its identifier
     * @return returns a QuizObject type
     * @param id
     */
    Quiz getById(int id);
}
