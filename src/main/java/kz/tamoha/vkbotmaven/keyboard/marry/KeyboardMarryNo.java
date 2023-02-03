
package kz.tamoha.vkbotmaven.keyboard.marry;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageEvent;
import kz.tamoha.vkbotmaven.keyboard.api.annotation.KeyboardAnnotation;
import kz.tamoha.vkbotmaven.keyboard.api.impl.AbstractKeyboard;
import kz.tamoha.vkbotmaven.keyboard.api.model.CacheDataKeyboard;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 03.02.2023 | 20:32 ⭐
 */
@KeyboardAnnotation("marry_no")
public class KeyboardMarryNo extends AbstractKeyboard {

    @Override
    public void run(CacheDataKeyboard cacheDataKeyboard, MessageEvent messageEvent) throws VkApiException {
        System.out.println("-");
    }
}
