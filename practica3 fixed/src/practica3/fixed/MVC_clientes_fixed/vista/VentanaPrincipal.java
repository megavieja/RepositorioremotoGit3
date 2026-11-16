package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * VISTA: ventana principal (JFrame) de tamaño fijo con un menu.
 * Los paneles se intercambian con un CardLayout.
 */
public class VentanaPrincipal extends JFrame {
    public static final String PANEL_VISUALIZAR = "visualizar";
    public static final String PANEL_NUEVO = "nuevo";

    private final CardLayout cartas = new CardLayout();
    private final JPanel contenedor = new JPanel(cartas);
    private final PanelVisualizar panelVisualizar = new PanelVisualizar();
    private final PanelNuevo panelNuevo = new PanelNuevo();

    private final JMenuItem itemVisualizar = new JMenuItem("Visualizar");
    private final JMenuItem itemNuevo = new JMenuItem("Nuevo");
    private final JMenuItem itemSalir = new JMenuItem("Salir");

    public VentanaPrincipal() {
        super("Gestión de clientes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(520, 300);
        setResizable(false);               // tamaño FIJO
        setLocationRelativeTo(null);       // centrada en pantalla

        // Menu
        JMenu menu = new JMenu("Menú");
        menu.add(itemVisualizar);
        menu.add(itemNuevo);
        menu.addSeparator();
        menu.add(itemSalir);
        JMenuBar barra = new JMenuBar();
        barra.add(menu);
        setJMenuBar(barra);

        // Paneles (cada uno con un nombre para poder invocarlo luego)
        contenedor.add(panelVisualizar, PANEL_VISUALIZAR);
        contenedor.add(panelNuevo, PANEL_NUEVO);
        add(contenedor);
    }

    public void mostrarPanel(String nombre) { cartas.show(contenedor, nombre); }

    public PanelVisualizar getPanelVisualizar() { return panelVisualizar; }
    public PanelNuevo getPanelNuevo() { return panelNuevo; }

    public void addVisualizarListener(ActionListener l) { itemVisualizar.addActionListener(l); }
    public void addNuevoListener(ActionListener l)      { itemNuevo.addActionListener(l); }
    public void addSalirListener(ActionListener l)      { itemSalir.addActionListener(l); }
}
