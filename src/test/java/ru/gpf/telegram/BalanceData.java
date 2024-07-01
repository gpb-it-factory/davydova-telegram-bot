package ru.gpf.telegram;

import ru.gpf.telegram.domain.Balance;

import java.math.BigDecimal;

public class BalanceData {
    public final static BigDecimal BALANCE = new BigDecimal("100.00");
    public final static Balance BALANCE_AVAILABLE = new Balance(BALANCE);
    public final static Balance BALANCE_AVAILABLE_BAD_FORMAT = new Balance(new BigDecimal("100.001"));
    public final static Balance BALANCE_USER_NOT_EXIST = new Balance(false);
    public final static Balance BALANCE_ACCOUNT_NOT_EXIST = new Balance(true);
}
