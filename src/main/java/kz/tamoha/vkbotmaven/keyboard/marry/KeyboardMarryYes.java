package kz.tamoha.vkbotmaven.keyboard.marry;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.EventData;
import kz.tamoha.vkbotmaven.keyboard.api.annotation.KeyboardAnnotation;
import kz.tamoha.vkbotmaven.keyboard.api.impl.AbstractKeyboard;
import kz.tamoha.vkbotmaven.keyboard.api.model.CacheDataKeyboard;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 03.02.2023 | 20:32 ⭐
 */
@KeyboardAnnotation("marry_yes")
public class KeyboardMarryYes extends AbstractKeyboard {

    @Override
    public void run(CacheDataKeyboard cacheDataKeyboard, MessageEvent messageEvent) throws VkApiException {
        System.out.println("+");
        vk.messages.sendEventAnswer()
                .setUserId(messageEvent.getUserId())
                .setPeerId(messageEvent.getPeerId())
                .setEventId(messageEvent.getEventId())
                .setEventData(new EventData.ShowSnackbar("ты гей"))
                .execute();
    }
}
