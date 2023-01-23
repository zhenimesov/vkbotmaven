package kz.tamoha.vkbotmaven.gson.parser.modules;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {
    @SerializedName("first_name")
    private String name;
    private String last_name;
    private int id;

    public int getId() {
        return id;
    }
    public String getLast_name(){
        return last_name;
    }
    public String getName(){
        return name;
        }

    @Override
    public String toString(){
        return "Root{"+
                "first_name='" + name + '\''+
                ", last_name=" + last_name +
                ", id=" + id +
                '}';
}}
