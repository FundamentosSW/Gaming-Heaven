package Controladores;



import Modelo.Angel;
import Modelo.BaseDatos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import java.io.IOException;
import java.time.LocalDate;

public class RegisterAngelController {
    VistasController controladorVistas = new VistasController();

    @FXML private TextField nombreText;
    @FXML private TextField apellidoText;
    @FXML private TextField correoText;
    @FXML private DatePicker fechaNacimientoPicker;
    @FXML private TextField documentoText;
    @FXML private TextField telefonoText;
    @FXML private TextField nombreUsuarioText;
    @FXML private PasswordField contrasenaText;

    public void registrarUsuario(ActionEvent event) throws IOException {
        String nombre = nombreText.getText();
        String apellido = apellidoText.getText();
        String correo = correoText.getText();
        LocalDate fechaNacimiento = fechaNacimientoPicker.getValue();
        String username = nombreUsuarioText.getText();
        String password = contrasenaText.getText();

        if (username.isEmpty() || password.isEmpty() || correo.isEmpty() || nombre.isEmpty() || apellido.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return;
        }

        double documento = Double.parseDouble(documentoText.getText());
        long telefono = Long.parseLong(telefonoText.getText());

        Angel usuario = new Angel(username, password, 0, "angel", nombre, apellido, correo, fechaNacimiento, documento, telefono);
        BaseDatos.getInstance().registrarUsuarioDB(usuario);

        if (usuario != null) {
            controladorVistas.Login(event);
        }

    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void Login(ActionEvent event) throws IOException {
        controladorVistas.Login(event);
    }
}

