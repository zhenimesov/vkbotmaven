package kz.tamoha.vkbotmaven.command.admin;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.annotation.Permission;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;
import lombok.val;

import java.util.List;

@Permission(1)
@CommandAnnotation(aliases = { "default", "участник" })
public class UnSetAdmin extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySenders = cache.getReplySenders().get(0);
        val push = cache.getReplySenders().get(0).getFullName().get(0).getPush();
        User sender = cache.getReplySenders().get(0);

        String msg;

        if(sender == cache.getSender()) {
            msg = MessageTextData.ERROR_ADMIN.getText()
                    .replace("%fullName%", cache.getSender().getFullName().get(0).getPush())
                    .replace("%text%", "вы не можете применять команду на себе");
        } else if (replySenders != null) {

            if (replySenders.getPermission() != 0) {
                sender.updatePerm(manager, 0, sender);
                msg=MessageTextData.DEFAULT.getText()
                        .replace("%fullName%", push);

            } else msg=MessageTextData.ERROR_ADMIN.getText()
                    .replace("%fullName%", push)
                    .replace("%text%", "уже имеет роль участника");

        } else msg=MessageTextData.ERROR_ADMIN.getText()
                .replace("%fullName%", push)
                .replace("%text%", "отсутствует в этой беседе");

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();
    }
}
