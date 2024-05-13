package ru.gpf.telegram.util;

/**
 * формирует ответы для пользователя на комманды
 */

public class BotAnswer {
    private static final String PING_ANSWER = "pong";

    private static final String UNKNOWN_ANSWER = "Очень интересно, но ничего не понятно";

    public static String getStartMsg(String userName) {
        StringBuilder builder = new StringBuilder();
        builder.append("Привет ");
        builder.append(userName);
        builder.append("! Давай поиграем в пинг понг, ты начинаешь! \nПросто нажми /ping");
        return builder.toString();
    }

    public static String getPingMsg() {
        return PING_ANSWER;
    }

    public static String getUnknownMsg() {
        return UNKNOWN_ANSWER;
    }
}
