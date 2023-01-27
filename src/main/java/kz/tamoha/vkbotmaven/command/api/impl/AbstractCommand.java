package kz.tamoha.vkbotmaven.command.api.impl;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.VkBotsMethods;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.Main;
import kz.tamoha.vkbotmaven.command.api.Command;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.data.LocalData;
import kz.tamoha.vkbotmaven.manager.Manager;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractCommand implements Command {

    Manager manager;

    VkBotsMethods vk;
    LocalData localData;

    protected AbstractCommand() {
        this.manager = Main.getManager();

        this.vk = manager.vk();
        this.localData = manager.localData();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(MessageTextData.ERROR_NO_REPLY_MESSAGE.getText())
                .execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        run(cache, message, args);
    }
}