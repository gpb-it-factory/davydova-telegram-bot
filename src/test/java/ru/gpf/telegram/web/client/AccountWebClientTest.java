package ru.gpf.telegram.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.gpf.telegram.*;
import ru.gpf.telegram.domain.Balance;
import ru.gpf.telegram.entity.AccountEntity;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.web.client.util.ClientProperties;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccountWebClientTest extends AbstractWebClientTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestClient restClient;

    @Autowired
    ModelMapper mapper;

    @Autowired
    private ClientProperties clientProperties;


    private AccountWebClient accountWebClient;

    @BeforeEach
    public void setUpWebClient() {
        accountWebClient = new AccountWebClient(restTemplate, restClient, clientProperties);
    }


    @Test
    void checkRequest_CreateAccount() {
        MockWebServerUtil.runEmptyBody200(mockWebServer);
        accountWebClient.createAccount(mapper.map(AccountData.ACCOUNT, AccountEntity.class));

        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/v1/api/accounts");
        assertThat(request.getBody().readUtf8()).isEqualTo("{\"userTelegramId\":1,\"accountName\":\"promotion\"}");
    }

    @Test
    void checkSuccessResponse_CreateAccount() {
        MockWebServerUtil.runEmptyBody200(mockWebServer);

        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(Mockito.any());

        Assertions.assertTrue(errorEntity.isEmpty());
    }

    @Test
    void checkResponseWithError400InResponse_CreateAccount() {
        MockWebServerUtil.runWithBody400UserAlreadyRegistered(mockWebServer);

        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(Mockito.any());

        Assertions.assertTrue(errorEntity.isPresent());
    }

    @Test
    void checkResponseWithUnknownErrorInResponse_CreateAccount() {
        MockWebServerUtil.runWithBodyUnknownError(mockWebServer);

        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(Mockito.any());

        Assertions.assertTrue(errorEntity.isPresent());
        Matcher.match(errorEntity.get(), WebClientData.ERROR_ENTITY_UNKNOWN_ERROR, "traceId");
    }

    @Test
    void checkRequest_GetBalance() {
        MockWebServerUtil.runBalanceBody200(mockWebServer);
        accountWebClient.getBalance(UserData.USER_ID);

        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(request.getMethod()).isEqualTo("GET");
        assertThat(request.getPath()).isEqualTo("/v1/api/users/1/balance");
        assertThat(request.getBody().readUtf8().isEmpty());
    }

    @Test
    void checkSuccessResponse_GetBalance() {
        MockWebServerUtil.runBalanceBody200(mockWebServer);

        Balance balance = accountWebClient.getBalance(UserData.USER_ID);

        Matcher.match(balance, BalanceData.BALANCE_AVAILABLE);
    }

    @Test
    void checkResponseWithError400InResponse409UserNotFound_GetBalance() {
        MockWebServerUtil.runBalanceBody400UserNotFound(mockWebServer);

        Balance balance = accountWebClient.getBalance(UserData.USER_ID);
        System.out.println(balance);

        Matcher.match(balance, BalanceData.BALANCE_USER_NOT_EXIST);
    }

    @Test
    void checkResponseWithError400InResponse404AccountNotFound_GetBalance() {
        MockWebServerUtil.runBalanceBody400AccountNotFound(mockWebServer);

        Balance balance = accountWebClient.getBalance(UserData.USER_ID);

        Matcher.match(balance, BalanceData.BALANCE_ACCOUNT_NOT_EXIST);
    }

    @Test
    void checkResponseWithUnknownErrorInResponse_GetBalance() {
        MockWebServerUtil.runWithBodyUnknownError(mockWebServer);

        Assertions.assertThrows(RuntimeException.class, () -> accountWebClient.getBalance(UserData.USER_ID));
    }


}