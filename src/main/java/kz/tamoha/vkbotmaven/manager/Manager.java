package kz.tamoha.vkbotmaven.manager;

import api.longpoll.bots.methods.VkBotsMethods;
import kz.tamoha.vkbotmaven.data.LocalData;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.06.2022 | 18:08 ⭐
 */
public interface Manager {
    VkBotsMethods vk();

    LocalData localData();
}