package rutaminima;

import java.util.Stack;

public class AlgoritmosGrafos {
    
    public static Resultado dijkstra(Grafo g, int inicio) {
        Resultado r = new Resultado();
     
        
        ColaPrioridad cola = new ColaPrioridad();
        
        cola.encolar(g.getNodos().get(inicio), 0);
        
        while (!cola.vacia()) {
            Nodo n = (Nodo) cola.desencolar();
            
            r.agregar(n);
            
            for (int i = 0; i < n.getVecinos().size(); i++) {
                Nodo nv = n.getVecinos().get(i);
                if (!r.getNodos().contains(nv)) {
                    double valor = n.getValor() + n.getAristas().get(i).getValor();
                    if (!cola.contiene(nv)) {
                        nv.setValor(valor);
                        nv.setPadre(n);
                        cola.encolar(nv, nv.getValor());
                    } else {
                        if (nv.getValor() > valor) {
                            nv.setValor(valor);
                            nv.setPadre(n);
                            cola.eliminar(nv);
                            cola.encolar(nv, valor);
                        }
                    }
                }
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
                pila.add(nd);
                nd = nd.getPadre();
            }
            r = new Resultado();
            while (!pila.isEmpty()) {
                r.agregar((Nodo) pila.pop());
            }
        }
        
        return r;
    }
    
}
