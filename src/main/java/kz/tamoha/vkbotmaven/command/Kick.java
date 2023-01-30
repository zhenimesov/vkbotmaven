package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.exceptions.VkApiResponseException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

import java.util.List;



@CommandAnnotation(aliases = {"кик", "kick"})
public class Kick extends AbstractCommand {

    private List<Message> replyMessages(Message message) {
        List<Message> messages=message.getFwdMessages();
        if (message.getReplyMessage() != null) messages.add(0, message.getReplyMessage());

        return messages;
    }

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        List<Message> replyMessages=replyMessages(message);


        if (replyMessages.size() == 0) {
            vk.messages.send()
                    .setPeerId(message.getPeerId())
                    .setMessage(MessageTextData.REPLY_MESSAGES_KICK.getText())
                    .execute();
            return;
        }

        for (Message replyMessage : replyMessages) {
            int fromId=replyMessage.getFromId();


            try {
                vk.messages.removeChatUser()
                        .setChatId(message.getPeerId() - 2_000_000_000)
                        .setMemberId(fromId)
                        .execute();
            } catch (VkApiResponseException e) {
                if (e.getMessage().contains("User not found in chat")) {
                    vk.messages.send()
                            .setMessage(MessageTextData.ERROR_NOT_FOUND_USER.getText());
                }
            }
            String fromName=cache.getReplySenders().get(0).getFullName().get(0).getPush();
            vk.messages.send()
                    .setPeerId(message.getPeerId())
                    .setMessage(MessageTextData.KICK.getText()
                            .replace("%USERS%", "Пользователь")
                            .replace("%fullName%", fromName)
                            .replace("%kicked%", "кикнут"))
                    .execute();
        }
    }
}