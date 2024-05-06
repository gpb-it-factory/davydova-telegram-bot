Telegram-bot
========================
Учебный проект в рамках GPF

## AS IS:
![mem.jpeg](mem.jpeg)

## TO BE
Предполагается, взаимодействие будет происходить таким образом:

```plantuml
@startuml
participant TelegramBot
participant Service
participant ABS
TelegramBot -> Service: HTTP-запрос
activate Service
alt Валидация прошла успешно
Service -> ABS: HTTP-запрос
activate ABS
ABS -> Service: HTTP-ответ с данными
deactivate ABS
Service -> TelegramBot: HTTP-ответ с данными
else Валидация не прошла
Service -> TelegramBot: HTTP-ответ c кодом 400
end
@enduml
```

### Для запуска проекта:
Если бы проект был готов, тут было бы описание как его запустить, но он не готов.

### Ссылка на Swagger:
Будет проект, будет ссылка на поодробную, хорошо описанную документацию.
