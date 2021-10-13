package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;

public class MyDeserializer implements JsonDeserializer <Ciudad> {

    @Override
    public Ciudad deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            int id = json.getAsJsonObject().get("id").getAsInt();
            String nombre = json.getAsJsonObject().get("name").getAsString();
            String country = json.getAsJsonObject().get("sys").getAsJsonObject().get("country").getAsString();
            float temperatura = json.getAsJsonObject().get("main").getAsJsonObject().get("temp").getAsFloat();
            String descripcion = json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("description").getAsString();
            String icono = json.getAsJsonObject().get("weather").getAsJsonArray().get(0).getAsJsonObject().get("icon").getAsString();

            return new Ciudad (id,nombre,country,temperatura,descripcion,icono);
        } catch (Exception E) {
            return null;
        }

    }
}
