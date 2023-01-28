package kz.tamoha.vkbotmaven.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import kz.tamoha.vkbotmaven.model.basic.FullName;
import kz.tamoha.vkbotmaven.model.basic.Gender;
import kz.tamoha.vkbotmaven.model.basic.User;
import lombok.val;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ferius_057 (Charles_Grozny)
 */
public class UserDeserializer implements JsonDeserializer<User> {

    @Override
    public User deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
        val jsonObject = jsonElement.getAsJsonObject();

        val id = jsonObject.get("id").getAsInt();
        val firstName = jsonObject.get("firstName").getAsJsonArray().asList()
                .stream().map(String::valueOf).map(e -> e.replace("\"", ""))
                .collect(Collectors.toList());
        val lastName = jsonObject.get("lastName").getAsJsonArray().asList()
                .stream().map(String::valueOf).map(e -> e.replace("\"", ""))
                .collect(Collectors.toList());

        List<FullName> fullNames = new ArrayList<>();
        for (int i = 0; i < firstName.size(); i++) {
            fullNames.add(
                    FullName.builder()
                            .push("[id" + id + "|" + firstName.get(i) + " " + lastName.get(i) + "]")
                            .noPush(firstName.get(i) + " " + lastName.get(i))
                            .build()
            );
        }

        return User.builder()
                .id(id)
                .firstName(firstName)
                .lastName(lastName)
                .fullName(fullNames)
                .gender(Gender.valueOf(jsonObject.get("gender").getAsString()))
                .permission(jsonObject.get("permission").getAsInt())
                .build();
    }
}