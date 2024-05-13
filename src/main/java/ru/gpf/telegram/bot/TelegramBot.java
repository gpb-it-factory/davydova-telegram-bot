package ru.gpf.telegram.bot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.gpf.telegram.bot.command.CommandHandler;

/**
 * Telegram bot отвечающий pong на запрос ping
 */

@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final static Logger log = LoggerFactory.getLogger(TelegramBot.class);
    private final Environment environment;
    public final CommandHandler commandHandler;

    public TelegramBot(@Value("${bot.token}") String botToken, Environment environment, CommandHandler commandHandler) {
        super(botToken);
        this.environment = environment;
        this.commandHandler = commandHandler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            sendMessage(commandHandler.handleCommands(update));
        }
    }

    private void sendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
            log.info("sent message in chat with id: {}", sendMessage.getChatId());
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public String getBotUsername() {
        return environment.getProperty("bot.name");
    }
}
