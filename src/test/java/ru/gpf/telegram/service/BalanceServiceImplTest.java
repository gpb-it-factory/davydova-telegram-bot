package ru.gpf.telegram.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gpf.telegram.BalanceData;
import ru.gpf.telegram.UserData;
import ru.gpf.telegram.gateway.AccountGatewayImpl;
import ru.gpf.telegram.util.BotAnswer;


class BalanceServiceImplTest {
    AccountGatewayImpl accountGateway;
    BalanceService balanceService;

    @BeforeEach
    void setUp() {
        accountGateway = Mockito.mock(AccountGatewayImpl.class);
        balanceService = new BalanceServiceImpl(accountGateway);
    }

    @Test
    void checkGetBalanceSuccess() {
        Mockito.when(accountGateway.getBalance(Mockito.any())).thenReturn(BalanceData.BALANCE_AVAILABLE);

        String answer = balanceService.getBalance(UserData.USER_ID);

        Assertions.assertEquals(answer, BotAnswer.getBalanceMsg(BalanceData.BALANCE));
    }

    @Test
    void checkGetBalanceUserNotFound() {
        Mockito.when(accountGateway.getBalance(Mockito.any())).thenReturn(BalanceData.BALANCE_USER_NOT_EXIST);

        String answer = balanceService.getBalance(UserData.USER_ID);

        Assertions.assertEquals(answer, BotAnswer.getBalanceUserDoesNotExistErrorMsg());
    }

    @Test
    void checkGetBalanceAccountNotFound() {
        Mockito.when(accountGateway.getBalance(Mockito.any())).thenReturn(BalanceData.BALANCE_ACCOUNT_NOT_EXIST);

        String answer = balanceService.getBalance(UserData.USER_ID);

        Assertions.assertEquals(answer, BotAnswer.getBalanceAccountDoesNotExistErrorMsg());
    }
}