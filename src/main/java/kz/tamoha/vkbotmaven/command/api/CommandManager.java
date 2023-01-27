package kz.tamoha.vkbotmaven.command.api;

import api.longpoll.bots.model.objects.basic.Message;

/**
 * @author whilein
 */
public interface CommandManager {

    void run(Message message);

    void register(Command command);

}
