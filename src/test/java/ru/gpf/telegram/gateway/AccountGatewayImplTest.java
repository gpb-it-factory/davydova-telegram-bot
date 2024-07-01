package ru.gpf.telegram.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import ru.gpf.telegram.BalanceData;
import ru.gpf.telegram.Matcher;
import ru.gpf.telegram.UserData;
import ru.gpf.telegram.WebClientData;
import ru.gpf.telegram.domain.RegisteredAccount;
import ru.gpf.telegram.web.client.AccountWebClient;

import java.util.Optional;

import static ru.gpf.telegram.AccountData.*;


class AccountGatewayImplTest {
    AccountWebClient accountWebClient;
    AccountGatewayImpl accountGateway;

    @BeforeEach
    void setUp() {
        accountWebClient = Mockito.mock(AccountWebClient.class);
        accountGateway = new AccountGatewayImpl(accountWebClient, Mockito.mock(ModelMapper.class));
    }

    @Test
    void createAccountSuccessful() {
        Mockito.when(accountWebClient.createAccount(Mockito.any())).thenReturn(Optional.empty());

        RegisteredAccount actual = accountGateway.createAccount(ACCOUNT);

        Matcher.match(actual, CREATE_ACCOUNT_SUCCESSFUL);
    }

    @Test
    void createAccountUnknownError() {
        Mockito.when(accountWebClient.createAccount(Mockito.any())).thenReturn(Optional.of(WebClientData.ERROR_ENTITY_409));

        RegisteredAccount actual = accountGateway.createAccount(ACCOUNT);

        Matcher.match(actual, CREATE_ACCOUNT_CONFLICT);
    }

    @Test
    void createAccountIfAlreadyWasCreatedOrUserWasNotRegistered() {
        Mockito.when(accountWebClient.createAccount(Mockito.any())).thenReturn(Optional.of(WebClientData.ERROR_ENTITY_UNKNOWN_ERROR));

        RegisteredAccount actual = accountGateway.createAccount(ACCOUNT);

        Matcher.match(actual, CREATE_ACCOUNT_UNSUCCESSFUL);
    }

    @Test
    void check_GetBalance() {
        Mockito.when(accountWebClient.getBalance(UserData.USER_ID)).thenReturn(BalanceData.BALANCE_AVAILABLE);

        Assertions.assertDoesNotThrow(() -> accountGateway.getBalance(UserData.USER_ID));
    }

    @Test
    void checkWhenDecimalInBadFormat_GetBalance() {
        Mockito.when(accountWebClient.getBalance(UserData.USER_ID)).thenReturn(BalanceData.BALANCE_AVAILABLE_BAD_FORMAT);

        Assertions.assertThrows(RuntimeException.class, () -> accountGateway.getBalance(UserData.USER_ID));
    }


}