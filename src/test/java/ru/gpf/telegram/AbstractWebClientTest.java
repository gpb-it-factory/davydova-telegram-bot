package ru.gpf.telegram;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gpf.telegram.web.client.util.ClientProperties;

import java.io.IOException;

public abstract class AbstractWebClientTest extends AbstractTest {
    public MockWebServer mockWebServer;

    @Autowired
    private ClientProperties clientProperties;

    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start(clientProperties.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @AfterEach
    void shutDownMockWebServer() {
        try {
            mockWebServer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
