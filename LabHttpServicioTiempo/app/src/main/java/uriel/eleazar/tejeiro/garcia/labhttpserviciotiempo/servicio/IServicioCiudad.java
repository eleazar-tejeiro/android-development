package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;
// base
// api.openweathermap.org/data/2.5/weather?q=santa cruz,bo&appid=64597b3665f4c8b9f918b0da999890ad&units=metric&lang=es
public interface IServicioCiudad {
    @GET("weather")
    Call<Ciudad> getServicioCiudad(@Query("q") String nombreCiudad, @Query("appid") String apikey, @Query("units") String unidadmetrica, @Query("lang") String languaje);
    @GET("weather")
    Call<Ciudad> getServicioCiudad(@Query("q") String nombreCiudad, @Query("appid") String apikey);

}
