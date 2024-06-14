package ru.gpf.telegram.entity;

public final class UserEntity {

    private Long userTelegramId;

    private String userName;

    private UserEntity() {
    }


    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "userTelegramId=" + userTelegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
