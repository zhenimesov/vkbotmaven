package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.FullName;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

import java.util.List;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "profile", "профиль"})
public class Profile extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
       getProfile(cache.getSender(), message.getPeerId());
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        for (User replySender : cache.getReplySenders()) {
            getProfile(replySender, message.getPeerId());
        }
    }

    private void getProfile(User user, int peerId) throws VkApiException {
        List<FullName> fullName = user.getFullName();
        vk.messages.send()
                .setPeerId(peerId)
                .setMessage(MessageTextData.PROFILE.getText()
                        .replace("%id%", String.valueOf(user.getId()))

                        .replace("%p_0%", fullName.get(0).getPush())
                        .replace("%p_1%", fullName.get(1).getPush())
                        .replace("%p_2%", fullName.get(2).getPush())
                        .replace("%p_3%", fullName.get(3).getPush())
                        .replace("%p_4%", fullName.get(4).getPush())
                        .replace("%p_5%", fullName.get(5).getPush())
                        .replace("%np_0%", fullName.get(0).getNoPush())
                        .replace("%np_1%", fullName.get(1).getNoPush())
                        .replace("%np_2%", fullName.get(2).getNoPush())
                        .replace("%np_3%", fullName.get(3).getNoPush())
                        .replace("%np_4%", fullName.get(4).getNoPush())
                        .replace("%np_5%", fullName.get(5).getNoPush())

                        .replace("%gender%", user.getGender().getValue())

                        .replace("%perm%", String.valueOf(user.getPermission()))

                        .replace("%nickname%", user.getNickname().equalsIgnoreCase("0")
                                ? "Не установлен" : user.getNickname()))
                .execute();
    }
}