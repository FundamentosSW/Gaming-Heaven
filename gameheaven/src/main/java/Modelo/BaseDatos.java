package Modelo;
import javafx.scene.control.Alert;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;


public class BaseDatos {
    private static BaseDatos instancia;

    // Constructor privado para evitar instanciación directa
    private BaseDatos() {
    }


    // Método estático para obtener la instancia única de la clase BaseDatos
    public static synchronized BaseDatos getInstance() {
        if (instancia == null) {
            instancia = new BaseDatos();
        }
        return instancia;
    }

    public Angel buscarUsuario(String username) {
        String sql = "SELECT username,password, tipo_usuario from usuarios where username = ? ";//AND password = ?
        Angel usuario = null;
        try {
            Connection conex = DriverManager.getConnection(ConnectionDB.DB_URl, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
            PreparedStatement ps = conex.prepareStatement(sql);
            //Se le asignan los valores a los parametros
            ps.setString(1, username);
            //ps.setString(2, password);
            //Ejecutar consulta
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                usuario = new Angel();
                usuario.setUsername(rs.getString("username"));
                usuario.setPassword(rs.getString("password"));
                usuario.setTipo_usuario(rs.getString("tipo_usuario"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void registrarUsuarioDB(Angel usuario) {
        String sql = "INSERT INTO USUARIOS(username, puntos, tipo_usuario, password) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO INFO_PERSONAL_USUARIOS(nombre, apellido, telefono, correo, fecha_nacimiento, documento, username) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conex = DriverManager.getConnection(ConnectionDB.DB_URl, ConnectionDB.USERNAME, ConnectionDB.PASSWORD);
             PreparedStatement ps = conex.prepareStatement(sql);
             PreparedStatement ps2 = conex.prepareStatement(sql2)) {

            ps.setString(1, usuario.getUsername());
            ps.setDouble(2, usuario.getPoints());
            ps.setString(3, usuario.getTipo_usuario());

            // Hashear la contraseña antes de guardarla
            String hashedPassword = BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt());
            ps.setString(4, hashedPassword);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                // Llenar info_personal_usuarios después de confirmar el usuario
                ps2.setString(1, usuario.getNombre());
                ps2.setString(2, usuario.getApellido());
                ps2.setLong(3, usuario.getTelefono());
                ps2.setString(4, usuario.getCorreo());
                ps2.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));
                ps2.setDouble(6, usuario.getNumdoc());
                ps2.setString(7, usuario.getUsername());
                ps2.executeUpdate();

                mostrarAlerta("Registro de usuario", "Se registró exitosamente", "Ahora elija un tipo de suscripción");
            } else {
                mostrarAlerta("Error", "No se pudo registrar el usuario", "Por favor, intente nuevamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mostrarAlerta("Error", "Problema en la base de datos", "No se pudo completar el registro.");
        }
    }

    // Método para mostrar alertas de JavaFX
    private void mostrarAlerta(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
