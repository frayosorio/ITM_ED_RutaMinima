import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Resultado {

    private List<Nodo> nodos;

    public Resultado() {
        nodos = new ArrayList<>();
    }

    public List<Nodo> getNodos() {
        return nodos;
    }

    public void agregar(Nodo n) {
        nodos.add(n);
    }

    public void mostrar(JTable tbl) {
        String[] encabezados = new String[] { "Ciudad", "Distancia" };
        String[][] datos = new String[nodos.size()][2];

        int fila = 0;
        for (Nodo n : nodos) {
            datos[fila][0] = n.getNombre();
            datos[fila][1] = String.valueOf(n.getValor());
            fila++;
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }

}
