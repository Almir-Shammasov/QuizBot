package com.telegram.quiz.quizbot.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_data")
public class UserData {
    @Id
    @Column(name = "user_id")
    private int userId;
    @Column(name = "last_activity")
    private String lastActivity;
    @Column(name = "last_command")
    private String lastCommand;
    @Column(name = "create_at")
    private String createAt;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private TelegramUser user;

}
