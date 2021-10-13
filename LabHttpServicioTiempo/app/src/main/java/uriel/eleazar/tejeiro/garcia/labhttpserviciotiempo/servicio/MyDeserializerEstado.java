package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Estado;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;

public class MyDeserializerEstado implements JsonDeserializer<Estado> {

    @Override
    public Estado deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            int id =   json.getAsJsonObject().get("id").getAsInt();
            String estado = json.getAsJsonObject().get("estado").getAsString();
            return new Estado(id,estado);
        }
        catch (Exception E)
        {
            return new Estado(5,E.getMessage());
        }

    }
}
