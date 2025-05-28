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

import servicios.Grafo;

public class FrmRutaMinima extends JFrame {

    private JTable tblRutaMinima;
    private JComboBox cmbDesde, cmbHasta;
    private JTable tblNodos, tblAristas;

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

        tblNodos = new JTable();

        DefaultTableModel dtm = new DefaultTableModel(null, Grafo.getEncabezadosNodos());
        tblNodos.setModel(dtm);
        JScrollPane spNodos = new JScrollPane(tblNodos);
        pnlNodos.add(spNodos);

        tblAristas = new JTable();

        dtm = new DefaultTableModel(null, Grafo.getEncabezadosAristas());
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

        cmbDesde = new JComboBox();
        cmbDesde.setBounds(120, 10, 200, 25);
        pnlRutaMinima.add(cmbDesde);

        cmbHasta = new JComboBox();
        cmbHasta.setBounds(120, 35, 200, 25);
        pnlRutaMinima.add(cmbHasta);

        tblRutaMinima = new JTable();
        String[] encabezadosRutaMinima = new String[] { "Ciudad", "Distancia" };
        dtm = new DefaultTableModel(null, encabezadosRutaMinima);
        tblRutaMinima.setModel(dtm);
        JScrollPane spRutaMinima = new JScrollPane(tblRutaMinima);
        spRutaMinima.setBounds(10, 70, 400, 300);
        pnlRutaMinima.add(spRutaMinima);

        JButton btnRutaMinima = new JButton("Calcular");
        btnRutaMinima.setBounds(350, 30, 100, 25);
        pnlRutaMinima.add(btnRutaMinima);

        btnRutaMinima.addActionListener(evt -> calcularRutaMinima());

        getContentPane().add(tp);
        cargarDatos();
    }

    private Grafo grafo;

    private void cargarDatos() {
        String nombreArchivo = System.getProperty("user.dir") + "/src/datos/distancias.txt";
        grafo = new Grafo();
        grafo.desdeArchivo(nombreArchivo);

        grafo.mostrarNodos(cmbDesde);
        grafo.mostrarNodos(cmbHasta);

        grafo.mostrarNodos(tblNodos);

        grafo.mostrarAristas(tblAristas);
    }

    private void calcularRutaMinima() {

    }

}
