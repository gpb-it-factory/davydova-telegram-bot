package ru.gpf.telegram.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;
import ru.gpf.telegram.*;
import ru.gpf.telegram.entity.AccountEntity;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.web.client.util.ClientProperties;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AccountWebClientTest extends AbstractWebClientTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    ModelMapper mapper;

    @Autowired
    @Qualifier("testProperties")
    private ClientProperties clientProperties;


    private AccountWebClient accountWebClient;

    @BeforeEach
    public void setUpWebClient() {
        accountWebClient = new AccountWebClient(restTemplate, clientProperties);
    }


    @Test
    void checkRequest() {
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
    void checkSuccessResponse() {
        MockWebServerUtil.runEmptyBody200(mockWebServer);

        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(Mockito.any());

        Assertions.assertTrue(errorEntity.isEmpty());
    }

    @Test
    void checkResponseWithError400InResponse() {
        MockWebServerUtil.runWithBody400UserAlreadyRegistered(mockWebServer);

        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(Mockito.any());

        Assertions.assertTrue(errorEntity.isPresent());
    }

    @Test
    void checkResponseWithUnknownErrorInResponse() {
        MockWebServerUtil.runWithBodyUnknownError(mockWebServer);

        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(Mockito.any());

        Assertions.assertTrue(errorEntity.isPresent());
        Matcher.match(errorEntity.get(), WebClientData.ERROR_ENTITY_UNKNOWN_ERROR, "traceId");
    }

}