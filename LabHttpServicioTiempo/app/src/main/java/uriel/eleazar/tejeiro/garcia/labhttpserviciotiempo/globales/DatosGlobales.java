package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.globales;

import java.util.ArrayList;

import retrofit2.Call;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Deuda;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Estado;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Producto;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos.Usuario;
import uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.servicio.IServicioProducto;

public class DatosGlobales {
    public static ArrayList<Producto> productos = new ArrayList<Producto>();
    public static IServicioProducto servicioProducto;
    public static Call<ArrayList<Producto>> productosRecibidos;
    public static Call<Estado> estadoRecibido;
    public static Call<ArrayList<Usuario>> usuarioRecibido;
    public static Usuario cobradorLogueado = new Usuario();

    public static Call<ArrayList<Deuda>> callDeudasCliente;
    public static ArrayList<Deuda> deudasCliente = new ArrayList<Deuda>();
}
