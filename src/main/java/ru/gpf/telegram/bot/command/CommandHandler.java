package ru.gpf.telegram.bot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.util.BotAnswer;
import ru.gpf.telegram.util.CommandUtil;

import java.util.Map;

import static ru.gpf.telegram.bot.command.Commands.*;

/**
 * Хранит команды, вызывает метод обработки у подходящей команды
 */

@Component
public class CommandHandler {


    private static final Logger log = LoggerFactory.getLogger(CommandHandler.class);
    private final Map<String, Command> commands;

    public CommandHandler(@Autowired StartCommand startCommand,
                          @Autowired PingCommand pinCommand,
                          @Autowired RegisterCommand registerCommand,
                          @Autowired CreateAccountCommand createAccountCommand) {
        this.commands = Map.of(
                START_COMMAND, startCommand,
                PING_COMMAND, pinCommand,
                REGISTER_COMMAND, registerCommand,
                CREATE_ACCOUNT_COMMAND, createAccountCommand
        );
    }

    public SendMessage handleCommands(Update update) {
        String command = CommandUtil.getMessageText(update);
        Command commandHandler = commands.get(command);
        if (commandHandler != null) {
            try {
                return commandHandler.createAnswer(update);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                long chatId = CommandUtil.getChatId(update);
                return new SendMessage(String.valueOf(chatId), BotAnswer.getErrorMsg());
            }

        } else {
            long chatId = CommandUtil.getChatId(update);
            return new SendMessage(String.valueOf(chatId), BotAnswer.getUnknownMsg());
        }
    }
}
