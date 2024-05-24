package rutaminima;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;
    private List<Nodo> vecinos;
    private List<Arista> aristas;

    public Nodo(String nombre) {
        this.nombre = nombre;
        vecinos = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public List<Nodo> getVecinos() {
        return vecinos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public String getNombre() {
        return nombre;
    }
    
    public void agregarVecino(Nodo n, Arista a){
        vecinos.add(n);
        aristas.add(a);
    }

}
