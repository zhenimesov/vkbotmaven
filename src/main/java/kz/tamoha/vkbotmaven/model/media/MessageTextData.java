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


    // Команды - ошибки
    ERROR_NO_REPLY_MESSAGE("❗ Сообщение должно быть ответом на другое сообщение или пересланным сообщение"),


    ERROR_REPLY_ONLY_USER("❌ Данная команда работает только на пользователей.")


    ;



    String text;
}