package rutaminima;

public class Cola {

    class NodoCola {

        Object item;
        double prioridad;
        NodoCola siguiente;
    }

    private NodoCola frente;

    public Cola() {
        frente = null;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public Object getItemFrente() {
        return estaVacia() ? null : frente.item;
    }

    public Object getPrioridadFrente() {
        return estaVacia() ? -1 : frente.prioridad;
    }

    public void encolar(Object item, double prioridad) {
        NodoCola n = new NodoCola();
        if (!estaVacia()) {
            NodoCola apuntador = frente;
            boolean encontrado = false;
            while (apuntador.siguiente != null && !encontrado) {
                if (apuntador.prioridad < prioridad) {
                    encontrado = true;
                } else {
                    apuntador = apuntador.siguiente;
                }
            }
            NodoCola temporal = apuntador.siguiente;
            apuntador.siguiente = n;
            n.siguiente = temporal;
        } else {
            frente = n;
            n.siguiente = null;
        }
        n.item = item;
        n.prioridad = prioridad;
    }

    public Object desencolar() {
        if (estaVacia()) {
            return null;
        } else {
            Object item = frente.item;
            frente = frente.siguiente;
            return item;
        }
    }

    public boolean contiene(Nodo n) {
        NodoCola apuntador = frente;
        while (apuntador != null) {
            if (apuntador.item.equals(n)) {
                return true;
            } else {
                apuntador = apuntador.siguiente;
            }
        }
        return false;
    }

    public void eliminar(Object item) {
        if (item != null && !estaVacia()) {
            boolean encontrado = false;
            NodoCola apuntador = frente;
            NodoCola anterior = null;
            while (apuntador != null && !encontrado) {
                if (apuntador.item.equals(item)) {
                    encontrado = true;
                } else {
                    anterior = apuntador;
                    apuntador = apuntador.siguiente;
                }
            }
            if (encontrado) {
                if (anterior == null) {
                    frente = apuntador.siguiente;
                } else {
                    anterior.siguiente = apuntador.siguiente;
                }
            }
        }
    }

}
