package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
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
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(MessageTextData.PROFILE.getText()
                        .replace("%id%", String.valueOf(cache.getSender().getId()))

                        .replace("%p_0%", cache.getSender().getFullName().get(0).getPush())
                        .replace("%p_1%", cache.getSender().getFullName().get(1).getPush())
                        .replace("%p_2%", cache.getSender().getFullName().get(2).getPush())
                        .replace("%p_3%", cache.getSender().getFullName().get(3).getPush())
                        .replace("%p_4%", cache.getSender().getFullName().get(4).getPush())
                        .replace("%p_5%", cache.getSender().getFullName().get(5).getPush())
                        .replace("%np_0%", cache.getSender().getFullName().get(0).getNoPush())
                        .replace("%np_1%", cache.getSender().getFullName().get(1).getNoPush())
                        .replace("%np_2%", cache.getSender().getFullName().get(2).getNoPush())
                        .replace("%np_3%", cache.getSender().getFullName().get(3).getNoPush())
                        .replace("%np_4%", cache.getSender().getFullName().get(4).getNoPush())
                        .replace("%np_5%", cache.getSender().getFullName().get(5).getNoPush())

                        .replace("%gender%", cache.getSender().getGender().getValue())

                        .replace("%perm%", String.valueOf(cache.getSender().getPermission())))
                .execute();
    }

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        for (User replySender : cache.getReplySenders()) {
            vk.messages.send()
                    .setPeerId(message.getPeerId())
                    .setMessage(MessageTextData.PROFILE.getText()
                            .replace("%id%", String.valueOf(cache.getSender().getId()))

                            .replace("%p_0%", replySender.getFullName().get(0).getPush())
                            .replace("%p_1%", replySender.getFullName().get(1).getPush())
                            .replace("%p_2%", replySender.getFullName().get(2).getPush())
                            .replace("%p_3%", replySender.getFullName().get(3).getPush())
                            .replace("%p_4%", replySender.getFullName().get(4).getPush())
                            .replace("%p_5%", replySender.getFullName().get(5).getPush())
                            .replace("%np_0%", replySender.getFullName().get(0).getNoPush())
                            .replace("%np_1%", replySender.getFullName().get(1).getNoPush())
                            .replace("%np_2%", replySender.getFullName().get(2).getNoPush())
                            .replace("%np_3%", replySender.getFullName().get(3).getNoPush())
                            .replace("%np_4%", replySender.getFullName().get(4).getNoPush())
                            .replace("%np_5%", replySender.getFullName().get(5).getNoPush())

                            .replace("%gender%", replySender.getGender().getValue())

                            .replace("%perm%", String.valueOf(replySender.getPermission())))
                    .execute();
        }
    }
}