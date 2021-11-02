package es.iesnervion.juanjomz.heterogeneouslistverydifferent;

public class Alimento{
    private String nombre;
    private int cantdad;

    public Alimento(String nombre, int cantdad) {
        this.nombre = nombre;
        this.cantdad = cantdad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantdad() {
        return cantdad;
    }

    public void setCantdad(int cantdad) {
        this.cantdad = cantdad;
    }

    @Override
    public String toString() {
        return "Alimento{" +
                "nombre='" + nombre + '\'' +
                ", cantdad=" + cantdad +
                '}';
    }
}
