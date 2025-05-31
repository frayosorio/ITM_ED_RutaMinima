package servicios;

public class NodoResultado {
    private final String nombre;
    private final double distanciaAcumulada;

    public NodoResultado(String nombre, double distanciaAcumulada) {
        this.nombre = nombre;
        this.distanciaAcumulada = distanciaAcumulada;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDistanciaAcumulada() {
        return distanciaAcumulada;
    }

}
