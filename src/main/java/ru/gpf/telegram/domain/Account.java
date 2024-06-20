package ru.gpf.telegram.domain;

public final class Account {

    private Long userTelegramId;

    private String accountName;

    private Account() {
    }

    public Account(Long userTelegramId) {
        if (userTelegramId == null) {
            throw new IllegalArgumentException("userTelegramId can not be null");
        }
        this.userTelegramId = userTelegramId;
        this.accountName = "promotion";
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "Account{" +
                "userTelegramId=" + userTelegramId +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
