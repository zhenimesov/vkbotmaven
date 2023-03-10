package kz.tamoha.vkbotmaven.model.basic;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.additional.NameCase;
import com.google.gson.annotations.JsonAdapter;
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

@Setter
@Getter
@Builder
@ToString
@JsonAdapter(UserDeserializer.class)
@FieldDefaults( level = AccessLevel.PRIVATE)
public class User {
    int id;
    String nickname;
    List<String> firstName;
    List<String> lastName;
    List<FullName> fullName;
    Gender gender;
    int permission;
    Marry marry;



    private static User registrationUser(Manager manager, int id) throws VkApiException, BotException {
        HashMap<Integer, List<String>> fistName = new HashMap<>();
        HashMap<Integer, List<String>> lastName = new HashMap<>();
        Gender gender = Gender.UNKNOWN;


        for (int i = 0; i < 6; i++) {
            List<api.longpoll.bots.model.objects.basic.User> responseObject = manager.vk().users.get()
                    .setFields("sex")
                    .setUserIds(String.valueOf(id))
                    .setNameCase(NameCase.values()[i])
                    .execute().getResponse();

            for (api.longpoll.bots.model.objects.basic.User response : responseObject) {
                List<String> f = fistName.get(response.getId());
                if (f == null) f = new ArrayList<>();
                List<String> l = lastName.get(response.getId());
                if (l == null) l = new ArrayList<>();
                f.add(response.getFirstName());
                l.add(response.getLastName());
                fistName.put(response.getId(), f);
                lastName.put(response.getId(), l);
            }

            if (responseObject.get(0).getSex() == 1)
                gender = Gender.WOMAN;
            else if (responseObject.get(0).getSex() == 2)
                gender = Gender.MAN;
            else {
                throw new BotException(MessageTextData.ERROR_GET_GENDER.getText());
            }
        }

        List<FullName> fullNames = new ArrayList<>();
        for (int i = 0; i < fistName.get(id).size(); i++) {
            fullNames.add(
                    FullName.builder()
                            .push("[id" + id + "|" + fistName.get(id).get(i) + " " + lastName.get(id).get(i) + "]")
                            .noPush(fistName.get(id).get(i) + " " + lastName.get(id).get(i))
                            .build()
            );
        }

        User user = User.builder()
                .id(id)
                .nickname("0")
                .firstName(fistName.get(id))
                .lastName(lastName.get(id))
                .fullName(fullNames)
                .gender(gender)
                .marry(Marry.builder().build())
                .permission(0)
                .build();

        manager.allUsers().add(id);
        manager.dataBaseModel().getUsers().add(user);
        manager.dataBase().write();


        return user;
    }
    public static User get(Manager manager, int id) throws VkApiException, BotException {
        Optional<User> user = manager.dataBaseModel().getUsers().stream()
                .filter(userFilter -> userFilter.getId() == id)
                .findFirst();

        if (user.isEmpty()) {
            return registrationUser(manager, id);
        }

        return user.get();
    }

    public void updateNickname(Manager manager, String nickname, User user) {
        manager.dataBaseModel().getUsers().remove(user);
        user.setNickname(nickname);
        manager.dataBaseModel().getUsers().add(user);
        manager.dataBase().write();
    }

    public void updatePerm(Manager manager, int permission, User user) {
        manager.dataBaseModel().getUsers().remove(user);
        user.setPermission(permission);
        manager.dataBaseModel().getUsers().add(user);
        manager.dataBase().write();
    }
    public User updatePartner(Manager manager, User user, User partner, long date) {
        val users = manager.dataBaseModel().getUsers();

        users.remove(user);
        user.setMarry(Marry.builder()
                .partner(partner)
                .id(partner.getId())
                .date(date)
                .build());
        users.add(user);

        manager.dataBase().write();
        return user;
    }
}