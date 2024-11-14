package com.telegram.quiz.quizbot.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_statistics")
@NoArgsConstructor
@Getter
public class UserStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private TelegramUser user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "correct_answers")
    private int correctAnswers;

    @Column(name = "incorrect_answers")
    private int incorrectAnswers;
}
