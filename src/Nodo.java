import java.util.ArrayList;
import java.util.List;

public class Nodo {

    private String nombre;
    private double valor;
    private Nodo padre;
    private List<Nodo> vecinos;
    private List<Double> valores;

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }

    public Nodo(String nombre) {
        this.nombre = nombre;
        vecinos = new ArrayList();
        valores = new ArrayList();
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public List<Nodo> getVecinos() {
        return vecinos;
    }

    public List<Double> getValores() {
        return valores;
    }

    public void agregarVecino(Nodo n, double valor) {
        vecinos.add(n);
        valores.add(valor);
    }

}
