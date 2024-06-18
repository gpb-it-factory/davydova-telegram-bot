package ru.gpf.telegram.util;

/**
 * Формирует ответы для пользователя на команды
 */

public class BotAnswer {
    private BotAnswer() {
    }

    private static final String PING_ANSWER = "pong";

    private static final String UNKNOWN_ANSWER = "Очень интересно, но ничего не понятно";

    private static final String REGISTER_ANSWER = "Вы успешно зарегистрированы";

    private static final String REGISTER_ERROR_ANSWER = "Два раза нельзя войти в одну и ту же реку и зарегистрироваться в одном и том же сервисе";

    private static final String ERROR_ANSWER = "Трудно в это поверить, но в нашей программе что то пошло не так";

    public static String getStartMsg(String userName) {
        StringBuilder builder = new StringBuilder();
        builder.append("Здравствуйте ");
        builder.append(userName);
        builder.append("!\n");
        builder.append("""
                Совсем скоро Вы сможете открыть счет в нашем банке, получить 5000 рублей на счет и купить все о чем вы мечтали!
                А пока Вы можете зарегистрироваться, просто нажмите /register
                """);
        return builder.toString();
    }

    public static String getPingMsg() {
        return PING_ANSWER;
    }

    public static String getUnknownMsg() {
        return UNKNOWN_ANSWER;
    }

    public static String getRegisterMsg() {
        return REGISTER_ANSWER;
    }

    public static String getRegisterErrorMsg() {
        return REGISTER_ERROR_ANSWER;
    }

    public static String getErrorMsg() {
        return ERROR_ANSWER;
    }
}
