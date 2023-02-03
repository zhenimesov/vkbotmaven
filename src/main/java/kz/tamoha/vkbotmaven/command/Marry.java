package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.messages.Send;
import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.CallbackButton;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

import java.util.ArrayList;
import java.util.List;


@CommandAnnotation(aliases = {"брак", "marry"})
public class Marry extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        String fullName = cache.getSender().getFullName().get(4).getPush();
        String replySenderFullName = cache.getReplySenders().get(0).getFullName().get(0).getPush();
        User sender = cache.getSender();
        User replySender = cache.getReplySenders().get(0);


        try {
            sendCallbackButton(message.getPeerId(), replySenderFullName, fullName);
        } catch (VkApiException e) {
            if (cache.getSender().getId() == cache.getReplySenders().get(0).getId()) {
                vk.messages.send()
                        .setPeerId(message.getPeerId())
                        .setMessage(MessageTextData.ERROR_CANT_USE_COMMAND_YOUSELF.getText())
                        .execute();
            }
        }
    }

    public void sendCallbackButton(int peerId, String replySenderFullName, String fullName) throws VkApiException {
        List<Button> buttons = new ArrayList<>();
        // button 1
        buttons.add(new CallbackButton(Button.Color.POSITIVE, new CallbackButton.Action("Да", null)));
        // button 2
        buttons.add(new CallbackButton(Button.Color.NEGATIVE, new CallbackButton.Action("Нет", null)));

        Keyboard keyboard = new Keyboard(List.of(buttons))
                .setInline(true);

        Send.ResponseBody responseBody = vk.messages.send()
                .setPeerId(peerId)
                .setMessage(MessageTextData.MARRY.getText()
                        .replace("%user1%", replySenderFullName)
                        .replace("%user2%", fullName))
                .setKeyboard(keyboard)
                .execute();
    }
}

