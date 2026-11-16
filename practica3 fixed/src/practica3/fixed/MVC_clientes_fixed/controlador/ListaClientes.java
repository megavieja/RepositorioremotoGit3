package controlador;

import java.util.ArrayList;
import java.util.Comparator;
import modelo.Cliente;

/**
 * CONTROLADOR: lista de clientes (hereda de ArrayList) que ademas recuerda
 * cual es el cliente "actual" mediante un indice.
 */
public class ListaClientes extends ArrayList<Cliente> {
    private int actual = 0;   // indice del cliente que se esta mostrando

    public ListaClientes() {
        super();
    }

    // ---- Estado de la lista ----
    // OJO: isEmpty() ya existe en ArrayList, asi que "esVacia" solo lo envuelve
    public boolean esVacia() { return isEmpty(); }
    public boolean esPrimero() { return actual == 0; }
    public boolean esUltimo() { return actual == size() - 1; }

    // ---- Navegacion ----
    public void avanzar() { if (!esVacia() && !esUltimo()) actual++; }
    public void retroceder() { if (!esVacia() && !esPrimero()) actual--; }
    public void irAlPrimero() { actual = 0; }
    public void irAlUltimo() { actual = Math.max(0, size() - 1); }

    // ---- Operaciones sobre los datos ----
    /** Añade, reordena por numero y deja como actual al cliente recien creado. */
    public void anadirCliente(Cliente c) {
        add(c);
        ordenarPorNumero();
        actual = indexOf(c);
    }

    /** Borra el cliente actual y recoloca el indice para que no se salga de la lista. */
    public void borrarCliente() {
        if (esVacia()) return;
        remove(actual);
        if (actual >= size()) actual = Math.max(0, size() - 1);
    }

    public Cliente getClienteActual() {
        return esVacia() ? null : get(actual);
    }

    public void ordenarPorNumero() {
        sort(Comparator.comparingInt(Cliente::getNumero));
    }

    // ---- Extras ----
    public int getActual() { return actual; }

    public boolean existeNumero(int numero) {
        for (Cliente c : this)
            if (c.getNumero() == numero) return true;
        return false;
    }
}
