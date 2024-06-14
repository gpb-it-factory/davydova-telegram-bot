package ru.gpf.telegram.bot.command;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.gpf.telegram.Matcher;
import ru.gpf.telegram.util.BotAnswer;

import static ru.gpf.telegram.CommandData.*;


class StartCommandTest {
    @Test
    void createAnswer() {
        StartCommand startCommand = new StartCommand();

        SendMessage result = startCommand.createAnswer(getUpdate(Commands.START_COMMAND));

        Matcher.match(result, getSendMsg(BotAnswer.getStartMsg(USER_FIRST_NAME)));
    }
}