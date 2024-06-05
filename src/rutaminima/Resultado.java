package rutaminima;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Resultado {

    private List<Nodo> nodos;

    public Resultado() {
        nodos = new ArrayList<>();
    }

    public void agregar(Nodo n) {
        nodos.add(n);
    }

    public List<Nodo> getNodos() {
        return nodos;
    }
    public void mostrarNodos(JTable tbl) {
        String[][] datos = null;
        if (nodos != null && nodos.size() > 0) {
            datos = new String[nodos.size()][2];

            for (int i = 0; i < nodos.size(); i++) {
                datos[i][0] = nodos.get(i).getNombre();
                datos[i][1] = String.valueOf(nodos.get(i).getValor());
            }
        }
        DefaultTableModel dtm = new DefaultTableModel(datos, new String[]{"Nombre", "Valor"});
        tbl.setModel(dtm);
    }
    
    
}
