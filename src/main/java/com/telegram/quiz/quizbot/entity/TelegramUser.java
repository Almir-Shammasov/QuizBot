package com.telegram.quiz.quizbot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "first_name")
    private String firstname;
    @Column(name = "username")
    private String username;
    @Column(name = "user_id")
    private long userid;
    @Column(name = "text")
    private String text;
    @Column(name = "send_time")
    private String sendTime;

    public TelegramUser(String firstname, String username, long userid, String text, String sendTime) {
        this.firstname = firstname;
        this.username = username;
        this.userid = userid;
        this.text = text;
        this.sendTime = sendTime;
    }
}
