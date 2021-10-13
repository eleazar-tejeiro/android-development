package com.example.centrovetpatitas;

public class Consulta {
    private int id, idCita;
    private String NombreVeterinario, FechaCita, HoraCita, RazonCita;

    public Consulta(int id, String nombreVeterinario, String fechaCita, String horaCita, String razonCita) {
        this.id = id;
        NombreVeterinario = nombreVeterinario;
        FechaCita = fechaCita;
        HoraCita = horaCita;
        RazonCita = razonCita;
    }

    public Consulta(int id, int idCita, String nombreVeterinario, String fechaCita, String horaCita, String razonCita) {
        this.id = id;
        this.idCita = idCita;
        NombreVeterinario = nombreVeterinario;
        FechaCita = fechaCita;
        HoraCita = horaCita;
        RazonCita = razonCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreVeterinario() {
        return NombreVeterinario;
    }

    public void setNombreVeterinario(String nombreVeterinario) {
        NombreVeterinario = nombreVeterinario;
    }

    public String getFechaCita() {
        return FechaCita;
    }

    public void setFechaCita(String fechaCita) {
        FechaCita = fechaCita;
    }

    public String getHoraCita() {
        return HoraCita;
    }

    public void setHoraCita(String horaCita) {
        HoraCita = horaCita;
    }

    public String getRazonCita() {
        return RazonCita;
    }

    public void setRazonCita(String razonCita) {
        RazonCita = razonCita;
    }
}
