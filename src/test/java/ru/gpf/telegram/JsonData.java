package ru.gpf.telegram;

public class JsonData {
    public static String getBalanceResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpf/telegram/json/Balance.json");
    }

    public static String getBalanceUserNotExistErrorResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpf/telegram/json/BalanceUserNotExist.json");
    }

    public static String getBalanceAccountNotExistErrorResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpf/telegram/json/BalanceErrorAccountNotExist.json");
    }

    public static String getUnknownErrorResponse() {
        return JsonUtil.readFileAsString("src/test/java/ru/gpf/telegram/json/UnknownError.json");
    }
}
