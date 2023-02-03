package kz.tamoha.vkbotmaven.model.basic;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * ⭐ 06.07.2022 | 0:19 ⭐
 */
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FullName {
    String noPush, push;
}