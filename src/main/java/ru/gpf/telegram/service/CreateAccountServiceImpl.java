package ru.gpf.telegram.service;

import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.Account;
import ru.gpf.telegram.domain.RegisteredAccount;
import ru.gpf.telegram.gateway.AccountGateway;
import ru.gpf.telegram.monitoring.CreateAccountMetricService;
import ru.gpf.telegram.util.BotAnswer;

@Service
public class CreateAccountServiceImpl implements CreateAccountService {
    private final AccountGateway accountGateway;
    private final CreateAccountMetricService createAccountMetricService;

    public CreateAccountServiceImpl(AccountGateway accountGateway, CreateAccountMetricService createAccountMetricService) {
        this.accountGateway = accountGateway;
        this.createAccountMetricService = createAccountMetricService;
    }

    @Override
    public String createAccount(Account account) {
        RegisteredAccount registeredAccount = accountGateway.createAccount(account);
        String result;
        switch (registeredAccount.getRegisteredStatus()) {
            case REGISTERED -> {
                result = BotAnswer.getCreateAccountMsg();
                createAccountMetricService.incrementRegisterMetric();
            }
            case CONFLICT_REGISTERED -> result = BotAnswer.getCreateAccountErrorMsg();
            default -> result = BotAnswer.getErrorMsg();
        }
        return result;
    }
}
