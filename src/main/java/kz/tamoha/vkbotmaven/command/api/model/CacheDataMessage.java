package kz.tamoha.vkbotmaven.command.api.model;

import kz.tamoha.vkbotmaven.model.basic.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 04.07.2022 | 17:22 ⭐
 */
@Setter
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheDataMessage {

    User sender;
    List<User> replySenders = new ArrayList<>();
}