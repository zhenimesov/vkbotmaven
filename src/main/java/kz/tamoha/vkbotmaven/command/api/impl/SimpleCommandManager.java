package kz.tamoha.vkbotmaven.command.api.impl;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import kz.tamoha.vkbotmaven.command.api.Command;
import kz.tamoha.vkbotmaven.command.api.CommandManager;
import kz.tamoha.vkbotmaven.command.api.annotation.CommandAnnotation;
import kz.tamoha.vkbotmaven.command.api.annotation.MinimalArgs;
import kz.tamoha.vkbotmaven.command.api.annotation.Permission;
import kz.tamoha.vkbotmaven.command.api.model.CacheDataMessage;
import kz.tamoha.vkbotmaven.exception.BotException;
import kz.tamoha.vkbotmaven.manager.Manager;
import kz.tamoha.vkbotmaven.model.basic.User;
import kz.tamoha.vkbotmaven.model.media.MessageTextData;
import kz.tamoha.vkbotmaven.util.AccessingAllClassesInPackage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class SimpleCommandManager implements CommandManager {

    Manager manager;
    Map<String, Command> commandMap;

    CacheDataMessage cacheDataMessage = new CacheDataMessage();

    @SneakyThrows
    public static CommandManager create(final Manager manager) {
        val commandManager = new SimpleCommandManager(manager, new HashMap<>());
        val classesInPackage = AccessingAllClassesInPackage.getClassesCommand("kz.tamoha.vkbotmaven.command");
        for (val clazz : classesInPackage)
            commandManager.register((Command) clazz.getConstructor().newInstance());

        System.out.printf("Зарегистрировано %d команд.\n", classesInPackage.size());

        return commandManager;
    }

    @Override
    public void run(final Message message) {
        val text = message.getText();

        if (text.length() <= 1 || text.charAt(0) != '/')
            return;

        String[] params, args;

        params = text.substring(1)
                .replace("\n", "")
                .strip().split(" ");
        args = Arrays.copyOfRange(params, 1, params.length);

        val command = commandMap.get(params[0].toLowerCase());

        if (command != null) {
            try {
                val messages = replyMessage(message);

                cacheDataMessage.getReplySenders().clear();

                cacheDataMessage.setSender(User.get(manager, message.getFromId()));

                if (!checkSyntax(command, message.getPeerId(), args)) return;
                if (!checkPermission(command, cacheDataMessage.getSender())) return;

                if (messages.size() == 0)
                    command.run(cacheDataMessage, message, args);
                else {
                    val users = cacheDataMessage.getReplySenders();

                    for (val reply : messages) {
                        if (reply.getFromId() > 0) {
                              users.add(User.get(manager, reply.getFromId()));
                        }
                    }

                    if (users.size() == 0) {
                        manager.vk().messages.send()
                                .setPeerId(message.getPeerId())
                                .setDisableMentions(true)
                                .setMessage(MessageTextData.ERROR_REPLY_ONLY_USER.getText())
                                .execute();
                        return;
                    }

                    cacheDataMessage.setReplySenders(users);

                    command.run(cacheDataMessage, message, messages, args);
                }
            } catch (VkApiException e) {
                e.printStackTrace();
            } catch (BotException e) {
                try {
                    manager.vk().messages.send()
                            .setPeerId(message.getPeerId())
                            .setDisableMentions(true)
                            .setMessage(MessageTextData.ERROR_BOT_EXCEPTION.getText()
                                    .replace("%error%", e.getMessage()))
                            .execute();
                } catch (VkApiException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    @Override
    public void register(final Command command) {
        for (val alias : command.getClass().getAnnotation(CommandAnnotation.class).aliases())
            commandMap.put(alias, command);
    }

    private List<Message> replyMessage(Message message) {
        val messages = message.getFwdMessages();
        if (message.getReplyMessage() != null) messages.add(0, message.getReplyMessage());

        return messages;
    }


    private boolean checkPermission(Command command, kz.tamoha.vkbotmaven.model.basic.User user) throws VkApiException {
        val declaredAnnotation = command.getClass().getDeclaredAnnotation(Permission.class);
        if (declaredAnnotation == null || user.getPermission() >= declaredAnnotation.value()) return true;

        System.out.println("нет прав");
     /*   manager.vk().messages.send()
                .setPeerId(peerId)
                .setDisableMentions(true)
                .setMessage("❗ [id" + user.getUserId() + "|" + cacheDataMessage.getSender().getFirstName()[0] + "], у вас недостаточно прав для данной команды.")
                .execute();*/
        return false;
    }

    private boolean checkSyntax(Command command, int peerId, String... args) throws VkApiException {
        val declaredAnnotation = command.getClass().getDeclaredAnnotation(MinimalArgs.class);
        if (declaredAnnotation == null || declaredAnnotation.value() <= args.length) return true;

        manager.vk().messages.send()
                .setPeerId(peerId)
                .setMessage(declaredAnnotation.message())
                .execute();
        return false;
    }
}