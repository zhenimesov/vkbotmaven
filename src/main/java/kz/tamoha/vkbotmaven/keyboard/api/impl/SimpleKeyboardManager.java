package kz.tamoha.vkbotmaven.keyboard.api.impl;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.messages.MessageEvent;
import api.longpoll.bots.model.objects.additional.EventData;
import com.google.gson.JsonElement;
import kz.tamoha.vkbotmaven.keyboard.api.Keyboard;
import kz.tamoha.vkbotmaven.keyboard.api.KeyboardManager;
import kz.tamoha.vkbotmaven.keyboard.api.annotation.KeyboardAnnotation;
import kz.tamoha.vkbotmaven.manager.Manager;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;
import kz.tamoha.vkbotmaven.util.AccessingAllClassesInPackage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SimpleKeyboardManager implements KeyboardManager {
    Manager manager;
    Map<String, Keyboard> keyboardMap;

    @SneakyThrows
    public static KeyboardManager create(final Manager manager) {
        val commandManager = new SimpleKeyboardManager(manager, new HashMap<>());
        val classesInPackage = AccessingAllClassesInPackage.getClassesKeyboard("kz.tamoha.vkbotmaven.keyboard");
        for (val clazz : classesInPackage)
            commandManager.register((Keyboard) clazz.getConstructor().newInstance());

        System.out.printf("Зарегистрировано %d кнопок.\n", classesInPackage.size());

        return commandManager;
    }

    @Override
    public void run(final MessageEvent messageEvent) {
        JsonElement payload = messageEvent.getPayload();
        if (payload.isJsonObject()) {
            val asJsonObject = payload.getAsJsonObject();

            val button = asJsonObject.get("button");
            if (button.isJsonNull()) return;

            val data = asJsonObject.get("data");
            if (button.isJsonNull()) return;


            try {
                double asDouble = data.getAsDouble();
                val keyboardData = manager.localData().keyboardData;
                System.out.println(asDouble);
                System.out.println(keyboardData);
                if (!keyboardData.containsKey(asDouble)) {
                    manager.vk().messages.sendEventAnswer()
                            .setUserId(messageEvent.getUserId())
                            .setPeerId(messageEvent.getPeerId())
                            .setEventId(messageEvent.getEventId())
                            .setEventData(new EventData.ShowSnackbar(MessageTextData.ERROR_NOT_RELEVANT.getText()))
                            .execute();
                    return;
                }


                String type = button.getAsString();

                if (type.length() <= 1) return;

                Keyboard keyboard = keyboardMap.get(type);
                if (keyboard == null) return;

                keyboard.run(keyboardData.get(asDouble), messageEvent);

                keyboardData.remove(asDouble);
            } catch (VkApiException e) {
                e.printStackTrace();
            } catch (UnsupportedOperationException ignored) {}

        }
    }


    @Override
    public void register(final Keyboard keyboard) {
        keyboardMap.put(keyboard.getClass().getAnnotation(KeyboardAnnotation.class).value(), keyboard);
    }
}