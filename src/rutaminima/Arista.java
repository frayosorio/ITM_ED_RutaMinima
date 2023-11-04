package rutaminima;

public class Arista {

    private Nodo nodo1, nodo2;
    private double valor;

    public Arista(Nodo nodo1, Nodo nodo2, double valor) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
        this.valor = valor;
    }

    public Nodo getNodo1() {
        return nodo1;
    }

    public Nodo getNodo2() {
        return nodo2;
    }

    public double getValor() {
        return valor;
    }

}
