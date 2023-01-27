package kz.tamoha.vkbotmaven.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public final class Config {
    String token;

    public static Config load(final Path path) throws IOException {
        val properties = new Properties();

        if (Files.exists(path)) {
            try (val input = Files.newInputStream(path)) {
                properties.load(input);
            }
        } else {
            properties.setProperty("token", "token"); 
            try (val os = Files.newOutputStream(path)) {
                properties.store(os, "Config Data");
            }
        }

        return new Config(
                properties.getProperty("token")
        );
    }
}