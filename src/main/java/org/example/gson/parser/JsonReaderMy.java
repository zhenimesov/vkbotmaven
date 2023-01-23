package org.example.gson.parser;

import com.google.gson.Gson;
import org.example.gson.parser.modules.Root;

import java.io.FileReader;

public class JsonReaderMy {
    public Root jsonReader(){
        Gson gson = new Gson();

        try(FileReader reader = new FileReader("reponses.json")) {

            Root root = gson.fromJson(reader, Root.class);
            return root;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
