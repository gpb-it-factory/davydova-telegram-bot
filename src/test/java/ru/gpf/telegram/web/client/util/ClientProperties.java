package ru.gpf.telegram.web.client.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("testProperties")
public class ClientProperties {
    private final String url;
    private final String registerPath;
    private final int port;

    {
        url = "http://localhost:1111";
        registerPath = "/v1/api/auth";
        port = 1111;
    }

    private ClientProperties() {
    }


    public String getUrl() {
        return url;
    }

    public String getRegisterPath() {
        return registerPath;
    }

    public int getPort() {
        return port;
    }
}
