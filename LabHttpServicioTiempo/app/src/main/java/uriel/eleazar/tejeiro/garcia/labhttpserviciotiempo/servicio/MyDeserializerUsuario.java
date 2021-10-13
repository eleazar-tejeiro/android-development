package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Usuario;

public class MyDeserializerUsuario  implements JsonDeserializer<Usuario> {
    @Override
    public Usuario deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String nro_ci = json.getAsJsonObject().get("nro_ci").getAsString();
            String nombre = json.getAsJsonObject().get("nombre").getAsString();
            String usuario = json.getAsJsonObject().get("usuario").getAsString();
            String estado = json.getAsJsonObject().get("estado").getAsString();
            return new Usuario(nro_ci,nombre,usuario,estado);
        }
        catch (Exception E)
        {
            return null;
        }

    }
}
