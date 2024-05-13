FROM openjdk:17-oracle
ENV TOKEN_ENV=$TOKEN_ENV
COPY build/libs/telegram-0.0.1-SNAPSHOT.jar /application.jar
ENTRYPOINT ["java", "-jar", "application.jar", "'--bot.token=$TOKEN_ENV"]