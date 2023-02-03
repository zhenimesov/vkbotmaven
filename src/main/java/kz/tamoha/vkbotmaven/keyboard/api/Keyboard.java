package kz.tamoha.vkbotmaven.keyboard.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageEvent;
import kz.tamoha.vkbotmaven.keyboard.api.model.CacheDataKeyboard;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.01.2022 | 22:04 ⭐
 */
public interface Keyboard {
    void run(CacheDataKeyboard cacheDataKeyboard, MessageEvent messageEvent) throws VkApiException;
}