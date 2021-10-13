package com.uriel.tejeiro.parcial2cobrad2.modelos;

import androidx.annotation.NonNull;

public class Deuda {
    private String ci_deudor;
    private String estado;
    private String fecha_vencimiento;
    private String glosa_deuda;
    private float monto;
    private String nombre_deudor;
    private int nro_deuda;
    public Deuda(){}

    public Deuda(String ci_deudor, String estado, String fecha_vencimiento, String glosa_deuda, float monto, String nombre_deudor, int nro_deuda) {
        this.ci_deudor = ci_deudor;
        this.estado = estado;
        this.fecha_vencimiento = fecha_vencimiento;
        this.glosa_deuda = glosa_deuda;
        this.monto = monto;
        this.nombre_deudor = nombre_deudor;
        this.nro_deuda = nro_deuda;
    }

    public String getCi_deudor() {
        return ci_deudor;
    }

    public void setCi_deudor(String ci_deudor) {
        this.ci_deudor = ci_deudor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getGlosa_deuda() {
        return glosa_deuda;
    }

    public void setGlosa_deuda(String glosa_deuda) {
        this.glosa_deuda = glosa_deuda;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public String getNombre_deudor() {
        return nombre_deudor;
    }

    public void setNombre_deudor(String nombre_deudor) {
        this.nombre_deudor = nombre_deudor;
    }

    public int getNro_deuda() {
        return nro_deuda;
    }

    public void setNro_deuda(int nro_deuda) {
        this.nro_deuda = nro_deuda;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getNombre_deudor() + " Bs:"+this.getMonto()+" "+this.getEstado();
    }
}
