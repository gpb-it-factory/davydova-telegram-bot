package ru.gpf.telegram.web.client;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gpf.telegram.entity.AccountEntity;
import ru.gpf.telegram.entity.ErrorEntity;
import ru.gpf.telegram.web.client.util.ClientProperties;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@Service
public class AccountWebClient {
    private final RestTemplate restTemplate;
    private final ClientProperties clientProperties;

    public AccountWebClient(RestTemplate restTemplate, ClientProperties clientProperties) {
        this.restTemplate = restTemplate;
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
}
