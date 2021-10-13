package com.example.centrovetpatitas;

public class Mascota {

    private int id;
    private String Nombre, Especie, Genero, Raza, Edad;
    private Double Peso;
    private String Esterilizado, Vacunado;

    public Mascota(int id, String nombre, String especie, String genero, String raza, String edad, Double peso, String esterilizado, String vacunado) {
        this.id = id;
        Nombre = nombre;
        Especie = especie;
        Genero = genero;
        Raza = raza;
        Edad = edad;
        Peso = peso;
        Esterilizado = esterilizado;
        Vacunado = vacunado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEspecie() {
        return Especie;
    }

    public void setEspecie(String especie) {
        Especie = especie;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public String getRaza() {
        return Raza;
    }

    public void setRaza(String raza) {
        Raza = raza;
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String edad) {
        Edad = edad;
    }

    public Double getPeso() {
        return Peso;
    }

    public void setPeso(Double peso) {
        Peso = peso;
    }

    public String isEsterilizado() {
        return Esterilizado;
    }

    public void setEsterilizado(String esterilizado) {
        Esterilizado = esterilizado;
    }

    public String isVacunado() {
        return Vacunado;
    }

    public void setVacunado(String vacunado) {
        Vacunado = vacunado;
    }
}
