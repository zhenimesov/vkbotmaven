//package org.example.gson;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import org.example.gson.parser.modules.People;
//import java.io.*;
//import java.io.IOException;
//import java.io.Serializable;
//
//public class Test implements Serializable {
//    public static void main(String[] args) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        People people = createPeopleObject();
//
//        try (FileWriter writer = new FileWriter("reponses.json")) {
//            gson.toJson(people, writer);
//            System.out.println("УСПЕШНО");
//        } catch (IOException e) {
//            System.out.println("НЕ УСПЕШНО");
//            e.printStackTrace();
//        }
//
//    }
//    public static People createPeopleObject(){
//        People people = new People();
//        people.setFirst_name("Тамоха");
//        people.setLast_name("Хасбулатов");
//        people.setId(271921577);
//
//
//        return people;
//    }
//}
//
