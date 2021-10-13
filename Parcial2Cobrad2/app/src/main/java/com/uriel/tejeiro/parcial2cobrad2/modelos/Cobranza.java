package com.uriel.tejeiro.parcial2cobrad2.modelos;

public class Cobranza {
    private int nro_cobranza;
    private String nro_ci_cobrador;
    private String nombre_cobrador;
    private int nro_deuda;
    private float monto_cobrado;
    private String fecha_cobranza;

    public Cobranza() {
    }

    public Cobranza(int nro_cobranza, String nro_ci_cobrador, String nombre_cobrador, int nro_deuda, float monto_cobrado, String fecha_cobranza) {
        this.nro_cobranza = nro_cobranza;
        this.nro_ci_cobrador = nro_ci_cobrador;
        this.nombre_cobrador = nombre_cobrador;
        this.nro_deuda = nro_deuda;
        this.monto_cobrado = monto_cobrado;
        this.fecha_cobranza = fecha_cobranza;
    }

    public int getNro_cobranza() {
        return nro_cobranza;
    }

    public void setNro_cobranza(int nro_cobranza) {
        this.nro_cobranza = nro_cobranza;
    }

    public String getNro_ci_cobrador() {
        return nro_ci_cobrador;
    }

    public void setNro_ci_cobrador(String nro_ci_cobrador) {
        this.nro_ci_cobrador = nro_ci_cobrador;
    }

    public String getNombre_cobrador() {
        return nombre_cobrador;
    }

    public void setNombre_cobrador(String nombre_cobrador) {
        this.nombre_cobrador = nombre_cobrador;
    }

    public int getNro_deuda() {
        return nro_deuda;
    }

    public void setNro_deuda(int nro_deuda) {
        this.nro_deuda = nro_deuda;
    }

    public float getMonto_cobrado() {
        return monto_cobrado;
    }

    public void setMonto_cobrado(float monto_cobrado) {
        this.monto_cobrado = monto_cobrado;
    }

    public String getFecha_cobranza() {
        return fecha_cobranza;
    }

    public void setFecha_cobranza(String fecha_cobranza) {
        this.fecha_cobranza = fecha_cobranza;
    }
}
