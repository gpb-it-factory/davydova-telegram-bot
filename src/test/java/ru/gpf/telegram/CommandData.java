package ru.gpf.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

public class CommandData {
    public static final Long CHAT_ID = 1L;
    public static final String USER_FIRST_NAME = "Durov";

    public static Message getMsg(String text) {
        Message message = new Message();
        message.setText(text);
        message.setFrom(getUser());
        message.setChat(getChat());
        return message;
    }

    public static SendMessage getSendMsg(String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setChatId(CHAT_ID);
        return sendMessage;
    }

    public static Update getUpdate(String text) {
        Update update = new Update();
        update.setMessage(getMsg(text));
        return update;
    }

    private static User getUser() {
        User user = new User();
        user.setId(UserData.USER_ID);
        user.setFirstName(USER_FIRST_NAME);
        user.setUserName(UserData.USER_NAME);
        user.setIsBot(true);
        return user;
    }

    private static Chat getChat() {
        Chat chat = new Chat(CHAT_ID, "");
        chat.setUserName(USER_FIRST_NAME);
        return chat;
    }
}
