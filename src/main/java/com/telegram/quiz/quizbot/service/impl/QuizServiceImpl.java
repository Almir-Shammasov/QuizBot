package com.telegram.quiz.quizbot.service.impl;

import com.telegram.quiz.quizbot.db.QuizRepository;
import com.telegram.quiz.quizbot.entity.Quiz;
import com.telegram.quiz.quizbot.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;

    public List<Quiz> getAllQuestions() {
        return quizRepository.findAll();
    }

    public Quiz getById(int id) {
        return quizRepository.getById(id);
    }

    public int getCount() {
        return (int)quizRepository.count();
    }
}
