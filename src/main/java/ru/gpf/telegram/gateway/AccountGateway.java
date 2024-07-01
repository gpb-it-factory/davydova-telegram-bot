package ru.gpf.telegram.gateway;

import ru.gpf.telegram.domain.Account;
import ru.gpf.telegram.domain.Balance;
import ru.gpf.telegram.domain.RegisteredAccount;

public interface AccountGateway {
    RegisteredAccount createAccount(Account account);

    Balance getBalance(Long userTelegramId);
}
