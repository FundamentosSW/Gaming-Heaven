package Controladores;

import Modelo.Angel;
import Modelo.BaseDatos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.PasswordField;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private TextField UsernameText;
    @FXML
    private PasswordField PasswordText;

    VistasController controladorVistas = new VistasController();

    public void RegisterView(ActionEvent event) throws IOException {
        controladorVistas.RegisterAngel(event);
    }

    public void VerificarLogin(ActionEvent event) throws IOException {

        String username = UsernameText.getText();
        String password = PasswordText.getText();

        Angel user = BaseDatos.getInstance().buscarUsuario(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            //System.out.println("usuario="+ user.getUsername()+" "+ user.getTipo_usuario());
            if (user.getTipo_usuario().equalsIgnoreCase("angel")) {
                controladorVistas.menuPrincipalView(event);
            }
        } else {
            // Mostrar alerta en pantalla
            mostrarAlerta("Error", "Usuario o contraseña Incorrectos", "No se pudo verficar sus credenciales.");
        }


    }

    private void mostrarAlerta(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}