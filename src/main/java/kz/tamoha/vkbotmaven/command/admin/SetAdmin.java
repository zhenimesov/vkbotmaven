package kz.tamoha.vkbotmaven.command.admin;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.annotation.Permission;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.database.DataBase;
import kz.tamoha.vkbotmaven.model.basic.User;
import lombok.val;

import java.util.List;

@Permission(9)
@CommandAnnotation(aliases = {"админ", "admin"})
public class SetAdmin extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        val peerId = message.getPeerId();
        val replySenders = cache.getReplySenders().get(0);
        val push = cache.getReplySenders().get(0).getFullName().get(0).getPush();

        String msg;
        if (replySenders != null) {
            if (replySenders.getPermission() == 0) {

            }
        }
    }
}