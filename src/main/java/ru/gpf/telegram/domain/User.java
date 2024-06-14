package ru.gpf.telegram.domain;

public final class User {

    private Long userTelegramId;

    private String userName;

    private User() {
    }

    public User(Long userTelegramId, String userName) {
        if (userTelegramId == null || userName.trim().isEmpty()) {
            throw new IllegalArgumentException("id and name can not be null");
        }
        this.userTelegramId = userTelegramId;
        this.userName = userName;
    }

    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public String getUserName() {
        return userName;
    }


    @Override
    public String toString() {
        return "User{" +
                "userTelegramId=" + userTelegramId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
