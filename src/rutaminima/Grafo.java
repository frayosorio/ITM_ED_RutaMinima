package rutaminima;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Grafo {

    private List<Nodo> nodos;
    private List<Arista> aristas;

    public Nodo buscarNodo(String nombre) {
        for (Nodo n : nodos) {
            if (n.getNombre().equals(nombre)) {
                return n;
            }
        }
        return null;
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

    public void desdeArchivo(String nombreArchivo) {
        nodos = new ArrayList<>();
        aristas = new ArrayList<>();

        //abrir el archivo de datos
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                String linea = br.readLine();
                while (linea != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 3) {
                        //obtener el primer nodo de la nueva arista, verificando si ya existe
                        Nodo n1 = buscarNodo(datos[0].trim());
                        if (n1 == null) {
                            n1 = agregarNodo(datos[0].trim());
                        }
                        //obtener el segundo nodo de la nueva arista, verificando si ya existe
                        Nodo n2 = buscarNodo(datos[1].trim());
                        if (n2 == null) {
                            n2 = agregarNodo(datos[1].trim());
                        }
                        //obtener el valor de la arista
                        double valor = 0;
                        try {
                            valor = Double.parseDouble(datos[2].trim());
                        } catch (Exception ex) {
                        }

                        //agregar la nueva arista
                        agregarArista(n1, n2, valor);
                    }

                    linea = br.readLine();
                }
            } catch (IOException ex) {

            }
        }
    }

    public void mostrarNodos(JTable tbl) {
        String[] encabezados = new String[]{"Nombre"};
        String[][] datos = null;
        if (nodos.size() > 0) {
            datos = new String[nodos.size()][1];
            for (int i = 0; i < nodos.size(); i++) {
                datos[i][0] = nodos.get(i).getNombre();
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

    public void mostrarNodos(JComboBox cmb) {
        cmb.removeAllItems();
        for (int i = 0; i < nodos.size(); i++) {
            cmb.addItem(nodos.get(i).getNombre());
        }
    }

    public void mostrarAristas(JTable tbl) {
        String[] encabezados = new String[]{"Origen", "Destino", "Distancia"};
        String[][] datos = null;
        if (aristas.size() > 0) {
            datos = new String[aristas.size()][3];
            for (int i = 0; i < aristas.size(); i++) {
                datos[i][0] = aristas.get(i).getNodo1().getNombre();
                datos[i][1] = aristas.get(i).getNodo2().getNombre();
                datos[i][2] = String.valueOf(aristas.get(i).getValor());
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

}
