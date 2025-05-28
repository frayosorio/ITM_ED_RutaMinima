package servicios;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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

}
