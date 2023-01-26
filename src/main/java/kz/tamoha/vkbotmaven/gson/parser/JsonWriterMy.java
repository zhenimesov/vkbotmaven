package kz.tamoha.vkbotmaven.gson.parser;

import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.tamoha.vkbotmaven.gson.GetUserExample;
import kz.tamoha.vkbotmaven.gson.parser.modules.People;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static kz.tamoha.vkbotmaven.gson.GetUserExample.USER_ID;

public class JsonWriterMy{
    GetUserExample gu = new GetUserExample();
    People people = new People(USER_ID);

    public JsonWriterMy() throws VkApiException {
    }
    Message message=messageNew
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
