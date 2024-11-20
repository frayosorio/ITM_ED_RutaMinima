public class Nodo {

    private String nombre;

    public Nodo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object n) {
        return nombre.equals(((Nodo) n).getNombre());
    }

}
