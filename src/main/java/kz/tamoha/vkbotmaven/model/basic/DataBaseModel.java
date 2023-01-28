package kz.tamoha.vkbotmaven.model.basic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 29.01.2023 | 0:25 ⭐
 */
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DataBaseModel {
    List<User> users;
}