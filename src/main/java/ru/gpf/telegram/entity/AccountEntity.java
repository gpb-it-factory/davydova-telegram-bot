package ru.gpf.telegram.entity;

public final class AccountEntity {

    private Long userTelegramId;

    private String accountName;

    private AccountEntity() {
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getAccountName() {
        return accountName;
    }

    @Override
    public String toString() {
        return "AccountEntity{" +
                "userTelegramId=" + userTelegramId +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}
