package kz.tamoha.vkbotmaven.command.nickname;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.User;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 04.02.2023 | 0:09 ⭐
 */
public class NickNameList extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        User sender;

    }
}
