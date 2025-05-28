package servicios;

public class Arista {
    private Nodo destino;
    private double valor;

    public Arista(Nodo destino, double valor) {
        this.destino = destino;
        this.valor = valor;
    }

    public Nodo getDestino() {
        return destino;
    }

    public double getValor() {
        return valor;
    }
}
