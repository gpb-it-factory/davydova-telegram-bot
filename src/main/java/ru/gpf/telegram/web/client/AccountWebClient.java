package ru.gpf.telegram.web.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import ru.gpf.telegram.domain.Balance;
import ru.gpf.telegram.entity.AccountEntity;
import ru.gpf.telegram.entity.BalanceEntity;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.web.client.util.ClientProperties;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountWebClient {
    private static final Logger log = LoggerFactory.getLogger(AccountWebClient.class);
    private final RestTemplate restTemplate;
    private final RestClient restClient;
    private final ClientProperties clientProperties;

    public AccountWebClient(RestTemplate restTemplate, RestClient restClient, ClientProperties clientProperties) {
        this.restTemplate = restTemplate;
        this.restClient = restClient;
        this.clientProperties = clientProperties;
    }

    public Optional<ErrorEntity> createAccount(AccountEntity accountEntity) {
        URI uri = URI.create(clientProperties.getUrl() + clientProperties.getCreateAccountPath());
        ResponseEntity<ErrorEntity> response = restTemplate.postForEntity(uri, accountEntity, ErrorEntity.class);
        if (response.getStatusCode().equals(HttpStatus.OK)) {
            return Optional.empty();
        } else if (response.getStatusCode().equals(HttpStatus.BAD_REQUEST) && response.getBody() != null) {
            return Optional.of(response.getBody());
        }
        return Optional.of(new ErrorEntity("ошибка сервера", "Server error", "500", UUID.randomUUID()));
    }

    public Balance getBalance(Long userTelegramId) {
        URI uri = URI.create(clientProperties.getUrl() + clientProperties.getBalancePath(userTelegramId));
        return restClient
                .get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .exchange((request, response) -> {
                    if (response.getStatusCode() == HttpStatus.OK) {
                        BalanceEntity balanceEntity = Objects.requireNonNull(response.bodyTo(BalanceEntity.class));
                        return new Balance(balanceEntity.balance());
                    } else {
                        ErrorEntity errorEntity = response.bodyTo(ErrorEntity.class);
                        if (errorEntity != null) {
                            log.warn("user {} does not get balance with error {}", userTelegramId, errorEntity);
                            return switch (errorEntity.getCode()) {
                                case "409" -> new Balance(false);
                                case "404" -> new Balance(true);
                                default -> throw new RuntimeException("ошибка back сервера: " + errorEntity);
                            };
                        } else {
                            throw new RuntimeException("неизвестная ошибка back сервера");
                        }
                    }
                });
    }


}
