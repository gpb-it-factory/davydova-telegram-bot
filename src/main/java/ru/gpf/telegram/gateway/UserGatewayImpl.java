package ru.gpf.telegram.gateway;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.RegisteredStatus;
import ru.gpf.telegram.domain.RegisteredUser;
import ru.gpf.telegram.domain.User;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.entity.UserEntity;
import ru.gpf.telegram.util.Code;
import ru.gpf.telegram.web.client.UserWebClient;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserGatewayImpl implements UserGateway {
    private static final Logger log = LoggerFactory.getLogger(UserGatewayImpl.class);
    private final UserWebClient userWebClient;
    private final ModelMapper mapper;

    public UserGatewayImpl(UserWebClient userWebClient, ModelMapper mapper) {
        this.userWebClient = userWebClient;
        this.mapper = mapper;
    }

    @Override
    public RegisteredUser registerUser(User user) {
        User userEntity = mapper.map(user, User.class);
        Optional<ErrorEntity> errorEntity = userWebClient.registerUser(mapper.map(userEntity, UserEntity.class));
        if (errorEntity.isPresent()) {
            if (Objects.equals(errorEntity.get().getCode(), Code.ALREADY_REGISTERED)) {
                log.warn(String.valueOf(errorEntity.get()));
                return new RegisteredUser(RegisteredStatus.ALREADY_REGISTERED);
            } else {
                log.error(String.valueOf(errorEntity.get()));
                return new RegisteredUser(RegisteredStatus.UNREGISTERED);
            }
        }
        return new RegisteredUser(RegisteredStatus.REGISTERED);
    }
}
