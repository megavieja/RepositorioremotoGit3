package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * VISTA: panel que muestra UN cliente cada vez.
 * Solo dibuja y expone metodos. No tiene logica ni conoce la clase Cliente.
 */
public class PanelVisualizar extends JPanel {
    private final JLabel lblPosicion = new JLabel("", SwingConstants.CENTER);
    private final JTextField txtNumero = new JTextField();
    private final JTextField txtNombre = new JTextField();
    private final JTextField txtPuntos = new JTextField();
    private final JTextField txtFecha = new JTextField();

    private final JButton btnPrimero = new JButton("|<");
    private final JButton btnAnterior = new JButton("<");
    private final JButton btnSiguiente = new JButton(">");
    private final JButton btnUltimo = new JButton(">|");
    private final JButton btnBorrar = new JButton("Borrar cliente");

    public PanelVisualizar() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Los datos son solo de lectura: se ven, pero no se editan
        for (JTextField t : new JTextField[]{txtNumero, txtNombre, txtPuntos, txtFecha})
            t.setEditable(false);

        JPanel datos = new JPanel(new GridLayout(4, 2, 8, 8));
        datos.add(new JLabel("Número:"));           datos.add(txtNumero);
        datos.add(new JLabel("Nombre:"));           datos.add(txtNombre);
        datos.add(new JLabel("Puntos:"));           datos.add(txtPuntos);
        datos.add(new JLabel("Fecha nacimiento:")); datos.add(txtFecha);

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 5));
        botones.add(btnPrimero);
        botones.add(btnAnterior);
        botones.add(btnSiguiente);
        botones.add(btnUltimo);
        botones.add(btnBorrar);

        add(lblPosicion, BorderLayout.NORTH);
        add(datos, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    /** El controlador le pasa los datos ya convertidos a texto. */
    public void mostrarCliente(String numero, String nombre, String puntos, String fecha,
                               int posicion, int total) {
        lblPosicion.setText("Cliente " + posicion + " de " + total);
        txtNumero.setText(numero);
        txtNombre.setText(nombre);
        txtPuntos.setText(puntos);
        txtFecha.setText(fecha);
    }

    /** Se usa cuando no queda ningun cliente en la lista. */
    public void mostrarVacio() {
        lblPosicion.setText("No hay clientes");
        txtNumero.setText("");
        txtNombre.setText("");
        txtPuntos.setText("");
        txtFecha.setText("");
    }

    /** El controlador decide que botones tienen sentido; la vista solo obedece. */
    public void setNavegacion(boolean puedeRetroceder, boolean puedeAvanzar, boolean hayClientes) {
        btnPrimero.setEnabled(puedeRetroceder);
        btnAnterior.setEnabled(puedeRetroceder);
        btnSiguiente.setEnabled(puedeAvanzar);
        btnUltimo.setEnabled(puedeAvanzar);
        btnBorrar.setEnabled(hayClientes);
    }

    /** Ventana de confirmacion. Devuelve true si el usuario pulsa "Si". */
    public boolean confirmarBorrado(String nombre) {
        int r = JOptionPane.showConfirmDialog(this,
                "¿Seguro que quieres borrar a " + nombre + "?",
                "Confirmar borrado", JOptionPane.YES_NO_OPTION);
        return r == JOptionPane.YES_OPTION;
    }

    // La vista solo avisa de que "se pulso algo"
    public void addPrimeroListener(ActionListener l)   { btnPrimero.addActionListener(l); }
    public void addAnteriorListener(ActionListener l)  { btnAnterior.addActionListener(l); }
    public void addSiguienteListener(ActionListener l) { btnSiguiente.addActionListener(l); }
    public void addUltimoListener(ActionListener l)    { btnUltimo.addActionListener(l); }
    public void addBorrarListener(ActionListener l)    { btnBorrar.addActionListener(l); }
}
