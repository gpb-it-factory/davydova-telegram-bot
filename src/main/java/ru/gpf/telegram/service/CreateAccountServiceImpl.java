package ru.gpf.telegram.service;

import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.Account;
import ru.gpf.telegram.domain.RegisteredAccount;
import ru.gpf.telegram.gateway.AccountGateway;
import ru.gpf.telegram.util.BotAnswer;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
    private final AccountGateway accountGateway;

    public CreateAccountServiceImpl(AccountGateway accountGateway) {
        this.accountGateway = accountGateway;
    }

    @Override
    public String createAccount(Account account) {
        RegisteredAccount registeredAccount = accountGateway.createAccount(account);
        return switch (registeredAccount.getRegisteredStatus()) {
            case REGISTERED -> BotAnswer.getCreateAccountMsg();
            case CONFLICT_REGISTERED -> BotAnswer.getCreateAccountErrorMsg();
            default -> BotAnswer.getErrorMsg();
        };
    }
}
