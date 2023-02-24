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
    HELP("Привет %first_name%" +
            "\nЭто мои команды:" +
            "\n/help - помощь" +
            "\n/uptime - Время запуска бота" +
            "\n/nick - Поставить свой ник в боте" +
            "\n\n ОФОРМЛЮ ПОТОМ ХЕЛП"),

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
            "perm: %perm% \n\n" +
            "nickname: %nickname%"
    ),

    KICK("%user% %fullName% был %kicked% из этой беседы."),
    NICKNAME("✏ %fullName%, вы изменили свой ник на: %nickName%."),
    UNSETNICKNAME("✏ %fullName%, вы успешно удалили свой ник."),
    ADMIN("✅ Пользователь %fullName%, теперь стал(-а) админом."),
    DEFAULT("✅ Пользователь %fullName%, теперь стал(-а) участником."),
    MARRY("\uD83D\uDC98 Согласны ли вы %user1%, вступить брак с %user2% ?"),
    ERROR_MARRY_HAVE_MARRY_YOU("Вы уже состоите в браке!"),
    ERROR_MARRY_HAVE_MARRY_PARTNER("%partner% уже состоит в браке."),

    MARRY_BUSY("%fullName% уже занят \uD83D\uDC94"),

    // Команды - ошибки,
    ERROR_CENSORED_WORD_NICKNAME("❗ В вашем нике запрещенные символы."),
    ERROR_NO_REPLY_MESSAGE("❗ Сообщение должно быть ответом на другое сообщение."),

    ERROR_REPLY_ONLY_USER("❌ Данная команда работает только на пользователей."),

    ERROR_NOT_FOUND_USER("❌ %user% отсуствует в беседе.\n\n"),

    ERROR_GET_GENDER("❌ Не удалось определить пол пользователя."),

    ERROR_BOT_EXCEPTION("[ERROR]: %error%"),

    ERROR_REPLY_MESSAGES_KICK("❌ Перешлите это сообщения, кого хотите кикнуть."),
    ERROR_TEXT("\uD83D\uDD0D %fullName%, %text%."),
    ERROR_CANT_USE_COMMAND_YOUSELF("\uD83D\uDD0D вы не можете использовать команду на себе."),





    // кнопки
    ERROR_NOT_RELEVANT("Кнопка уже не акутальна :D"),

    ERROR_MARRY_NOT_FOR_YOU("Увы, но предолжения было сделано не тебе)"),

    MARRY_YES_DONE("\uD83D\uDC9EПоздравим пару %partner1% и %partner2% этой беседы." +
            "\nОни вступили в брак, удачи им!!!\uD83D\uDC98"),











    ;



    String text;

}