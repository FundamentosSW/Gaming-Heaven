package com.example.gameheaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PruebaConexion {

    public static void main(String[] args) {
        // URL de conexión a MySQL
        String url = "jdbc:mysql://localhost:3306/gamingheaven";
        String usuario = "root"; // Reemplaza con tu usuario de MySQL
        String contraseña = "22010310"; // Reemplaza con tu contraseña de MySQL

        // Intentar cargar el controlador y conectarse a la base de datos
        try {
            // Cargar el controlador de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer conexión
            Connection conexion = DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexión exitosa a la base de datos.");

            // Cerrar la conexión
            conexion.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se pudo cargar el controlador JDBC.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }
    }
}
