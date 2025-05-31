package servicios;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Resultado {

    private static String[] ENCABEZADOS_RUTA = new String[] { "Ciudad", "Distancia" };

    private List<NodoResultado> nodos;

    public Resultado(List<NodoResultado> nodos) {
        this.nodos = nodos;
    }

    public List<NodoResultado> getNodos() {
        return nodos;
    }

    public static String[] getEncabezados() {
        return ENCABEZADOS_RUTA;
    }

    public void mostrar(JTable tbl) {
        DefaultTableModel dtm = new DefaultTableModel(ENCABEZADOS_RUTA, 0);

        nodos.stream()
                .map(nodo -> new Object[] {
                        nodo.getNombre(),
                        nodo.getDistanciaAcumulada()
                })
                .forEach(dtm::addRow);
        tbl.setModel(dtm);
    }

}
