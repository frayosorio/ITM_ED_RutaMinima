package rutaminima;

import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;

    private List<Nodo> vecinos;
    private List<Arista> aristas;

    //atributos temporales para procesos con los grafos
    private double valor;
    private Nodo padre;
    private boolean activo;

    public Nodo(String nombre) {
        this.nombre = nombre;
        this.vecinos = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public List<Nodo> getVecinos() {
        return vecinos;
    }

    public void activar() {
        activo = true;
    }

    public void desactivar() {
        activo = false;
    }

    public boolean estaActivo() {
        return activo;
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
