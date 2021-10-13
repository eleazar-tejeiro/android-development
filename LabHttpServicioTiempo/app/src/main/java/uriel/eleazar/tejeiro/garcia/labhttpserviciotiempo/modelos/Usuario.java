package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos;

public class Usuario {
    public String nro_ci;
    public String nombre;
    public String usuario;
    public String estado;
    public Usuario(){}

    public Usuario(String nro_ci, String nombre, String usuario, String estado) {
        this.nro_ci = nro_ci;
        this.nombre = nombre;
        this.usuario = usuario;
        this.estado = estado;
    }

    public String getNro_ci() {
        return nro_ci;
    }

    public void setNro_ci(String nro_ci) {
        this.nro_ci = nro_ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
