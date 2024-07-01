package ru.gpf.telegram.web.client.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public final class ClientProperties {
    @Value("${ms.url}")
    private String url;

    private final String path;

    {
        path = "/v1/api";
    }

    private ClientProperties() {
    }


    public String getUrl() {
        return url;
    }

    public String getRegisterPath() {
        return path + "/auth";
    }

    public String getCreateAccountPath() {
        return path + "/accounts";
    }

    public String getBalancePath(Long id) {
        return path + "/users/" + id + "/balance";
    }
}
