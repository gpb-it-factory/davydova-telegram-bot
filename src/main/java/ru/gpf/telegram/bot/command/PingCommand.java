package ru.gpf.telegram.bot.command;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.gpf.telegram.util.BotAnswer;
import ru.gpf.telegram.util.CommandUtil;

@Component
public class PingCommand implements Command {
    @Override
    public SendMessage createAnswer(Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CommandUtil.getStringChatId(update));
        sendMessage.setText(BotAnswer.getPingMsg());
        return sendMessage;
    }
}
