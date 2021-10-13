package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Ciudad;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Deuda;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Estado;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Usuario;

public interface IServicioProducto {
    //http://rolandogonzales.eu5.org/servicios/servicio_movil_login_usuario.php?usuario=jvaca&password=123456

    //http://rolandogonzales.eu5.org/servicios/

    //http://rolandogonzales.eu5.org/servicios/obetner_productos_service2.php
    //http://rolandogonzales.eu5.org/servicios/guardar_productos_service.php?nombre=aspirina&precio=60
    @GET("obtener_productos.php")
    Call<ArrayList<Producto>> getServicioProductos();
    @GET("guardar_productos.php")
    Call<Estado> postServicioProductos(@Query("nombre") String nombreCiudad, @Query("precio") float precio);

    @GET("servicio_movil_login_usuario.php")
    Call<ArrayList<Usuario>> getServicioLoginUsuario(@Query("usuario") String usuario, @Query("password") String password);
    //http://rolandogonzales.eu5.org/servicios/servicio_movil_traer_deudas.php?cideudor=452160

    @GET("servicio_movil_traer_deudas.php")
    Call<ArrayList<Deuda>> getServicioTraerDeudas(@Query("cideudor") String cideudor);
}
