package kz.tamoha.vkbotmaven.gson.parser.modules;

public class People {
    private long id;
    private String first_name;
    private String last_name;
    private int screen_name;


    public void setId(long id) {
        this.id=id;
    }

    public int getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(int screen_name) {
        this.screen_name=screen_name;
    }


    public People(String first_name){
        this.first_name = first_name;
        this.last_name = last_name;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return (int) id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    @Override
    public String toString() {
        return "People{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", screen_name=" + screen_name +
                '}';
    }
}
