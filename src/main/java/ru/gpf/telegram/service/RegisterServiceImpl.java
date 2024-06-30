package ru.gpf.telegram.service;

import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.RegisteredUser;
import ru.gpf.telegram.domain.User;
import ru.gpf.telegram.gateway.UserGateway;
import ru.gpf.telegram.monitoring.RegisterMetricsService;
import ru.gpf.telegram.util.BotAnswer;


@Service
public class RegisterServiceImpl implements RegisterService {
    private final UserGateway userGateway;
    private final RegisterMetricsService registerMetricsService;

    public RegisterServiceImpl(UserGateway userGateway, RegisterMetricsService registerMetricsService) {
        this.userGateway = userGateway;
        this.registerMetricsService = registerMetricsService;
    }

    @Override
    public String registerUser(User user) {
        if (user.getUserName() == null || user.getUserName().isEmpty()) {
            return BotAnswer.getErrorMsg();
        }
        RegisteredUser registeredUser = userGateway.registerUser(user);
        String result;
        switch (registeredUser.getRegisteredStatus()) {
            case REGISTERED -> {
                registerMetricsService.incrementRegisterMetric();
                result = BotAnswer.getRegisterMsg();
            }
            case CONFLICT_REGISTERED -> result = BotAnswer.getRegisterErrorMsg();
            default -> result = BotAnswer.getErrorMsg();
        }

        return result;
    }
}
