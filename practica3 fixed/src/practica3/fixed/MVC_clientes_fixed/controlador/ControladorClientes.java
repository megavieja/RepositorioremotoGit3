package controlador;

import java.time.format.DateTimeFormatter;
import modelo.Cliente;
import vista.PanelNuevo;
import vista.PanelVisualizar;
import vista.VentanaPrincipal;

/** CONTROLADOR: conecta la vista con la lista de clientes. */
public class ControladorClientes {
    private static final DateTimeFormatter FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final ListaClientes lista;
    private final VentanaPrincipal ventana;
    private final PanelVisualizar pVer;
    private final PanelNuevo pNuevo;

    public ControladorClientes(ListaClientes lista, VentanaPrincipal ventana) {
        this.lista = lista;
        this.ventana = ventana;
        this.pVer = ventana.getPanelVisualizar();
        this.pNuevo = ventana.getPanelNuevo();

        // ----- Menu -----
        ventana.addVisualizarListener(e -> {
            refrescar();
            ventana.mostrarPanel(VentanaPrincipal.PANEL_VISUALIZAR);
        });
        ventana.addNuevoListener(e -> {
            pNuevo.limpiar();
            ventana.mostrarPanel(VentanaPrincipal.PANEL_NUEVO);
        });
        ventana.addSalirListener(e -> System.exit(0));

        // ----- Panel Visualizar -----
        pVer.addPrimeroListener(e -> { lista.irAlPrimero(); refrescar(); });
        pVer.addAnteriorListener(e -> { lista.retroceder(); refrescar(); });
        pVer.addSiguienteListener(e -> { lista.avanzar(); refrescar(); });
        pVer.addUltimoListener(e -> { lista.irAlUltimo(); refrescar(); });
        pVer.addBorrarListener(e -> borrarActual());

        // ----- Panel Nuevo -----
        pNuevo.addGuardarListener(e -> guardarNuevo());

        refrescar();   // pinta el primer cliente al arrancar
    }

    /** Vuelca el cliente actual en la vista y activa/desactiva botones. */
    private void refrescar() {
        if (lista.esVacia()) {
            pVer.mostrarVacio();
            pVer.setNavegacion(false, false, false);
            return;
        }
        Cliente c = lista.getClienteActual();
        pVer.mostrarCliente(
                String.valueOf(c.getNumero()),
                c.getNombre(),
                String.valueOf(c.getPuntos()),
                c.getFechaNacimiento().format(FECHA),
                lista.getActual() + 1,
                lista.size());
        pVer.setNavegacion(!lista.esPrimero(), !lista.esUltimo(), true);
    }

    private void borrarActual() {
        Cliente c = lista.getClienteActual();
        if (c != null && pVer.confirmarBorrado(c.getNombre())) {
            lista.borrarCliente();
            refrescar();
        }
    }

    private void guardarNuevo() {
        String error = ControlErrores.validarTodo(pNuevo.getNumero(), pNuevo.getNombre(),
                pNuevo.getPuntos(), pNuevo.getFecha(), lista);

        if (error != null) {                 // hay error -> JLabel rojo y no se guarda nada
            pNuevo.mostrarError(error);
            return;
        }

        Cliente nuevo = new Cliente(
                Integer.parseInt(pNuevo.getNumero().trim()),
                pNuevo.getNombre().trim(),
                Float.parseFloat(pNuevo.getPuntos().trim().replace(',', '.')),
                ControlErrores.convertirFecha(pNuevo.getFecha()));

        lista.anadirCliente(nuevo);
        pNuevo.limpiar();
        pNuevo.mostrarOk("Cliente guardado correctamente.");
    }
}
