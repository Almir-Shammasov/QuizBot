package com.telegram.quiz.quizbot.service;

import com.telegram.quiz.quizbot.db.QuizRepository;
import com.telegram.quiz.quizbot.entity.QuizObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DbQuizService {
    private final QuizRepository quizRepository;

    public List<QuizObject> getAllQuestions() {
        return quizRepository.findAll();
    }

    public QuizObject getById(int id) {
        return quizRepository.getById(id);
    }


}
