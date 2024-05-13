package ru.gpf.telegram.bot.command;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Интерфейс для обработки комманд от пользователя бота
 */
public interface Command {
    SendMessage createAnswer(Update update);
}
