package kz.tamoha.vkbotmaven.command.api;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;

import java.util.List;

public interface Command {

    void run(CacheDataMessage cache, Message message, String[] args) throws VkApiException;

    void run(CacheDataMessage cache, Message message, List<Message> replyMessages, String[] args) throws VkApiException;
}