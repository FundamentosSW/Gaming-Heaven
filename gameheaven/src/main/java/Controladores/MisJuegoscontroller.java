package Controladores;

import Modelo.BaseDatos;
import Modelo.Juego;
import Modelo.UsuarioActivo;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.util.List;

public class MisJuegoscontroller {

    @FXML
    private GridPane juegosUs;

    VistasController controladorVistas = new VistasController();


    @FXML
    public void initialize() {
        cargarJuegos();
    }

    private void cargarJuegos() {

        if (!UsuarioActivo.getInstance().estaLogueado()) {
            mostrarAlerta("Error", "No hay usuario logueado", "Por favor, inicie sesión.");
            return;
        }
        String username = UsuarioActivo.getInstance().getUsuario().getUsername();
        List<Juego> juegos = BaseDatos.getInstance().cargarJuegosUsuario(username);
        int row = 0, col = 0;

        for (Juego juego : juegos) {
            try {
                File logoFile = new File(juego.getLogo_path());
                if (!logoFile.exists()) {
                    System.err.println("Archivo no encontrado: " + juego.getLogo_path());
                    continue;
                }
                // Crear ImageView para el logo del juego
                ImageView logoView = new ImageView(new Image(new FileInputStream(juego.getLogo_path())));
                logoView.setFitWidth(110);
                logoView.setFitHeight(110);

                // Crear botón de Apertura del juego
                Button openBtn = new Button("Abrir");
                openBtn.setStyle("-fx-background-color: #115d9a; -fx-text-fill: white; -fx-font-weight: bold;");
                openBtn.setOnAction(e -> AbrirJuego(juego.getJuego_path()));

                // Agregar a la GridPane
                juegosUs.add(logoView, col, row);
                juegosUs.add(openBtn, col, row + 1);

                col++;
                if (col > 2) { // Máximo 3 columnas
                    col = 0;
                    row += 2;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void AbrirJuego(String filePath) {
        new Thread(() -> {
            try {
                File archivoJuego = new File(filePath);

                // Verificar que el archivo existe
                if (!archivoJuego.exists()) {
                    Platform.runLater(() -> mostrarAlerta("Error", "Archivo no encontrado", "El archivo del juego no existe: " + archivoJuego.getAbsolutePath()));
                    return;
                }

                // Obtener la ruta absoluta correctamente
                String rutaNormalizada = archivoJuego.getAbsolutePath();

                // Construir el comando para Windows
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    new ProcessBuilder("cmd.exe", "/c", "start", "cmd", "/k", rutaNormalizada).start();
                } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {
                    new ProcessBuilder("open", "-a", "Terminal", rutaNormalizada).start();
                } else {
                    new ProcessBuilder("x-terminal-emulator", "-e", rutaNormalizada).start();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Platform.runLater(() -> mostrarAlerta("Error", "No se pudo abrir el juego", "Por favor, intente nuevamente."));
            }
        }).start(); // Ejecutar en un hilo separado
    }






    private void mostrarAlerta(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }





    public void PerfilView(ActionEvent event) throws IOException {
        controladorVistas.PerfilView(event);
    }

    public void menuPrincipalView(ActionEvent event) throws IOException {
        controladorVistas.menuPrincipalView(event);
    }

    public void LogOut(ActionEvent event) throws IOException {
        UsuarioActivo.getInstance().cerrarSesion();
        controladorVistas.LogOut(event);
    }

}
