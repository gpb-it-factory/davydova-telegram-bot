package ru.gpf.telegram.domain;

public final class RegisteredAccount {
    private RegisteredStatus registeredStatus;

    private RegisteredAccount() {
    }

    public RegisteredAccount(RegisteredStatus registeredStatus) {
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
        return "RegisteredAccount{" +
                "registeredStatus=" + registeredStatus +
                '}';
    }
}
