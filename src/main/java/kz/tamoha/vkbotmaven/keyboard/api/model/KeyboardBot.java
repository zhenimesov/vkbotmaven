package kz.tamoha.vkbotmaven.keyboard.api.model;

import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.CallbackButton;
import com.google.gson.JsonObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 21.03.2022 | 23:50 ⭐
 */
@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KeyboardBot {
    Keyboard remove;

    JsonObject payload = new JsonObject();


    public KeyboardBot() {
        this.remove = new Keyboard(Collections.emptyList());
    }

    public Keyboard createMarry(String payload) {
        JsonObject payloadYes = new JsonObject();
        payloadYes.addProperty("button","marry_yes");
        payloadYes.addProperty("data", payload);

        JsonObject payloadNo = new JsonObject();
        payloadNo.addProperty("button","marry_no");
        payloadNo.addProperty("data", payload);


        List<Button> buttons = new ArrayList<>();

        // line1 - button 1
        Button buttonPositive = new CallbackButton(Button.Color.POSITIVE,
                new CallbackButton.Action("Да", payloadYes));
        buttons.add(buttonPositive);

        // line1 - button 1
        buttons.add(new CallbackButton(Button.Color.NEGATIVE,
                new CallbackButton.Action("Нет", payloadNo)));


        return new Keyboard(Collections.singletonList(buttons))
                .setInline(true);
    }
}