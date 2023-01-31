package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.exceptions.VkApiResponseException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

import java.util.List;


@CommandAnnotation(aliases = {"кик", "kick"})
public class Kick extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException {
        StringBuilder sb = new StringBuilder();

        for (User user : cache.getReplySenders()) {
            try {
                vk.messages.removeChatUser()
                        .setChatId(message.getPeerId() - 2_000_000_000)
                        .setMemberId(user.getId())
                        .execute();

                sb.append(MessageTextData.KICK.getText()
                        .replace("%user%", "Пользователь")
                        .replace("%fullName%", user.getFullName().get(0).getPush())
                        .replace("%kicked%", "Исключен"));


            } catch (VkApiResponseException e) {
                if (e.getMessage().contains("User not found in chat")) {
                    sb.append(MessageTextData.ERROR_NOT_FOUND_USER.getText()
                            .replace("%user%", user.getFullName().get(1).getPush()));
                }
            }
        }
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(sb.toString())
                .execute();

    }
}