package kz.tamoha.vkbotmaven.command.nickname;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.annotation.MinimalArgs;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

@CommandAnnotation(aliases = {"Удалить ник", "Delete nickname",})
public class UnSetNick extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        User sender = cache.getSender();
        String msg;
        sender.updateNickname(manager, "0", sender);
        msg =MessageTextData.UNSETNICKNAME.getText()
                .replace("%fullName", cache.getSender().getFullName().get(0).getPush());

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(msg)
                .execute();
    }
}
