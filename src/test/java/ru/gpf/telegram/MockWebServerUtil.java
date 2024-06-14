package ru.gpf.telegram;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class MockWebServerUtil {
    public static void runWithBody400UserAlreadyRegistered(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(400)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(WebClientData.USERS_ALREADY_REGISTER_JSON)
        );
    }

    public static void runWithBodyUnknownError(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(401)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(WebClientData.USERS_ALREADY_REGISTER_JSON)
        );
    }


    public static void runEmptyBody200(MockWebServer mockWebServer) {
        mockWebServer.enqueue(
                new MockResponse().setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        );
    }
}
