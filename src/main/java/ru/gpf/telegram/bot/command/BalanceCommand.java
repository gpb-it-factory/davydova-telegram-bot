package ru.gpf.telegram.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.service.BalanceService;
import ru.gpf.telegram.util.CommandUtil;

@Component
public class BalanceCommand implements Command {
    private final BalanceService balanceService;

    public BalanceCommand(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    @Override
    public SendMessage createAnswer(Update update) {
        String answer = balanceService.getBalance(update.getMessage().getFrom().getId());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtil.getStringChatId(update));
        sendMessage.setText(answer);
        return sendMessage;
    }
}
