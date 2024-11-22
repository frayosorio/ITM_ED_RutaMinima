import java.util.ArrayList;
import java.util.List;

public class ColaPrioridad {

    private List<ElementoCola> elementos;

    public ColaPrioridad() {
        elementos = new ArrayList<>();
    }

    public boolean vacia() {
        return elementos.size() == 0;
    }

    public Object obtenerPrimero() {
        if (vacia()) {
            return null;
        } else {
            return elementos.get(0);
        }
    }

    public double obtenerPrioridadPrimero() {
        return obtenerPrimero() == null ? -1 : ((ElementoCola) obtenerPrimero()).prioridad;
    }

    public void encolar(Object elemento, double prioridad) {
        int i = 0;
        while (i < elementos.size() && elementos.get(i).prioridad < prioridad) {
            i++;
        }
        elementos.add(i, new ElementoCola(elemento, prioridad));
    }

    public Object desencolar() {
        if (vacia()) {
            return null;
        } else {
            ElementoCola elemento = elementos.get(0);
            elementos.remove(0);
            return elemento;
        }
    }

    public boolean contiene(Object elemento) {
        int i = 0;
        boolean encontrado = false;
        while (i < elementos.size() && !encontrado) {
            if (elementos.get(i).elemento.equals(elemento)) {
                encontrado = true;
            }
            i++;
        }
        return encontrado;
    }

    public void eliminar(Object elemento){
        int i = 0;
        while (i < elementos.size()) {
            if (elementos.get(i).elemento.equals(elemento)) {
                elementos.remove(i);
                return;
            }
            i++;
        }
    }

}
