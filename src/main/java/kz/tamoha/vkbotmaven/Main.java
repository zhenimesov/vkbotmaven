package kz.tamoha.vkbotmaven;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.VkBotsMethods;
import kz.tamoha.vkbotmaven.command.api.impl.SimpleCommandManager;
import kz.tamoha.vkbotmaven.data.Config;
import kz.tamoha.vkbotmaven.data.LocalData;
import kz.tamoha.vkbotmaven.database.DataBase;
import kz.tamoha.vkbotmaven.longpoll.LongPollHandler;
import kz.tamoha.vkbotmaven.manager.Manager;
import kz.tamoha.vkbotmaven.manager.impl.ManagerImpl;
import lombok.Getter;
import lombok.val;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.io.IoBuilder;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 27.01.2023 | 1:55 ⭐
 */
public class Main {
    @Getter
    private static Manager manager;

    static {
        /* for logging */
        System.setErr(IoBuilder.forLogger().setLevel(Level.ERROR).buildPrintStream());
        System.setOut(IoBuilder.forLogger().setLevel(Level.INFO).buildPrintStream());
    }

    public static void main(String[] args) throws IOException, VkApiException {
        val localData = new LocalData();
        localData.setTimeStartMs(System.currentTimeMillis()); // установка времени запуска
        System.out.printf("Run in %s %n", localData.getTimeStart());

        val config = Config.load(Paths.get("config.properties"));

        val dataBase = DataBase.create(localData.gson, "database.json");
        dataBase.read();


        manager = new ManagerImpl(
                new VkBotsMethods(config.getToken()), localData, dataBase
        );

        val commandManager = SimpleCommandManager.create(manager);

        val longPollHandler = new LongPollHandler(config.getToken(), commandManager);


        // при завершении всё выключить
        Runtime.getRuntime().addShutdownHook(new Thread(longPollHandler::stopPolling));

        // run
        longPollHandler.startPolling();
    }
}