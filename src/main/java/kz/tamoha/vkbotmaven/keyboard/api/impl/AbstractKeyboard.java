package kz.tamoha.vkbotmaven.keyboard.api.impl;

import api.longpoll.bots.methods.VkBotsMethods;
import kz.tamoha.vkbotmaven.Main;
import kz.tamoha.vkbotmaven.data.LocalData;
import kz.tamoha.vkbotmaven.keyboard.api.Keyboard;
import kz.tamoha.vkbotmaven.keyboard.api.model.CacheDataKeyboard;
import kz.tamoha.vkbotmaven.keyboard.api.model.KeyboardBot;
import kz.tamoha.vkbotmaven.manager.Manager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.01.2022 | 22:03 ⭐
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PROTECTED)
public abstract class AbstractKeyboard implements Keyboard {

    Manager manager;

    VkBotsMethods vk;
    LocalData localData;
    KeyboardBot keyboardBot;


    protected AbstractKeyboard() {
        this.manager = Main.getManager();

        this.vk = manager.vk();
        this.localData = manager.localData();
        this.keyboardBot = manager.keyboardBot();
    }

    public void addCache(CacheDataKeyboard cacheDataKeyboard) {
        double random = Math.random();
        localData.keyboardData.put(random, cacheDataKeyboard);


    }
}