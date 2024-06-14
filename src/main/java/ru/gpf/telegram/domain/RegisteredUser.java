package ru.gpf.telegram.domain;

public final class RegisteredUser {
    private RegisteredStatus registeredStatus;

    private RegisteredUser() {
    }

    public RegisteredUser(RegisteredStatus registeredStatus) {
        if (registeredStatus == null) {
            throw new IllegalArgumentException("RegisteredStatus must not be null");
        }
        this.registeredStatus = registeredStatus;
    }

    public RegisteredStatus getRegisteredStatus() {
        return registeredStatus;
    }

    @Override
    public String toString() {
        return "RegisteredUser{" +
                "registeredStatus=" + registeredStatus +
                '}';
    }
}
