package ru.gpf.telegram.bot.command;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.Matcher;
import ru.gpf.telegram.service.RegisterService;
import ru.gpf.telegram.service.RegisterServiceImpl;
import ru.gpf.telegram.util.BotAnswer;

import static ru.gpf.telegram.CommandData.getSendMsg;
import static ru.gpf.telegram.CommandData.getUpdate;

class RegisterCommandTest {


    @Test
    void createAnswer() {
        RegisterService registerService = Mockito.mock(RegisterServiceImpl.class);
        Mockito.when(registerService.registerUser(Mockito.any())).thenReturn(BotAnswer.getRegisterMsg());
        RegisterCommand registerCommand = new RegisterCommand(registerService);
        Update update = getUpdate(Commands.REGISTER_COMMAND);
        SendMessage sendMessage = registerCommand.createAnswer(update);

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getRegisterMsg()));
    }

    @Test
    void createAnswerBadRequest() {
        RegisterService registerService = Mockito.mock(RegisterServiceImpl.class);
        Mockito.when(registerService.registerUser(Mockito.any())).thenReturn(BotAnswer.getRegisterErrorMsg());
        RegisterCommand registerCommand = new RegisterCommand(registerService);

        SendMessage sendMessage = registerCommand.createAnswer(getUpdate(Commands.REGISTER_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getRegisterErrorMsg()));
    }

    @Test
    void createAnswerServerError() {
        RegisterService registerService = Mockito.mock(RegisterServiceImpl.class);
        Mockito.when(registerService.registerUser(Mockito.any())).thenReturn(BotAnswer.getErrorMsg());
        RegisterCommand registerCommand = new RegisterCommand(registerService);

        SendMessage sendMessage = registerCommand.createAnswer(getUpdate(Commands.REGISTER_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getErrorMsg()));
    }

}