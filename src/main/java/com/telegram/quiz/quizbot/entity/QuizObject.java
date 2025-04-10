package com.telegram.quiz.quizbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "question_text")
    private String questionText;
    @Column(name = "option_one")
    private String optionOne;
    @Column(name = "option_two")
    private String optionTwo;
    @Column(name = "option_three")
    private String optionThree;
    @Column(name = "option_four")
    private String optionFour;
    @Column(name = "option_five")
    private String optionFive;
    @Column(name = "option_six")
    private String optionSix;
    @Column(name = "correct_optional")
    private int correctOptional;
}
