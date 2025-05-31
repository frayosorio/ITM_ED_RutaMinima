package servicios;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.transform.stax.StAXResult;

public class Grafo {

    private static String[] encabezadosNodos = new String[] { "Ciudad" };
    private static String[] encabezadosAristas = new String[] { "Desde", "Hasta", "Distancia" };
    private Map<String, Nodo> nodos;

    public static String[] getEncabezadosAristas() {
        return encabezadosAristas;
    }

    public Grafo() {
        nodos = new HashMap<>();
    }

    public void desdeArchivo(String nombreArchivo) {
        try {
            Stream<String> lineas = Files.lines(Paths.get(nombreArchivo));
            lineas.map((linea) -> linea.split(","))
                    .forEach(textos -> {
                        agregarArista(textos[0].trim(), textos[1].trim(), Double.parseDouble(textos[2]));
                    });

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public Nodo getNuevoNodo(String nombre) {
        return nodos.computeIfAbsent(nombre, Nodo::new);
    }

    public void agregarArista(String origen, String destino, double valor) {
        Nodo nodoOrigen = getNuevoNodo(origen);
        Nodo nodoDestino = getNuevoNodo(destino);
        nodoOrigen.agregarArista(nodoDestino, valor);
        nodoDestino.agregarArista(nodoOrigen, valor);
    }

    public Collection<Nodo> getNodos() {
        return nodos.values();
    }

    public void mostrarNodos(JComboBox cmb) {
        cmb.removeAllItems();
        for (var nodo : nodos.values()) {
            cmb.addItem(nodo.getNombre());
        }
    }

    public void mostrarNodos(JTable tbl) {
        DefaultTableModel dtm = new DefaultTableModel(null, encabezadosNodos);

        nodos.values().stream()
                .map(Nodo::getNombre)
                .sorted()
                .forEach(nombre -> dtm.addRow(new Object[] { nombre }));

        tbl.setModel(dtm);
    }

    public static String[] getEncabezadosNodos() {
        return encabezadosNodos;
    }

    public void mostrarAristas(JTable tbl) {
        DefaultTableModel dtm = new DefaultTableModel(null, encabezadosAristas);

        nodos.values().stream()
                .<Object[]>flatMap((nodo) -> nodo.getVecinos().stream()
                        .map(arista -> new Object[] { nodo.getNombre(),
                                arista.getDestino().getNombre(),
                                arista.getValor() }))
                .forEach(dtm::addRow);

        tbl.setModel(dtm);
    }

    public Resultado dijkstra(String origen, String destino) {
        Nodo nodoOrigen = nodos.get(origen), nodoDestino = nodos.get(destino);

        System.out.println(origen+" - "+destino);

        Map<Nodo, Double> distancias = new HashMap<>();
        Map<Nodo, Nodo> antecesores = new HashMap<>();

        nodos.values().forEach(nodo -> distancias.put(nodo, Double.POSITIVE_INFINITY));
        distancias.put(nodoOrigen, 0.0);

        PriorityQueue<Nodo> cola = new PriorityQueue<>(Comparator.comparing(distancias::get));
        cola.add(nodoOrigen);

        while (!cola.isEmpty()) {
            var nodoActual = cola.poll();
            if (nodoActual == nodoDestino)
                break;

            for (var arista : nodoActual.getVecinos()) {
                var nodoVecino = arista.getDestino();
                double nuevaDistancia = distancias.get(nodoActual) + arista.getValor();
                if (nuevaDistancia < distancias.get(nodoVecino)) {
                    distancias.put(nodoVecino, nuevaDistancia);
                    antecesores.put(nodoVecino, nodoActual);
                    cola.remove(nodoVecino);
                    cola.add(nodoVecino);
                }

            }
        }

        if (!antecesores.containsKey(nodoDestino) && nodoOrigen != nodoDestino)
            return null;

        Stack<Nodo> pila = new Stack<>();
        for (Nodo nodoActual = nodoDestino; nodoActual != null; nodoActual = antecesores.get(nodoActual)) {
            pila.push(nodoActual);
        }

        List<NodoResultado> nodosResultado = new ArrayList<>();
        while (!pila.isEmpty()) {
            var nodoActual = pila.pop();
            nodosResultado.add(new NodoResultado(nodoActual.getNombre(), distancias.get(nodoActual)));
        }

        return new Resultado(nodosResultado);

    }

}
