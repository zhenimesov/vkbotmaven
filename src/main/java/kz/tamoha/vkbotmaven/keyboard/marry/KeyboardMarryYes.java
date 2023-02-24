package kz.tamoha.vkbotmaven.keyboard.marry;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.EventData;
import kz.tamoha.vkbotmaven.keyboard.api.annotation.KeyboardAnnotation;
import kz.tamoha.vkbotmaven.keyboard.api.impl.AbstractKeyboard;
import kz.tamoha.vkbotmaven.keyboard.api.model.CacheDataKeyboard;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;
import lombok.val;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 03.02.2023 | 20:32 ⭐
 */
@KeyboardAnnotation("marry_yes")
public class KeyboardMarryYes extends AbstractKeyboard {

    @Override
    public void run(CacheDataKeyboard cacheDataKeyboard, MessageEvent messageEvent) throws VkApiException {
        val partner1 = cacheDataKeyboard.getMarry().getPartner1();
        val partner2 = cacheDataKeyboard.getMarry().getPartner2();
        if (partner1.getId() != messageEvent.getUserId()) {
            vk.messages.sendEventAnswer()
                    .setUserId(messageEvent.getUserId())
                    .setPeerId(messageEvent.getPeerId())
                    .setEventId(messageEvent.getEventId())
                    .setEventData(new EventData.ShowSnackbar(MessageTextData.ERROR_MARRY_NOT_FOR_YOU.getText()))
                    .execute();
        }

        val date = System.currentTimeMillis();

        partner1.updatePartner(manager, partner1, partner2, date);
        partner2.updatePartner(manager, partner2, partner1, date);

        vk.messages.sendEventAnswer()
                .setUserId(messageEvent.getUserId())
                .setPeerId(messageEvent.getPeerId())
                .setEventId(messageEvent.getEventId())
                .setEventData(new EventData.ShowSnackbar("Поздравляю теперь вы в браке!"))
                .execute();


        vk.messages.send()
                .setPeerId(messageEvent.getPeerId())
                .setMessage(MessageTextData.MARRY_YES_DONE.getText()
                        .replace("%partner1%", partner1.getFullName().get(0).getPush())
                        .replace("%partner2%", partner2.getFullName().get(0).getPush()))
                .execute();
    }
}
