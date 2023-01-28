package kz.tamoha.vkbotmaven.model.basic;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.additional.NameCase;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import kz.tamoha.vkbotmaven.deserializers.UserDeserializer;
import kz.tamoha.vkbotmaven.exception.BotException;
import kz.tamoha.vkbotmaven.manager.Manager;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
@ToString
@JsonAdapter(UserDeserializer.class)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class User {
    int id;
    List<String> firstName;
    List<String> lastName;
    List<FullName> fullName;
    Gender gender;
    int permission;

    public static User get(Manager manager, int id) throws VkApiException, BotException {
        Optional<User> user = manager.dataBaseModel().getUsers().stream()
                .filter(userFilter -> userFilter.getId() == id)
                .findFirst();

        if (user.isEmpty()) {
            return registrationUser(manager, id);
        }

        return user.get();
    }


}