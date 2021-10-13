package com.uriel.tejeiro.parcial2cobrad2.globales;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Deuda;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Estado;
import com.uriel.tejeiro.parcial2cobrad2.modelos.Usuario;
import com.uriel.tejeiro.parcial2cobrad2.servicio.IServicioUsuario;

public class DatosGlobales {
    public static IServicioUsuario servicioUsuario;
    public static Call<List<Usuario>> usuarioRecibido;
    public static Usuario cobradorLogueado = new Usuario();
    public static Call<ArrayList<Deuda>> callDeudasCliente;
    public static ArrayList<Deuda> deudasCliente = new ArrayList<Deuda>();
    public static Call<Estado> estadoRecibido;
}
