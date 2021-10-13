package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;

public class MyDeserializerProducto implements JsonDeserializer<Producto> {

    @Override
    public Producto deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            int id = json.getAsJsonObject().get("id").getAsInt();
            String nombre = json.getAsJsonObject().get("nombre").getAsString();
            float precio = json.getAsJsonObject().get("precio").getAsFloat();

            return new Producto(id,nombre,precio);
        }
        catch (Exception E)
        {
            return null;
        }

    }
}
