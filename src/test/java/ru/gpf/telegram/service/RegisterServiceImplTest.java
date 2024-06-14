package ru.gpf.telegram.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.gpf.telegram.UserData;
import ru.gpf.telegram.gateway.UserGateway;
import ru.gpf.telegram.util.BotAnswer;


class RegisterServiceImplTest {

    @Test
    void checkRegisterSuccess() {
        UserGateway userGateway = Mockito.mock(UserGateway.class);
        Mockito.when(userGateway.registerUser(Mockito.any())).thenReturn(UserData.REGISTERED_USER_SUCCESSFUL);
        RegisterServiceImpl userService = new RegisterServiceImpl(userGateway);

        String answer = userService.registerUser(UserData.USER);

        Assertions.assertEquals(BotAnswer.getRegisterMsg(), answer);
    }

    @Test
    void checkUserAlreadyWasRegistered() {
        UserGateway userGateway = Mockito.mock(UserGateway.class);
        Mockito.when(userGateway.registerUser(Mockito.any())).thenReturn(UserData.REGISTERED_USER_DUPLICATE);
        RegisterServiceImpl userService = new RegisterServiceImpl(userGateway);

        String answer = userService.registerUser(UserData.USER);

        Assertions.assertEquals(BotAnswer.getRegisterErrorMsg(), answer);
    }

    @Test
    void checkErrorRegistered() {
        UserGateway userGateway = Mockito.mock(UserGateway.class);
        Mockito.when(userGateway.registerUser(Mockito.any())).thenReturn(UserData.REGISTERED_USER_UNSUCCESSFUL);
        RegisterServiceImpl userService = new RegisterServiceImpl(userGateway);

        String answer = userService.registerUser(UserData.USER);

        Assertions.assertEquals(BotAnswer.getErrorMsg(), answer);
    }

}