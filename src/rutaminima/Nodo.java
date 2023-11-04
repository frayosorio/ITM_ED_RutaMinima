package rutaminima;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;

    private List<Nodo> vecinos;
    private List<Arista> aristas;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.vecinos = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Nodo> getVecinos() {
        return vecinos;
    }

    //Agrega un nodo vecino del actual
    public void agregarVecino(Nodo n, Arista a) {
        this.vecinos.add(n);
        this.aristas.add(a);
    }

    public boolean esVecino(Nodo n) {
        return this.vecinos.contains(n);
    }

}
