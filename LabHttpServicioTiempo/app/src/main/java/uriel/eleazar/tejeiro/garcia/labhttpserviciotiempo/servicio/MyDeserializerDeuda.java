package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Deuda;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Usuario;

public class MyDeserializerDeuda implements JsonDeserializer<Deuda> {
    @Override
    public Deuda deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            String ci_deudor = json.getAsJsonObject().get("ci_deudor").getAsString();
            String estado = json.getAsJsonObject().get("estado").getAsString();
            String fecha_vencimiento = json.getAsJsonObject().get("fecha_vencimiento").getAsString();
            String glosa_deuda = json.getAsJsonObject().get("glosa_deuda").getAsString();
            float monto = json.getAsJsonObject().get("monto").getAsFloat();
            String nombre_deudor = json.getAsJsonObject().get("nombre_deudor").getAsString();
            int nro_deuda = json.getAsJsonObject().get("nro_deuda").getAsInt();

            return new Deuda(ci_deudor,estado,fecha_vencimiento,glosa_deuda,monto,nombre_deudor,nro_deuda);
        }
        catch (Exception E)
        {
            return null;
        }
    }
}
