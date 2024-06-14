package ru.gpf.telegram;

import ru.gpf.telegram.entity.ErrorEntity;

import java.util.UUID;

public class WebClientData {
    public static final String USERS_ALREADY_REGISTER_JSON = "{\"message\":\"user already was registered. You can not register again\",\"type\":\"General error\",\"code\":\"409\",\"traceId\":\"dff55702-8fbe-4bb5-abdf-41d00fc616fd\"}";

    public static final ErrorEntity ERROR_ENTITY_UNKNOWN_ERROR = new ErrorEntity("ошибка сервера", "Server error", "500", UUID.fromString("dff55702-8fbe-4bb5-abdf-41d00fc616fd"));
    public static final ErrorEntity ERROR_ENTITY_409 = new ErrorEntity("msg", "type", "409", UUID.randomUUID());

}

