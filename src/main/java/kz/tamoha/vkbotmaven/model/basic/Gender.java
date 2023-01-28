package kz.tamoha.vkbotmaven.model.basic;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 29.01.2023 | 0:19 ⭐
 */
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum Gender {
    MAN("мужской"),
    WOMAN("женский"),
    UNKNOWN("unknown");

    String value;
}