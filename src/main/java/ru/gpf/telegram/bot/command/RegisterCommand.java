package ru.gpf.telegram.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.domain.User;
import ru.gpf.telegram.service.RegisterService;
import ru.gpf.telegram.util.CommandUtil;

@Component
public class RegisterCommand implements Command {
    private final RegisterService registerService;

    public RegisterCommand(RegisterService registerService) {
        this.registerService = registerService;
    }

    @Override
    public SendMessage createAnswer(Update update) {
        SendMessage sendMessage = new SendMessage();

        Long userId = update.getMessage().getFrom().getId();
        String userName = update.getMessage().getFrom().getUserName();
        sendMessage.setText(registerService.registerUser(new User(userId, userName)));
        sendMessage.setChatId(CommandUtil.getStringChatId(update));

        return sendMessage;
    }
}
