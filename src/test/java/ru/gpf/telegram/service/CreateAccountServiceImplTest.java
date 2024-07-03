package ru.gpf.telegram.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gpf.telegram.gateway.AccountGatewayImpl;
import ru.gpf.telegram.monitoring.CreateAccountMetricService;
import ru.gpf.telegram.util.BotAnswer;

import static ru.gpf.telegram.AccountData.*;


class CreateAccountServiceImplTest {
    AccountGatewayImpl accountGateway;
    CreateAccountServiceImpl createAccountService;

    @BeforeEach
    void setUp() {
        accountGateway = Mockito.mock(AccountGatewayImpl.class);
        createAccountService = new CreateAccountServiceImpl(accountGateway, Mockito.mock(CreateAccountMetricService.class));
    }

    @Test
    void checkCreateAccountSuccess() {
        Mockito.when(accountGateway.createAccount(Mockito.any())).thenReturn(CREATE_ACCOUNT_SUCCESSFUL);

        String answer = createAccountService.createAccount(ACCOUNT);

        Assertions.assertEquals(BotAnswer.getCreateAccountMsg(), answer);
    }

    @Test
    void checkAccountAlreadyWasCreatedOrUserWasNotRegistered() {
        Mockito.when(accountGateway.createAccount(Mockito.any())).thenReturn(CREATE_ACCOUNT_CONFLICT);

        String answer = createAccountService.createAccount(ACCOUNT);

        Assertions.assertEquals(BotAnswer.getCreateAccountErrorMsg(), answer);
    }

    @Test
    void checkErrorCreated() {
        Mockito.when(accountGateway.createAccount(Mockito.any())).thenReturn(CREATE_ACCOUNT_UNSUCCESSFUL);

        String answer = createAccountService.createAccount(ACCOUNT);

        Assertions.assertEquals(BotAnswer.getErrorMsg(), answer);
    }

}