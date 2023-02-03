package kz.tamoha.vkbotmaven.command.admin;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Chat;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.DataBaseModel;
import kz.tamoha.vkbotmaven.model.basic.User;
import lombok.val;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 04.02.2023 | 0:10 ⭐
 */
@CommandAnnotation(aliases = {"админы", "admins"})
public class AdminList extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        User sender;
        DataBaseModel data;

    }
}
