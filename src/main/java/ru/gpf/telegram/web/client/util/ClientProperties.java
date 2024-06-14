package ru.gpf.telegram.web.client.util;

import org.springframework.stereotype.Component;

@Component
public final class ClientProperties {
    private final String url;
    private final String registerPath;

    {
        url = "http://host.docker.internal:7070";
        registerPath = "/v1/api/auth";
    }

    private ClientProperties() {
    }


    public String getUrl() {
        return url;
    }

    public String getRegisterPath() {
        return registerPath;
    }

}
