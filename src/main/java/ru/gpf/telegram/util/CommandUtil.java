package ru.gpf.telegram.util;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandUtil {
    public static Long getChatId(Update update) {
        return update.getMessage().getChatId();
    }

    public static String getStringChatId(Update update) {
        return update.getMessage().getChatId().toString();
    }

    public static String getMessageText(Update update) {
        return update.getMessage().getText();
    }
}
