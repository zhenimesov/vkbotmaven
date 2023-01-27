package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "help", "помощь", "commands", "команды" })
public class Help extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage(MessageTextData.HELP.getText())
                .execute();
    }
}