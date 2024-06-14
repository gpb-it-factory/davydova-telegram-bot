package ru.gpf.telegram.gateway;

import ru.gpf.telegram.domain.RegisteredUser;
import ru.gpf.telegram.domain.User;

public interface UserGateway {
    RegisteredUser registerUser(User user);
}
