package rutaminima;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Grafo {

    private List<Nodo> nodos;
    private List<Arista> aristas;

    public List<Nodo> getNodos() {
        return nodos;
    }

    public List<Arista> getAristas() {
        return aristas;
    }

    public Nodo agregarNodo(String nombre) {
        Nodo n = new Nodo(nombre);
        nodos.add(n);
        return n;
    }

    public void agregarArista(Nodo n1, Nodo n2, double valor) {
        Arista a = new Arista(n1, n2, valor);
        aristas.add(a);
        n1.agregarVecino(n2, a);
        n2.agregarVecino(n1, a);
    }

    private Nodo buscarNodo(String nombre) {
        for (Nodo n : nodos) {
            if (n.getNombre().equals(nombre)) {
                return n;
            }
        }
        return null;
    }

    public void desdeArchivo(String nombreArchivo) {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                String linea = br.readLine();
                while (linea != null) {
                    String[] textos = linea.split(",");
                    if (textos.length >= 3) {
                        Nodo n1 = buscarNodo(textos[0].trim());
                        n1 = n1 == null ? agregarNodo(textos[0].trim()) : n1;
                        Nodo n2 = buscarNodo(textos[1].trim());
                        n2 = n2 == null ? agregarNodo(textos[1].trim()) : n2;
                        double valor = 0;
                        try {
                            valor = Double.parseDouble(textos[2].trim());
                        } catch (Exception ex) {
                        }
                        agregarArista(n1, n2, valor);
                    }
                    linea = br.readLine();
                }

            } catch (Exception ex) {

            }
        }
    }

    public void mostrarAristas(JTable tbl) {
        String[][] datos = null;
        if (aristas != null && aristas.size() > 0) {
            datos = new String[aristas.size()][3];

            for (int i = 0; i < aristas.size(); i++) {
                datos[i][0] = aristas.get(i).getNodo1().getNombre();
                datos[i][1] = aristas.get(i).getNodo2().getNombre();
                datos[i][2] = String.valueOf(aristas.get(i).getValor());
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, new String[]{"Nodo 1", "Nodo 2", "Distancia"});
        tbl.setModel(dtm);
    }
    
    public void mostrarNodos(JTable tbl){
          String[][] datos = null;
        if (nodos != null && nodos.size() > 0) {
            datos = new String[nodos.size()][2];

            for (int i = 0; i < nodos.size(); i++) {
                datos[i][0] = nodos.get(i).getNombre();
                datos[i][1] = nodos.get(i).getVecinos().stream()
                                .map(Nodo::getNombre)
                                .collect(Collectors.joining(", "));
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, new String[]{"Nombre", "Vecinos"});
        tbl.setModel(dtm);      
    }

}
