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
        User partner1 = cache.getSender();
        User partner2 = cache.getReplySenders().get(0);

        if (partner1.getId() == partner2.getId()) {
            vk.messages.send()
                    .setPeerId(message.getPeerId())
                    .setMessage(MessageTextData.ERROR_CANT_USE_COMMAND_YOUSELF.getText())
                    .execute();
            return;
        }
        String partner1FullName = partner1.getFullName().get(4).getPush();
        String partner2FullName = partner2.getFullName().get(0).getPush();

        double random = Math.random();
        localData.keyboardData.put(random,
                CacheDataKeyboard.builder()
                        .marry(
                                CacheDataKeyboard.Marry.builder()
                                        .partner1(partner1)
                                        .partner2(partner2)
                                        .build()
                        )
                        .build());

        vk.messages.send()
                .setDisableMentions(true)
                .setPeerId(message.getPeerId())
                .setMessage(MessageTextData.MARRY.getText()
                        .replace("%user1%", partner2FullName)
                        .replace("%user2%", partner1FullName))
                .setKeyboard(manager.keyboardBot()
                        .createMarry(String.valueOf(random)))
                .execute();
    }
}

