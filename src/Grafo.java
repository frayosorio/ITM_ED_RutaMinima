import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JTable;
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

    public Grafo() {
        nodos = new ArrayList<Nodo>();
        aristas = new ArrayList<Arista>();
    }

    public void desdeArchivo(String nombreArchivo) {
        BufferedReader br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                String linea = br.readLine();
                while (linea != null) {
                    String[] datos = linea.split(",");
                    if (datos.length >= 3) {
                        Nodo n1 = new Nodo(datos[0].trim());
                        Nodo n2 = new Nodo(datos[1].trim());
                        if (!nodos.contains(n1)) {
                            nodos.add(n1);
                        }
                        if (!nodos.contains(n2)) {
                            nodos.add(n2);
                        }
                        double valor = Double.parseDouble(datos[2]);
                        aristas.add(new Arista(n1, n2, valor));
                        n1.agregarVecino(n2, valor);
                        n2.agregarVecino(n1, valor);
                    }
                    linea = br.readLine();
                }

            } catch (Exception ex) {

            }
        }
    }

    public void mostrarNodos(JTable tbl) {
        String[] encabezados = new String[] { "Nombre" };
        String[][] datos = new String[nodos.size()][1];

        int fila = 0;
        for (Nodo n : nodos) {
            datos[fila][0] = n.getNombre();
            fila++;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

    public void mostrarNodos(JComboBox cmb) {
        cmb.removeAllItems();
        for (Nodo n : nodos) {
            cmb.addItem(n.getNombre());
        }
    }

    public void mostrarAristas(JTable tbl) {
        String[] encabezados = new String[] { "Origen", "Destino", "Distancia" };
        String[][] datos = new String[aristas.size()][3];

        int fila = 0;
        for (Arista a : aristas) {
            datos[fila][0] = a.getNodo1().getNombre();
            datos[fila][1] = a.getNodo2().getNombre();
            datos[fila][2] = String.valueOf(a.getValor());
            fila++;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

    // ********** METODOS ESTATICOS **********

    public static Resultado dijkstra(Grafo g, int inicio) {
        Resultado r = new Resultado();

        ColaPrioridad cola = new ColaPrioridad();

        cola.encolar(g.getNodos().get(inicio), 0);

        while (!cola.vacia()) {
            Nodo n = (Nodo) ((ElementoCola) cola.desencolar()).elemento;
            r.agregar(n);

            for (int i = 0; i < n.getVecinos().size(); i++) {
                Nodo nVecino = n.getVecinos().get(i);
                if (!r.getNodos().contains(nVecino)) {
                    double valorAcumulado = n.getValor() + n.getValores().get(i);
                    if(!cola.contiene(nVecino)){
                        nVecino.setValor(valorAcumulado);
                        cola.encolar(nVecino, valorAcumulado);
                    }
                    else{
                        if(nVecino.getValor()>valorAcumulado){
                            nVecino.setValor(valorAcumulado);
                            cola.eliminar(nVecino);
                            cola.encolar(nVecino, valorAcumulado);
                        }
                    }
                }
            }

        }

        return r;
    }

}
