package ru.gpf.telegram.bot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.gpf.telegram.Matcher;
import ru.gpf.telegram.service.CreateAccountService;
import ru.gpf.telegram.service.CreateAccountServiceImpl;
import ru.gpf.telegram.util.BotAnswer;

import static ru.gpf.telegram.CommandData.getSendMsg;
import static ru.gpf.telegram.CommandData.getUpdate;

class CreateAccountCommandTest {
    CreateAccountService createAccountService;
    CreateAccountCommand createAccountCommand;

    @BeforeEach
    void setUp() {
        createAccountService = Mockito.mock(CreateAccountServiceImpl.class);
        createAccountCommand = new CreateAccountCommand(createAccountService);
    }

    @Test
    void checkCreateAnswer() {
        Mockito.when(createAccountService.createAccount(Mockito.any())).thenReturn(BotAnswer.getCreateAccountMsg());

        SendMessage sendMessage = createAccountCommand.createAnswer(getUpdate(Commands.CREATE_ACCOUNT_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getCreateAccountMsg()));
    }

    @Test
    void checkCreateAnswerBadRequest() {
        Mockito.when(createAccountService.createAccount(Mockito.any())).thenReturn(BotAnswer.getCreateAccountErrorMsg());

        SendMessage sendMessage = createAccountCommand.createAnswer(getUpdate(Commands.CREATE_ACCOUNT_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getCreateAccountErrorMsg()));
    }

    @Test
    void checkCreateAnswerServerError() {
        Mockito.when(createAccountService.createAccount(Mockito.any())).thenReturn(BotAnswer.getErrorMsg());

        SendMessage sendMessage = createAccountCommand.createAnswer(getUpdate(Commands.CREATE_ACCOUNT_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getErrorMsg()));
    }

}