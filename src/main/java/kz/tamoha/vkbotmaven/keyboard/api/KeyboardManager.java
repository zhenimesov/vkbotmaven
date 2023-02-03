package kz.tamoha.vkbotmaven.keyboard.api;

import api.longpoll.bots.model.events.messages.MessageEvent;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.01.2022 | 22:49 ⭐
 */
public interface KeyboardManager {

    void run(MessageEvent messageEvent);
    void register(Keyboard command);

}