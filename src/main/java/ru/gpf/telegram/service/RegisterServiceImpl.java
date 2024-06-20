package ru.gpf.telegram.service;

import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.RegisteredUser;
import ru.gpf.telegram.domain.User;
import ru.gpf.telegram.gateway.UserGateway;
import ru.gpf.telegram.util.BotAnswer;


@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserGateway userGateway;

    public RegisterServiceImpl(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public String registerUser(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            return BotAnswer.getErrorMsg();
        }
        RegisteredUser registeredUser = userGateway.registerUser(user);
        return switch (registeredUser.getRegisteredStatus()) {
            case REGISTERED -> BotAnswer.getRegisterMsg();
            case CONFLICT_REGISTERED -> BotAnswer.getRegisterErrorMsg();
            default -> BotAnswer.getErrorMsg();
        };
    }
}
