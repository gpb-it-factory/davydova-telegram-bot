package ru.gpf.telegram.gateway;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import ru.gpf.telegram.Matcher;
import ru.gpf.telegram.UserData;
import ru.gpf.telegram.WebClientData;
import ru.gpf.telegram.domain.RegisteredUser;
import ru.gpf.telegram.web.client.UserWebClient;

import java.util.Optional;

class UserGatewayImplTest {

    @Test
    void registerUserSuccessful() {
        UserWebClient userWebClient = Mockito.mock(UserWebClient.class);
        Mockito.when(userWebClient.registerUser(Mockito.any())).thenReturn(Optional.empty());
        UserGatewayImpl userGateway = new UserGatewayImpl(userWebClient, Mockito.mock(ModelMapper.class));

        RegisteredUser actual = userGateway.registerUser(UserData.USER);

        Matcher.match(actual, UserData.REGISTERED_USER_SUCCESSFUL);
    }

    @Test
    void registerUserUnknownErrorError() {
        UserWebClient userWebClient = Mockito.mock(UserWebClient.class);
        Mockito.when(userWebClient.registerUser(Mockito.any())).thenReturn(Optional.of(WebClientData.ERROR_ENTITY_409));
        UserGatewayImpl userGateway = new UserGatewayImpl(userWebClient, Mockito.mock(ModelMapper.class));

        RegisteredUser actual = userGateway.registerUser(UserData.USER);

        Matcher.match(actual, UserData.REGISTERED_USER_DUPLICATE);
    }

    @Test
    void registerUserIfAlreadyWasRegistered() {
        UserWebClient userWebClient = Mockito.mock(UserWebClient.class);
        Mockito.when(userWebClient.registerUser(Mockito.any())).thenReturn(Optional.of(WebClientData.ERROR_ENTITY_UNKNOWN_ERROR));
        UserGatewayImpl userGateway = new UserGatewayImpl(userWebClient, Mockito.mock(ModelMapper.class));

        RegisteredUser actual = userGateway.registerUser(UserData.USER);

        Matcher.match(actual, UserData.REGISTERED_USER_UNSUCCESSFUL);
    }
}