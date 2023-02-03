package kz.tamoha.vkbotmaven.command;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.impl.AbstractCommand;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;

import java.time.Duration;

/**
 * @author Charles_Grozny
 */
@CommandAnnotation(aliases = { "uptime", "аптайм"})
public class Uptime extends AbstractCommand {

    @Override
    public void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException {
        Duration duration = Duration.ofMillis(System.currentTimeMillis() - localData.timeStartMs);
        long days = duration.toDays();
        long hours = duration.minusDays(days).toHours() % 24;
        long minutes = duration.minusHours(hours).toMinutes() % 60;
        long seconds = duration.minusMinutes(minutes).getSeconds() % 60;

        StringBuilder time = new StringBuilder("Время работы: ");
        if (days != 0) time.append(days).append(" дн. ");
        if (hours != 0) time.append(hours).append(" ч. ");
        if (minutes != 0) time.append(minutes).append(" мин. ");
        if (seconds != 0) time.append(seconds).append(" сек. ");

        vk.messages.send()
                .setPeerId(message.getPeerId())
                .setMessage("✅Бот работает\nВремя запуска: " + localData.getTimeStart() + " GMT+6\n" + time)
                .execute();
    }
}