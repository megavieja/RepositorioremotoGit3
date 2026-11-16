package modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** MODELO: un cliente. No sabe nada de Swing ni de la vista. */
public class Cliente {
    private int numero;
    private String nombre;
    private float puntos;
    private LocalDate fechaNacimiento;

    // Constructor vacio
    public Cliente() { }

    // Constructor completo
    public Cliente(int numero, String nombre, float puntos, LocalDate fechaNacimiento) {
        this.numero = numero;
        this.nombre = nombre;
        this.puntos = puntos;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters
    public int getNumero() { return numero; }
    public String getNombre() { return nombre; }
    public float getPuntos() { return puntos; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    // Setters
    public void setNumero(int numero) { this.numero = numero; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPuntos(float puntos) { this.puntos = puntos; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    @Override
    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Cliente [numero=" + numero + ", nombre=" + nombre + ", puntos=" + puntos
                + ", fechaNacimiento=" + (fechaNacimiento == null ? "-" : fechaNacimiento.format(f)) + "]";
    }
}
