package rutaminima;

import java.awt.event.ItemEvent;
import java.util.Stack;

public class AlgoritmosGrafo {

    private static Resultado dijkstra(Grafo g, int inicio) {
        Resultado r = new Resultado();

        Cola cola = new Cola();

        cola.encolar(g.getNodos().get(inicio), 0);
        while (!cola.estaVacia()) {
            Nodo n = (Nodo) cola.desencolar();
            r.agregar(n);
            
            int pn = 0;
            for (Nodo nv : n.getVecinos()) {
                if (!r.getNodos().contains(nv)) {
                    double valor = n.getValor() + n.getAristas().get(pn).getValor();
                    if (!cola.contiene(nv)) {
                        nv.setValor(valor);
                        nv.setPadre(n);
                        cola.encolar(nv, valor);
                    } else {
                        if (nv.getValor() > valor) {
                            nv.setValor(valor);
                            nv.setPadre(n);
                            cola.eliminar(nv);
                            cola.encolar(nv, valor);
                        }
                    }
                }
                pn++;
            }
        }

        return r;
    }

    public static Resultado dijkstra(Grafo g, int inicio, int fin) {
        Resultado r = dijkstra(g, inicio);
        Nodo nd = g.getNodos().get(fin);
        if (r.getNodos().contains(nd)) {
            Stack pila = new Stack();
            while (nd != null) {
                pila.push(nd);
                nd = nd.getPadre();
            }

            r = new Resultado();
            while (!pila.isEmpty()) {
                r.agregar((Nodo) pila.pop());
            }
            return r;
        } else {
            return null;
        }
    }

}
