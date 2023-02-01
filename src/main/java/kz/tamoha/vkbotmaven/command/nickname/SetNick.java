package kz.tamoha.vkbotmaven.command.nickname;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.annotation.MinimalArgs;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;

import java.util.Arrays;
import java.util.regex.Pattern;

@MinimalArgs(1)
@CommandAnnotation(aliases = {"ник", "nickname", "никнейм", "nick "})
public class SetNick extends AbstractCommand {
    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        System.out.println(Arrays.toString(args));
        String nickName = args[0];
        User sender = cache.getSender();

        String msg;
        if (Pattern.compile("^[\\_\\- а-яА-ЯёЁa-zA-Z0-9]+$").matcher(nickName).find()) {
            sender.updateNickname(manager, nickName, sender);

            msg = MessageTextData.NICKNAME.getText()
                    .replace("%fullName%", sender.getFullName().get(0).getPush())
                    .replace("%nickName%", sender.getNickname());

        } else msg = MessageTextData.ERROR_CENSORED_WORD_NICKNAME.getText();

        vk.messages.send()
                .setMessage(msg)
                .setPeerId(message.getPeerId())
                .execute();


    }
}
