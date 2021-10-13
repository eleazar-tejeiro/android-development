package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Deuda;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Estado;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Usuario;

////base
////http://api.openweathermap.org/data/2.5/weather?q=santa%20cruz,bo&appid=ee1d5ff9987c89f1a9b00c4e56d9b701&lang=es&units=metric
public class APIPRODUCTOS {
    //http://rolandogonzales.eu5.org/servicios/servicio_movil_login_usuario.php?usuario=jvaca&password=123456
    //public static final String URL_BASE = "http://rolandogonzales.eu5.org/servicios/";
    public static final String URL_BASE = "http://urieltejeiro.eu5.org/servicios/";
    public static Retrofit retrofit = null;
    public static Retrofit getAPI(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Producto.class,new MyDeserializerProducto());
        gsonBuilder.registerTypeAdapter(Usuario.class,new MyDeserializerUsuario());
        gsonBuilder.registerTypeAdapter(Deuda.class,new MyDeserializerDeuda());


        //gsonBuilder.registerTypeAdapter(Estado.class,new MyDeserializerEstado());

        if(retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();

        }
        return retrofit;
    }

}
