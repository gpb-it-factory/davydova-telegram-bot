package ru.gpf.telegram.bot.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.util.BotAnswer;
import ru.gpf.telegram.util.CommandUtil;

import java.util.Map;

import static ru.gpf.telegram.bot.command.Commands.*;

/**
 * хранит команды, вызывет метод обработки у подходящей команды
 */

@Component
public class CommandHandler {


    private final Map<String, Command> commands;

    public CommandHandler(@Autowired StartCommand startCommand,
                          @Autowired PingCommand pinCommand) {
        this.commands = Map.of(
                START_COMMAND, startCommand,
                PING_COMMAND, pinCommand
        );
    }

    public SendMessage handleCommands(Update update) {
        String command = CommandUtil.getMessageText(update);
        Command commandHandler = commands.get(command);
        if (commandHandler != null) {
            return commandHandler.createAnswer(update);
        } else {
            long chatId = CommandUtil.getChatId(update);
            return new SendMessage(String.valueOf(chatId), BotAnswer.getUnknownMsg());
        }
    }
}
