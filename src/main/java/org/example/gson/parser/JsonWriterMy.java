package org.example.gson.parser;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.methods.impl.users.Get;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.Main;
import org.example.gson.parser.modules.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class JsonWriterMy{
    People people = new People("Tamokha","Khasbulatov",2712713819l);
        public People createPeopleObject() {
            Gson gson=new GsonBuilder().setPrettyPrinting().create();

            try (FileWriter writer=new FileWriter("reponses.json")) {
                gson.toJson(people, writer);
                System.out.println("УСПЕШНО СОХРАНЕН");
            } catch (IOException e) {
                System.out.println("НЕ УСПЕШНО");
                e.printStackTrace();
            }

        return null;
        }
}
