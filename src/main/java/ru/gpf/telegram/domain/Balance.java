package ru.gpf.telegram.domain;

import java.math.BigDecimal;

/**
 * isUserExist=false -> user has not registered yet
 * isUserExist=true && isAccountExist=false -> user registered but account was not created
 * isUserExist=true && isAccountExist=true -> user registered, account created, balance available
 */
public final class Balance {
    private boolean isUserExist;
    private boolean isAccountExist;
    private BigDecimal balance;

    private Balance() {
    }

    public Balance(BigDecimal balance) {
        if (balance == null) {
            throw new RuntimeException("Balance cannot be null");
        }
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Balance should be greater than zero");
        }
        this.isAccountExist = true;
        this.isUserExist = true;
        this.balance = balance;
    }

    public Balance(boolean isUserExist) {
        this.isUserExist = isUserExist;
        this.isAccountExist = false;
        this.balance = null;
    }

    public boolean isUserExist() {
        return isUserExist;
    }

    public boolean isAccountExist() {
        return isAccountExist;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "isUserExist=" + isUserExist +
                ", isAccountExist=" + isAccountExist +
                ", balance=" + balance +
                '}';
    }
}
