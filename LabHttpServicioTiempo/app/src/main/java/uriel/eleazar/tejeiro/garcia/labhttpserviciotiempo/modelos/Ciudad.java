package uriel.eleazar.tejeiro.garcia.labhttpserviciotiempo.modelos;

public class Ciudad {
    private int id;
    private String name;
    private String country;
    private float temperatura;
    private String description;
    private String icono;

    public Ciudad(){}

    public Ciudad(int id, String name, String country, float temperatura, String description, String icono) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.temperatura = temperatura;
        this.description = description;
        this.icono = icono;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        this.temperatura = temperatura;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
