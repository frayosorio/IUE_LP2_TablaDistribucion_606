import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmTablaDistribucion extends JFrame {

    JComboBox cmbRespuesta;
    JList lstMuestra;
    JTable tblTablaDistribucion;
    String[] opciones = new String[] { "Excelente", "Buena", "Regular", "Mala" };
    String[] encabezados = new String[] { "Variable", "Frecuencia absoluta (f)", "Frecuencia acumulada (F)",
            "Frecuencia relativa (fr)", "Frecuencia porcentual (%f)" };

    public FrmTablaDistribucion() {
        setSize(600, 500);
        setTitle("Tabla deDistribución");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JTextArea txtPregunta = new JTextArea(
                "¿Cómo considera la calidad de la señal de internet que entra al barrio? ");
        txtPregunta.setBounds(10, 10, 250, 50);
        txtPregunta.setEditable(false);
        txtPregunta.setOpaque(false);
        txtPregunta.setLineWrap(true);
        getContentPane().add(txtPregunta);

        cmbRespuesta = new JComboBox();
        DefaultComboBoxModel mdlRespuesta = new DefaultComboBoxModel(opciones);
        cmbRespuesta.setModel(mdlRespuesta);
        cmbRespuesta.setBounds(10, 60, 100, 25);
        getContentPane().add(cmbRespuesta);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(80, 90, 100, 25);
        getContentPane().add(btnAgregar);

        JButton btnQuitar = new JButton("Quitar");
        btnQuitar.setBounds(80, 120, 100, 25);
        getContentPane().add(btnQuitar);

        lstMuestra = new JList();
        JScrollPane spMuestra = new JScrollPane(lstMuestra);
        spMuestra.setBounds(210, 50, 100, 150);
        getContentPane().add(spMuestra);

        JButton btnTablaDistribucion = new JButton("Calcular");
        btnTablaDistribucion.setBounds(10, 200, 100, 25);
        getContentPane().add(btnTablaDistribucion);

        tblTablaDistribucion = new JTable();
        JScrollPane spTablaDistribucion = new JScrollPane(tblTablaDistribucion);
        spTablaDistribucion.setBounds(10, 230, 550, 150);
        getContentPane().add(spTablaDistribucion);

        DefaultTableModel dtm = new DefaultTableModel(null, encabezados);
        tblTablaDistribucion.setModel(dtm);

        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                agregarDato();
            }
        });

        btnQuitar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                quitarDato();
            }
        });

        btnTablaDistribucion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                calcularTablaDistribucion();
            }
        });
    }

    // declarar el arreglo que almacenará los datos de la muestra
    private String[] muestra = new String[1000];
    private int totalDatos = -1;

    private void agregarDato() {
        String respuesta = opciones[cmbRespuesta.getSelectedIndex()];
        totalDatos++;
        muestra[totalDatos] = respuesta;
        mostrarMuestra();
    }

    private void mostrarMuestra() {
        String[] strMuestra = new String[totalDatos + 1];
        for (int i = 0; i <= totalDatos; i++) {
            strMuestra[i] = muestra[i];
        }
        lstMuestra.setListData(strMuestra);
    }

    private void quitarDato() {
        // obtener la posicion escogida
        int posicion = lstMuestra.getSelectedIndex();
        if (posicion >= 0) {
            // retirar la posicion del vector
            for (int i = posicion; i < totalDatos; i++) {
                muestra[i] = muestra[i + 1];
            }
            totalDatos--;
            mostrarMuestra();
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una posición");
        }
    }
 

    private void calcularTablaDistribucion() {
        double[][] tablaDistribucion = new double[opciones.length][4];
        //calcular la frecuencia absoluta
        for (int i = 0; i <= totalDatos; i++) {
            // buscar la fila de la variable
            int posicionVariable = -1;
            for (int j = 0; j < opciones.length; j++) {
                if (muestra[i] == opciones[j]) {
                    posicionVariable = j;
                    break;
                }
            }
            tablaDistribucion[posicionVariable][0]++;
        }

        //mostrar la tabla de distribucion
        String[][] strTablaDistribucion=new String[opciones.length][5];
        for(int i=0;i<opciones.length;i++){
            strTablaDistribucion[i][0]=opciones[i];
            strTablaDistribucion[i][1]=String.valueOf(tablaDistribucion[i][0]);
            strTablaDistribucion[i][2]=String.valueOf(tablaDistribucion[i][1]);
        }

        DefaultTableModel dtm = new DefaultTableModel(strTablaDistribucion, encabezados);
        tblTablaDistribucion.setModel(dtm);
    }
}
