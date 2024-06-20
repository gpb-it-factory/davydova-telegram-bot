package ru.gpf.telegram.web.client.util;

import org.springframework.stereotype.Component;

@Component
public final class ClientProperties {
    private final String url;
    private final String path;

    {
        url = "http://host.docker.internal:7070";
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
}
