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
@CommandAnnotation(aliases = {"админ", "admin"})
public class SetAdmin extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySenders = cache.getReplySenders().get(0);
        val push = cache.getReplySenders().get(0).getFullName().get(0).getPush();
        User sender = cache.getReplySenders().get(0);

        String msg;
        if (sender == cache.getSender()) {
            msg = MessageTextData.ERROR_TEXT.getText()
                    .replace("%fullName%", cache.getSender().getFullName().get(0).getPush())
                    .replace("%text%", "вы не можете применять команду на себе");
        } else if (replySenders != null) {

            if (replySenders.getPermission() == 0) {
                sender.updatePerm(manager, 1, sender);
                msg = MessageTextData.ADMIN.getText()
                        .replace("%fullName%", push);

            } else msg = MessageTextData.ERROR_TEXT.getText()
                    .replace("%fullName%", push)
                    .replace("%text%", "уже имеет роль админа");

        } else msg = MessageTextData.ERROR_TEXT.getText()
                .replace("%fullName%", push)
                .replace("%text%", "отсутствует в этой беседе");

        vk.messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage(msg)
                .execute();

    }
}
