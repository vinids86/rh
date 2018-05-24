package com.xerpa.acme.utils.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xerpa.acme.utils.json.deserializer.LocalDateDeserializer;
import com.xerpa.acme.utils.json.deserializer.LocalDateTimeDeserializer;
import com.xerpa.acme.utils.json.serializer.DurationSerializer;
import com.xerpa.acme.utils.json.serializer.LocalDateSerializer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GsonUtils {

    public static Gson getGson() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        gsonBuilder.registerTypeAdapter(Duration.class, new DurationSerializer());

        return gsonBuilder.create();
    }
}
