package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/** VISTA: formulario para dar de alta un cliente nuevo. */
public class PanelNuevo extends JPanel {
    private final JTextField txtNumero = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPuntos = new JTextField();
    private final JTextField txtFecha = new JTextField();
    private final JLabel lblMensaje = new JLabel(" ", SwingConstants.CENTER);   // errores en ROJO
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnLimpiar = new JButton("Limpiar");

    public PanelNuevo() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel formulario = new JPanel(new GridLayout(4, 2, 8, 8));
        formulario.add(new JLabel("Número:"));                    formulario.add(txtNumero);
        formulario.add(new JLabel("Nombre:"));                    formulario.add(txtNombre);
        formulario.add(new JLabel("Puntos (0-1000):"));           formulario.add(txtPuntos);
        formulario.add(new JLabel("Fecha nac. (dd/mm/aaaa):"));   formulario.add(txtFecha);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        botones.add(btnGuardar);
        botones.add(btnLimpiar);

        JPanel sur = new JPanel(new GridLayout(2, 1));
        sur.add(lblMensaje);
        sur.add(botones);

        add(formulario, BorderLayout.CENTER);
        add(sur, BorderLayout.SOUTH);

        // "Limpiar" es puro aspecto visual, asi que lo resuelve la propia vista
        btnLimpiar.addActionListener(e -> limpiar());
    }

    // ---- Lo que el controlador necesita LEER ----
    public String getNumero() { return txtNumero.getText(); }
    public String getNombre() { return txtNombre.getText(); }
    public String getPuntos() { return txtPuntos.getText(); }
    public String getFecha()  { return txtFecha.getText(); }

    // ---- Mensajes ----
    public void mostrarError(String mensaje) {
        lblMensaje.setForeground(Color.RED);
        lblMensaje.setText(mensaje);
    }

    public void mostrarOk(String mensaje) {
        lblMensaje.setForeground(new Color(0, 130, 0));
        lblMensaje.setText(mensaje);
    }

    public void limpiar() {
        txtNumero.setText("");
        txtNombre.setText("");
        txtPuntos.setText("");
        txtFecha.setText("");
        lblMensaje.setText(" ");
        txtNumero.requestFocusInWindow();
    }

    public void addGuardarListener(ActionListener l) { btnGuardar.addActionListener(l); }
}
