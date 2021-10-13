package com.uriel.tejeiro.parcial2cobrad2.servicio;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.uriel.tejeiro.parcial2cobrad2.modelos.Deuda;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Estado;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Usuario;

public interface IServicioUsuario {

    @GET("servicio_movil_login_usuario.php")
    Call<List<Usuario>> getServicioLoginUsuario(@Query("usuario") String usuario, @Query("password") String password);

    @GET("servicio_movil_traer_deudas.php")
    Call<ArrayList<Deuda>> getServicioTraerDeudas(@Query("cideudor") String cideudor);

    @GET("servicio_movil_hacer_cobranza.php")
    Call<Estado> postServicioCobranzas(@Query("cicobrador") int ciCobrador,@Query("nombrecobrador") String nombreCobrador,@Query("nrodeuda") int nroDeuda,@Query("montocobrado") float montoCobrado,@Query("fechacobra") String fechaCobrado);
}
