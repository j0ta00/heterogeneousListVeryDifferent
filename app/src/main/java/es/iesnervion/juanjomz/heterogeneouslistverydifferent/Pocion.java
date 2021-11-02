package es.iesnervion.juanjomz.heterogeneouslistverydifferent;

public class Pocion{
    private String nombre;
    private int foto;
    private boolean equipado;

    public Pocion(String nombre, int foto, boolean equipado){
        this.nombre=nombre;
        this.foto=foto;
        this.equipado=equipado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public boolean isEquipado() {
        return equipado;
    }

    public void setEquipado(boolean equipado) {
        this.equipado = equipado;
    }

    @Override
    public String toString() {
        return "Pocion{" +
                "nombre='" + nombre + '\'' +
                ", foto=" + foto +
                ", equipado=" + equipado +
                '}';
    }
}
