package kz.tamoha.vkbotmaven.longpoll;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.events.messages.MessageNew;
import kz.tamoha.vkbotmaven.command.api.CommandManager;
import kz.tamoha.vkbotmaven.keyboard.api.KeyboardManager;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class LongPollHandler extends LongPollBot {
    String accessToken;
    CommandManager commandManager;
    KeyboardManager keyboardManager;

    @Override
    public void onMessageNew(MessageNew messageNew) {
        val message = messageNew.getMessage();
        if (message.hasText()) {
            System.out.printf("[*] New message: %s | %s | %s - %s %n", message.getText(),
                    message.getPeerId(), message.getFromId(), message);

            commandManager.run(message);
        }
    }

    @Override
    public void onMessageEvent(MessageEvent messageEvent) {
        System.out.printf("[*] New click button: %s | %s | %s | %s %n", messageEvent.getUserId(),
                messageEvent.getPeerId(), messageEvent.getEventId(), messageEvent.getPayload());

        if (messageEvent.getPayload() != null)
            keyboardManager.run(messageEvent);
    }
}