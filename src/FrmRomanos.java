import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class FrmRomanos extends JFrame {

    JTextField txtArabigo;
    JTextField txtRomano;

    // metodo constructor
    public FrmRomanos() {
        setSize(300, 200);
        setTitle("Conversión Romanos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblArabigo = new JLabel("Número arábigo");
        lblArabigo.setBounds(10, 10, 100, 25);
        getContentPane().add(lblArabigo);

        txtArabigo = new JTextField();
        txtArabigo.setBounds(110, 10, 100, 25);
        getContentPane().add(txtArabigo);

        JButton btnCalcular = new JButton("Convertir");
        btnCalcular.setBounds(10, 40, 100, 25);
        getContentPane().add(btnCalcular);

        txtRomano = new JTextField();
        txtRomano.setBounds(110, 40, 100, 25);
        txtRomano.setEnabled(false);
        getContentPane().add(txtRomano);

        btnCalcular.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                convertirARomano();
            }

        });
    }

    private void convertirARomano() {
        String[] romanos = new String[] { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] arabigos = new int[] { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        try {
            int arabigo = Integer.parseInt(txtArabigo.getText());
            if (arabigo > 0) {
                String romano = "";
                while (arabigo > 0) {
                    for (int i = 0; i < arabigos.length; i++) {
                        if (arabigo >= arabigos[i]) {
                            romano += romanos[i];
                            arabigo -= arabigos[i];
                            break;
                        }
                    }
                }
                txtRomano.setText(romano);
            } else {
                JOptionPane.showInternalMessageDialog(null, "El número debe ser positivo");
                txtArabigo.setText("");
            }
        } catch (Exception ex) {
            txtArabigo.setText("");
            JOptionPane.showInternalMessageDialog(null, "Por favor digite un número entero");
        }
    }

}
