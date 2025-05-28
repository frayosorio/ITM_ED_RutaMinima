package servicios;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;
    private List<Arista> vecinos;

    public Nodo(String nombre) {
        this.nombre = nombre;
        vecinos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Arista> getVecinos() {
        return vecinos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void agregarArista(Nodo nodo, double valor) {
        vecinos.add(new Arista(nodo, valor));
    }

}
