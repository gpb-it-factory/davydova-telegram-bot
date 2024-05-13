Telegram-bot
========================
Учебный проект в рамках GPF. Telegram-бот выступает как клиентское приложение, инициирует запросы пользователей.
Данные получаются из сервиса выступающего в качестве АВС.

## AS IS:

![mem.jpeg](mem.jpeg)

## TO BE

Предполагается, взаимодействие будет происходить таким образом:

```plantuml
@startuml
actor Client
participant TelegramBot
participant Service
participant ABS
Client -> TelegramBot: HTTP-запрос
activate TelegramBot
TelegramBot -> Service: HTTP-запрос
activate Service
alt Валидация прошла успешно
Service -> ABS: HTTP-запрос
activate ABS
ABS -> Service: HTTP-ответ с данными
deactivate ABS
Service -> TelegramBot: HTTP-ответ с данными
TelegramBot -> Client: HTTP-ответ с данными
else Валидация не прошла
Service -> TelegramBot: HTTP-ответ c кодом 400
TelegramBot -> Client: HTTP-ответ c кодом 400 
end
@enduml
```

### Для локального запуска проекта:

1. Cкачать содержимое Github репозитория командой:
```
   git clone https://github.com/gpb-it-factory/davydova-telegram-bot.git
```

2. Затем перейти в скачанный каталог "davydova-telegram-bot":
```
cd davydova-telegram-bot
```

3. Скомпилировать jar:
```
./gradlew build  
```

4. Cобрать образ:
```
docker build . -t davydova_telegram_bot
```

5. Запустить контейнер (<yourTokne> - ваш токен полученный от FatherBot):
```
docker container run -p 330:330 -e TOKEN_ENV=<yourTokne> --name gbf_bot -d davydova_telegram_bot
```

6. Заниматься прокрастинацией играя в пинг понг с ботом

### Ссылка на Swagger:

Будет проект, будет ссылка на поодробную, хорошо описанную документацию.
