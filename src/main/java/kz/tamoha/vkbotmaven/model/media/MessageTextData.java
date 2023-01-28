package kz.tamoha.vkbotmaven.model.media;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @apiNote тут хранится весь текст, который отправляет бот
 */
@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum MessageTextData {
    // Команды
    HELP("Это мои команды:" +
            "\n/help - помощь" +
            "\n/uptime - Время запуска бота"),

    PROFILE("id: %id%\n\n" +
            "" +
            "И: %p_0%\n"
            + "Р: %p_1%\n"
            + "Д: %p_2%\n"
            + "В: %p_3%\n"
            + "Т: %p_4%\n"
            + "П: %p_5%\n\n" +
            "И: %np_0%\n"
            + "Р: %np_1%\n"
            + "Д: %np_2%\n"
            + "В: %np_3%\n"
            + "Т: %np_4%\n"
            + "П: %np_5%\n\n" +
            "" +
            "gender: %gender%\n\n" +
            "" +
            "perm: %perm%"
    ),


    // Команды - ошибки
    ERROR_NO_REPLY_MESSAGE("❗ Сообщение должно быть ответом на другое сообщение или пересланным сообщение"),


    ERROR_REPLY_ONLY_USER("❌ Данная команда работает только на пользователей.")


    ;



    String text;
}