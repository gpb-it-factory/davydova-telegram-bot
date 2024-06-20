package ru.gpf.telegram.gateway;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.gpf.telegram.domain.*;
import ru.gpf.telegram.entity.AccountEntity;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.util.Code;
import ru.gpf.telegram.web.client.AccountWebClient;

import java.util.Objects;
import java.util.Optional;

@Service
public class AccountGatewayImpl implements AccountGateway {
    private static final Logger log = LoggerFactory.getLogger(AccountGatewayImpl.class);
    private final AccountWebClient accountWebClient;
    private final ModelMapper mapper;

    public AccountGatewayImpl(AccountWebClient accountWebClient, ModelMapper mapper) {
        this.accountWebClient = accountWebClient;
        this.mapper = mapper;
    }

    @Override
    public RegisteredAccount createAccount(Account account) {
        Optional<ErrorEntity> errorEntity = accountWebClient.createAccount(mapper.map(account, AccountEntity.class));
        if (errorEntity.isPresent()) {
            if (Objects.equals(errorEntity.get().getCode(), Code.CONFLICT)) {
                log.warn(String.valueOf(errorEntity.get()));
                return new RegisteredAccount(RegisteredStatus.CONFLICT_REGISTERED);
            } else {
                log.error(String.valueOf(errorEntity.get()));
                return new RegisteredAccount(RegisteredStatus.UNREGISTERED);
            }
        }
        return new RegisteredAccount(RegisteredStatus.REGISTERED);
    }
}
