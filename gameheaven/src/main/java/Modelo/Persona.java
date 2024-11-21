package Modelo;

import java.time.LocalDate;

public class Persona {
    private String Nombre;
    private String Apellido;
    private String Correo;
    private LocalDate FechaNacimiento;
    private double numdoc;
    private long telefono;

    public Persona(String nombre, String apellido, String correo, LocalDate fechaNacimiento, double numdoc, long telefono) {
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        FechaNacimiento = fechaNacimiento;
        this.numdoc = numdoc;
        this.telefono = telefono;
    }
    public Persona(){

    }

    public String getNombre() {
        return Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public LocalDate getFechaNacimiento() {
        return FechaNacimiento;
    }

    public double getNumdoc() {
        return numdoc;
    }

    public long getTelefono() {
        return telefono;
    }
}
