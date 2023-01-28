package kz.tamoha.vkbotmaven.manager.impl;

import api.longpoll.bots.methods.VkBotsMethods;
import kz.tamoha.vkbotmaven.data.LocalData;
import kz.tamoha.vkbotmaven.database.DataBase;
import kz.tamoha.vkbotmaven.manager.Manager;
import kz.tamoha.vkbotmaven.model.basic.DataBaseModel;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 26.06.2022 | 18:09 ⭐
 */
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ManagerImpl implements Manager {
    VkBotsMethods vk;
    LocalData localData;
    DataBase dataBase;


    @Override
    public VkBotsMethods vk() {
        return vk;
    }

    @Override
    public LocalData localData() {
        return localData;
    }

}
