package uriel.eleazar.tejeiro.garcia.obrasbol;

public class Herramienta {
    private int id, idObra;
    private String nombreHerramienta, descripcionHerramienta, fechaPrestamo, estadoHerramienta;

    public Herramienta(int id, int idObra, String nombreHerramienta, String descripcionHerramienta, String fechaPrestamo, String estadoHerramienta) {
        this.id = id;
        this.idObra = idObra;
        this.nombreHerramienta = nombreHerramienta;
        this.descripcionHerramienta = descripcionHerramienta;
        this.fechaPrestamo = fechaPrestamo;
        this.estadoHerramienta = estadoHerramienta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdObra() {
        return idObra;
    }

    public void setIdObra(int idObra) {
        this.idObra = idObra;
    }

    public String getNombreHerramienta() {
        return nombreHerramienta;
    }

    public void setNombreHerramienta(String nombreHerramienta) {
        this.nombreHerramienta = nombreHerramienta;
    }

    public String getDescripcionHerramienta() {
        return descripcionHerramienta;
    }

    public void setDescripcionHerramienta(String descripcionHerramienta) {
        this.descripcionHerramienta = descripcionHerramienta;
    }

    public String getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(String fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public String getEstadoHerramienta() {
        return estadoHerramienta;
    }

    public void setEstadoHerramienta(String estadoHerramienta) {
        this.estadoHerramienta = estadoHerramienta;
    }
}
