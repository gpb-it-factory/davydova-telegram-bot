package ru.gpf.telegram.web.client;

import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import ru.gpf.telegram.*;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.web.client.util.ClientProperties;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class UserWebClientTest extends AbstractWebClientTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("testProperties")
    private ClientProperties clientProperties;


    private UserWebClient userWebClient;

    @BeforeEach
    public void setUpWebClient() {
        userWebClient = new UserWebClient(restTemplate, clientProperties);
    }


    @Test
    void checkRequest() {
        MockWebServerUtil.runEmptyBody200(mockWebServer);
        userWebClient.registerUser(Mockito.any());

        RecordedRequest request = null;
        try {
            request = mockWebServer.takeRequest();
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThat(request.getMethod()).isEqualTo("POST");
        assertThat(request.getPath()).isEqualTo("/v1/api/auth");
    }

    @Test
    void checkSuccessResponse() {
        MockWebServerUtil.runEmptyBody200(mockWebServer);

        Optional<ErrorEntity> errorEntity = userWebClient.registerUser(Mockito.any());

        Assertions.assertTrue(errorEntity.isEmpty());
    }

    @Test
    void checkResponseWithError400InResponse() {
        MockWebServerUtil.runWithBody400UserAlreadyRegistered(mockWebServer);

        Optional<ErrorEntity> errorEntity = userWebClient.registerUser(Mockito.any());
        Assertions.assertTrue(errorEntity.isPresent());
    }

    @Test
    void checkResponseWithUnknownErrorInResponse() {
        MockWebServerUtil.runWithBodyUnknownError(mockWebServer);

        Optional<ErrorEntity> errorEntity = userWebClient.registerUser(Mockito.any());

        Assertions.assertTrue(errorEntity.isPresent());
        Matcher.match(errorEntity.get(), WebClientData.ERROR_ENTITY_UNKNOWN_ERROR, "traceId");
    }
}