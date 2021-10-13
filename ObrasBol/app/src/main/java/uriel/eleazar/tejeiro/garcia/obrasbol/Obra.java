package uriel.eleazar.tejeiro.garcia.obrasbol;

public class Obra {
    private int id;
    private String nombreObra, descripcionObra, fechaInicioObra, estadoObra, nombreResponsableObra;

    public Obra(int id, String nombreObra, String descripcionObra, String fechaInicioObra, String estadoObra, String nombreResponsableObra) {
        this.id = id;
        this.nombreObra = nombreObra;
        this.descripcionObra = descripcionObra;
        this.fechaInicioObra = fechaInicioObra;
        this.estadoObra = estadoObra;
        this.nombreResponsableObra = nombreResponsableObra;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreObra() {
        return nombreObra;
    }

    public void setNombreObra(String nombreObra) {
        this.nombreObra = nombreObra;
    }

    public String getDescripcionObra() {
        return descripcionObra;
    }

    public void setDescripcionObra(String descripcionObra) {
        this.descripcionObra = descripcionObra;
    }

    public String getFechaInicioObra() {
        return fechaInicioObra;
    }

    public void setFechaInicioObra(String fechaInicioObra) {
        this.fechaInicioObra = fechaInicioObra;
    }

    public String getEstadoObra() {
        return estadoObra;
    }

    public void setEstadoObra(String estadoObra) {
        this.estadoObra = estadoObra;
    }

    public String getNombreResponsableObra() {
        return nombreResponsableObra;
    }

    public void setNombreResponsableObra(String nombreResponsableObra) {
        this.nombreResponsableObra = nombreResponsableObra;
    }
}
