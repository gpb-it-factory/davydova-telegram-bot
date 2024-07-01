package ru.gpf.telegram;

import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public abstract class AbstractWebClientTest extends AbstractTest {
    public MockWebServer mockWebServer;

    @BeforeEach
    void setupMockWebServer() {
        mockWebServer = new MockWebServer();
        try {
            mockWebServer.start(1111);
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
