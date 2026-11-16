package controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * CONTROLADOR: clase ESTATICA para validar lo que escribe el usuario.
 * Cada metodo devuelve null si todo esta bien, o el texto del error.
 * (La vista pintara ese texto en un JLabel rojo.)
 */
public final class ControlErrores {
    // "uuuu" + STRICT => rechaza fechas inexistentes como 31/02/2000
    private static final DateTimeFormatter FORMATO =
            DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private ControlErrores() { }   // no se puede instanciar

    public static String validarNumero(String texto, ListaClientes lista) {
        try {
            int n = Integer.parseInt(texto.trim());
            if (n <= 0) return "El numero de cliente debe ser mayor que 0.";
            if (lista.existeNumero(n)) return "Ya existe un cliente con el numero " + n + ".";
            return null;
        } catch (NumberFormatException e) {
            return "El numero de cliente debe ser un entero.";
        }
    }

    public static String validarNombre(String texto) {
        if (texto == null || texto.isBlank()) return "El nombre no puede estar vacio.";
        return null;
    }

    public static String validarPuntos(String texto) {
        try {
            float p = Float.parseFloat(texto.trim().replace(',', '.'));
            if (p < 0 || p > 1000) return "Los puntos deben estar entre 0 y 1000.";
            return null;
        } catch (NumberFormatException e) {
            return "Los puntos deben ser un numero valido.";
        }
    }

    public static String validarFecha(String texto) {
        try {
            LocalDate f = LocalDate.parse(texto.trim(), FORMATO);
            if (f.getYear() < 1900 || f.getYear() > 2015)
                return "El año de nacimiento debe estar entre 1900 y 2015.";
            return null;
        } catch (Exception e) {
            return "Fecha incorrecta. Use el formato dd/mm/aaaa (ej. 25/12/1990).";
        }
    }

    /** Convierte un texto ya validado en LocalDate. */
    public static LocalDate convertirFecha(String texto) {
        return LocalDate.parse(texto.trim(), FORMATO);
    }

    /** Pasa por todas las validaciones; devuelve el primer error o null. */
    public static String validarTodo(String numero, String nombre, String puntos, String fecha,
                                     ListaClientes lista) {
        String error = validarNumero(numero, lista);
        if (error == null) error = validarNombre(nombre);
        if (error == null) error = validarPuntos(puntos);
        if (error == null) error = validarFecha(fecha);
        return error;
    }
}
