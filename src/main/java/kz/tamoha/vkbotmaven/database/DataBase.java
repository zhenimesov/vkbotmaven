package kz.tamoha.vkbotmaven.database;

import com.google.gson.Gson;
import kz.tamoha.vkbotmaven.model.basic.DataBaseModel;
import kz.tamoha.vkbotmaven.model.basic.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Ferius_057 (Charles_Grozny)
 * @date ⭐ 28.01.2023 | 1:24 ⭐
 */
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DataBase {
    @Getter
    @NonFinal
    DataBaseModel dataBaseModel;

    Gson gson;
    File fileDataBase;

    public static DataBase create(Gson gson, String fileName) {
        return new DataBase(gson, Paths.get(fileName).toFile());
    }

    public void read() {
        try (FileReader reader = new FileReader(fileDataBase)) {
            dataBaseModel = gson.fromJson(reader, DataBaseModel.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try (FileWriter writer = new FileWriter(fileDataBase)) {
            gson.toJson(dataBaseModel, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Set<Integer> getUsersId() {
        return dataBaseModel.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toSet());
    }

}