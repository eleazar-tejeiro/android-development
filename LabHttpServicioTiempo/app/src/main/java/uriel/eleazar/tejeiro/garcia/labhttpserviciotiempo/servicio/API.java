package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;

// base
// api.openweathermap.org/data/2.5/weather?q=santa cruz,bo&appid=64597b3665f4c8b9f918b0da999890ad&units=metric&lang=es
public class API {
    public static final String URL_BASE = "http://api.openweathermap.org/data/2.5/";
    public static final String APIKEY = "64597b3665f4c8b9f918b0da999890ad";
    public static final String URL_BASE_ICONOS = "http://openweathermap.org/img/wn/";
    public static final String FORMATO_IMAGEN = "@2x.png";
    public static Retrofit retrofit = null;

    public static Retrofit getAPI() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Ciudad.class, new MyDeserializer());
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                    .build();
        }
        return retrofit;
    }

}
