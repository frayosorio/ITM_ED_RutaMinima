import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmRutaMinima extends JFrame {

    private Grafo g;

    public FrmRutaMinima() {
        setSize(600, 450);
        setTitle("Ruta Mínima");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // getContentPane().setLayout(null);

        JTabbedPane tp = new JTabbedPane();
        JPanel pnlAristas = new JPanel();
        JPanel pnlNodos = new JPanel();
        JPanel pnlRutaMinima = new JPanel();

        tp.addTab("Nodos", pnlNodos);
        tp.addTab("Aristas", pnlAristas);
        tp.addTab("Ruta Mínima", pnlRutaMinima);

        JTable tblNodos = new JTable();
        String[] encabezadosNodos = new String[] { "Ciudad" };
        DefaultTableModel dtm = new DefaultTableModel(null, encabezadosNodos);
        tblNodos.setModel(dtm);
        JScrollPane spNodos = new JScrollPane(tblNodos);
        pnlNodos.add(spNodos);

        JTable tblAristas = new JTable();
        String[] encabezadosAristas = new String[] { "Desde", "Hasta", "Distancia" };
        dtm = new DefaultTableModel(null, encabezadosAristas);
        tblAristas.setModel(dtm);
        JScrollPane spAristas = new JScrollPane(tblAristas);
        pnlAristas.add(spAristas);

        pnlRutaMinima.setLayout(null);

        JLabel lblDesde = new JLabel("Desde:");
        lblDesde.setBounds(10, 10, 100, 25);
        pnlRutaMinima.add(lblDesde);

        JLabel lblHasta = new JLabel("Hasta:");
        lblHasta.setBounds(10, 35, 100, 25);
        pnlRutaMinima.add(lblHasta);

        JComboBox cmbDesde = new JComboBox();
        cmbDesde.setBounds(120, 10, 200, 25);
        pnlRutaMinima.add(cmbDesde);

        JComboBox cmbHasta = new JComboBox();
        cmbHasta.setBounds(120, 35, 200, 25);
        pnlRutaMinima.add(cmbHasta);

        JTable tblRutaMinima = new JTable();
        String[] encabezadosRutaMinima = new String[] { "Ciudad", "Distancia" };
        dtm = new DefaultTableModel(null, encabezadosRutaMinima);
        tblRutaMinima.setModel(dtm);
        JScrollPane spRutaMinima = new JScrollPane(tblRutaMinima);
        spRutaMinima.setBounds(10, 70, 400, 300);
        pnlRutaMinima.add(spRutaMinima);

        JButton btnRutaMinima = new JButton("Calcular");
        btnRutaMinima.setBounds(350, 30, 100, 25);
        pnlRutaMinima.add(btnRutaMinima);

        btnRutaMinima.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnRutaMinima_Click(evt);
            }
        });

        getContentPane().add(tp);

        g = new Grafo();
        String nombreArchivo = System.getProperty("user.dir") + "/src/datos/distancias.txt";
        g.desdeArchivo(nombreArchivo);
        g.mostrarNodos(tblNodos);
        g.mostrarNodos(cmbDesde);
        g.mostrarNodos(cmbHasta);
        g.mostrarAristas(tblAristas);

    }

    private void btnRutaMinima_Click(ActionEvent evt) {

    }

}
