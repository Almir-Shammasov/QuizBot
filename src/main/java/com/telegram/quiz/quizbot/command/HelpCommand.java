package com.telegram.quiz.quizbot.command;

import com.telegram.quiz.quizbot.entity.TelegramUser;
import com.telegram.quiz.quizbot.entity.UserData;
import com.telegram.quiz.quizbot.service.impl.UserDataServiceImpl;
import com.telegram.quiz.quizbot.service.impl.UserServiceImpl;
import com.telegram.quiz.quizbot.service.impl.TelegramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HelpCommand implements Command {
    private final TelegramService telegramService;
    private final UserServiceImpl dbUserService;
    private final UserDataServiceImpl userDataService;

    @Override
    public String getCommandName() {
        return "/help";
    }

    @Override
    public void execute(Message message) {
        telegramService.sendTextMessage("""
                Справочная информация: 
                Этот бот создан для развлекательных целей :3
                В нем вы можете сыграть в квиз выполнив команду /quiz
                Или получить рандомный мемасик выполнив команду /getmemes
                Список всех команд: /commandlist""", message.getChatId());

//        List<TelegramUser> users = dbUserService.getAllUsers();
//        List<Long> allUserId = new ArrayList<>();
//        for (TelegramUser user : users) {
//            allUserId.add(user.getUserId());
//        }
//
//        if(allUserId.contains(message.getFrom().getId())) {
//            userDataService.updateData(new UserData());
//        }
//        dbUserService.writeUser(new TelegramUser(
//                message.getFrom().getId(),
//                message.getChat().getUserName(),
//                message.getChat().getFirstName(),
//                LocalDateTime.now().toString(),
//                message.getText(),
//                "..."));
    }
}
