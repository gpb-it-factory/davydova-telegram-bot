package ru.gpf.telegram.service;

import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.Balance;
import ru.gpf.telegram.gateway.AccountGateway;
import ru.gpf.telegram.util.BotAnswer;

@Service
public class BalanceServiceImpl implements BalanceService {
    private final AccountGateway accountGateway;

    public BalanceServiceImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public String getBalance(Long userTelegramId) {
        Balance balance = accountGateway.getBalance(userTelegramId);
        if (!balance.isUserExist()) {
            return BotAnswer.getBalanceUserDoesNotExistErrorMsg();
        } else if (!balance.isAccountExist()) {
            return BotAnswer.getBalanceAccountDoesNotExistErrorMsg();
        } else {
            return BotAnswer.getBalanceMsg(balance.getBalance());
        }
    }
}
