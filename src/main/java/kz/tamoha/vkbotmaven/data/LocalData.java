package kz.tamoha.vkbotmaven.data;

import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

/**
 * @author Charles_Grozny
 */
@FieldDefaults(makeFinal = true, level = AccessLevel.PUBLIC)
public class LocalData {

    Gson gson = new Gson();

    @Setter
    @NonFinal
    long timeStartMs;

    @Getter(lazy = true)
    private String timeStart = makeTextTime();

    private String makeTextTime() {
        val timeStartMs = System.currentTimeMillis();
        val simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+6"));
        return simpleDateFormat.format(new Date(timeStartMs));
    }

}