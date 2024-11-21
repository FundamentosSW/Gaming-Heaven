package com.example.gameheaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;


public class PruebaInsercionUser {
    public static void main(String[] args) {
        // URL de conexión a MySQL
        String url = "jdbc:mysql://localhost:3306/gamingheaven";
        String usuario = "root"; // Reemplaza con tu usuario de MySQL
        String contraseña = "22010310"; // Reemplaza con tu contraseña de MySQL

        // Datos del usuario para la inserción
        String username = "user123";
        int puntos = 0;
        String tipoUsuario = "angel";
        String password = "pass1234"; // Asegúrate de que cumpla con la longitud permitida de 8 caracteres
        String nombre = "Juan";
        String apellido = "Pérez";
        String correo = "juan.perez@example.com";
        LocalDate fechaNacimiento = LocalDate.of(1990, 1, 1);
        double documento = 12345678;
        long telefono = 5551234;

        // Intentar cargar el controlador y conectarse a la base de datos
        try {
            // Cargar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);

            // Inserción en la tabla 'usuarios'
            String sqlUsuario = "INSERT INTO usuarios(username, puntos, tipo_usuario, password) VALUES (?, ?, ?, ?)";
            PreparedStatement psUsuario = conexion.prepareStatement(sqlUsuario);
            psUsuario.setString(1, username);
            psUsuario.setInt(2, puntos);
            psUsuario.setString(3, tipoUsuario);
            psUsuario.setString(4, password);
            int filasUsuario = psUsuario.executeUpdate();
            System.out.println("Filas insertadas en 'usuarios': " + filasUsuario);

            // Inserción en la tabla 'info_personal_usuarios'
            String sqlInfoPersonal = "INSERT INTO info_personal_usuarios(nombre, apellido, telefono, correo, fecha_nacimiento, documento, username) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psInfoPersonal = conexion.prepareStatement(sqlInfoPersonal);
            psInfoPersonal.setString(1, nombre);
            psInfoPersonal.setString(2, apellido);
            psInfoPersonal.setLong(3, telefono);
            psInfoPersonal.setString(4, correo);
            psInfoPersonal.setDate(5, java.sql.Date.valueOf(fechaNacimiento));
            psInfoPersonal.setDouble(6, documento);
            psInfoPersonal.setString(7, username);
            int filasInfoPersonal = psInfoPersonal.executeUpdate();
            System.out.println("Filas insertadas en 'info_personal_usuarios': " + filasInfoPersonal);

            // Cerrar la conexión
            psUsuario.close();
            psInfoPersonal.close();
            conexion.close();
            System.out.println("Conexión cerrada correctamente.");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar o ejecutar la consulta en la base de datos.");
            e.printStackTrace();
        }
    }
}
