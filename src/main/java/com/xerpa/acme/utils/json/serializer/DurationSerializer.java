package com.xerpa.acme.utils.json.serializer;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DurationSerializer implements JsonSerializer<Duration> {
    @Override
    public JsonElement serialize(Duration duration, Type type, JsonSerializationContext jsonSerializationContext) {

        if (duration.toNanos() < 0)
            return new JsonPrimitive(LocalTime.MIDNIGHT.plus(duration.multipliedBy(-1)).format(DateTimeFormatter.ofPattern("-HH:mm")));
        return new JsonPrimitive(LocalTime.MIDNIGHT.plus(duration).format(DateTimeFormatter.ofPattern("HH:mm")));
    }
}
