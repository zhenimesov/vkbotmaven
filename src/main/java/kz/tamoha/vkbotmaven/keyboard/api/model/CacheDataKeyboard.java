package kz.tamoha.vkbotmaven.keyboard.api.model;

import kz.tamoha.vkbotmaven.model.basic.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 03.02.2023 | 23:51 ⭐
 */
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CacheDataKeyboard {
    Marry marry;


    @Getter
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Marry {
        User partner1, partner2; // partner1 - Это тот кто кидает предложение!!!
    }
}