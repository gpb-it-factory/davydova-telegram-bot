package ru.gpf.telegram.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.domain.Account;
import ru.gpf.telegram.service.CreateAccountService;
import ru.gpf.telegram.util.CommandUtil;

@Component
public class CreateAccountCommand implements Command {
    private final CreateAccountService createAccountService;

    public CreateAccountCommand(CreateAccountService createAccountService) {
        this.createAccountService = createAccountService;
    }

    @Override
    public SendMessage createAnswer(Update update) {
        String answer = createAccountService.createAccount(new Account(update.getMessage().getFrom().getId()));
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtil.getStringChatId(update));
        sendMessage.setText(answer);
        return sendMessage;
    }
}
