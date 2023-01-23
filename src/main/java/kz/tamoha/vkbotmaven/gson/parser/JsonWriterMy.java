package kz.tamoha.vkbotmaven.gson.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import kz.tamoha.vkbotmaven.gson.parser.modules.People;

import java.io.FileWriter;
import java.io.IOException;

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
