package ru.gpf.telegram.bot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.gpf.telegram.BalanceData;
import ru.gpf.telegram.Matcher;
import ru.gpf.telegram.service.BalanceService;
import ru.gpf.telegram.service.BalanceServiceImpl;
import ru.gpf.telegram.util.BotAnswer;

import static ru.gpf.telegram.CommandData.getSendMsg;
import static ru.gpf.telegram.CommandData.getUpdate;

class BalanceCommandTest {
    BalanceService balanceService;
    BalanceCommand balanceCommand;

    @BeforeEach
    void setUp() {
        balanceService = Mockito.mock(BalanceServiceImpl.class);
        balanceCommand = new BalanceCommand(balanceService);
    }

    @Test
    void checkCreateAnswer() {
        Mockito.when(balanceService.getBalance(Mockito.any())).thenReturn(BotAnswer.getBalanceMsg(BalanceData.BALANCE));

        SendMessage sendMessage = balanceCommand.createAnswer(getUpdate(Commands.GET_BALANCE_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getBalanceMsg(BalanceData.BALANCE)));
    }

    @Test
    void checkCreateAnswerWhenUserDoesNotRegistered() {
        Mockito.when(balanceService.getBalance(Mockito.any())).thenReturn(BotAnswer.getBalanceUserDoesNotExistErrorMsg());

        SendMessage sendMessage = balanceCommand.createAnswer(getUpdate(Commands.GET_BALANCE_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getBalanceUserDoesNotExistErrorMsg()));
    }

    @Test
    void checkCreateAnswerWhenAccountDoesNotRegistered() {
        Mockito.when(balanceService.getBalance(Mockito.any())).thenReturn(BotAnswer.getBalanceAccountDoesNotExistErrorMsg());

        SendMessage sendMessage = balanceCommand.createAnswer(getUpdate(Commands.GET_BALANCE_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getBalanceAccountDoesNotExistErrorMsg()));
    }

    @Test
    void checkCreateAnswerServerError() {
        Mockito.when(balanceService.getBalance(Mockito.any())).thenReturn(BotAnswer.getErrorMsg());

        SendMessage sendMessage = balanceCommand.createAnswer(getUpdate(Commands.GET_BALANCE_COMMAND));

        Matcher.match(sendMessage, getSendMsg(BotAnswer.getErrorMsg()));
    }


}