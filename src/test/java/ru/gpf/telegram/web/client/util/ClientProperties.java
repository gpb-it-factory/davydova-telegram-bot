package ru.gpf.telegram.web.client.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("testProperties")
public class ClientProperties {
    private final String url;
    private final String registerPath;
    private final String createAccountPath;
    private final int port;

    {
        url = "http://localhost:1111";
        registerPath = "/v1/api/auth";
        createAccountPath = "/v1/api/accounts";
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

    public String getCreateAccountPath() {
        return createAccountPath;
    }

    public int getPort() {
        return port;
    }
}
