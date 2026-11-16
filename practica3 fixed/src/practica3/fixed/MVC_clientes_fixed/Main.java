import controlador.ControladorClientes;
import controlador.ListaClientes;
import java.time.LocalDate;
import javax.swing.SwingUtilities;
import modelo.Cliente;
import vista.VentanaPrincipal;

/** Solo ensambla: crea los datos, la vista y el controlador, y los conecta. */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ListaClientes lista = new ListaClientes();
            lista.anadirCliente(new Cliente(1, "Ana López", 120.5f, LocalDate.of(1990, 5, 14)));
            lista.anadirCliente(new Cliente(2, "Luis Pérez", 340f, LocalDate.of(1972, 11, 3)));
            lista.anadirCliente(new Cliente(3, "Marta Ruiz", 15.75f, LocalDate.of(1998, 1, 28)));
            lista.anadirCliente(new Cliente(4, "Pablo Gómez", 980f, LocalDate.of(1985, 7, 9)));
            lista.anadirCliente(new Cliente(5, "Lucía Torres", 500f, LocalDate.of(2005, 3, 21)));
            lista.anadirCliente(new Cliente(6, "JACINTO", 600f, LocalDate.of(2004, 7, 23)));
            lista.irAlPrimero();   // anadirCliente deja el actual en el ultimo añadido

            VentanaPrincipal ventana = new VentanaPrincipal();
            new ControladorClientes(lista, ventana);
            ventana.setVisible(true);
        });
    }
}
