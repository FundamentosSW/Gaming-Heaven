package Controladores;

import javafx.event.ActionEvent;
import Modelo.BaseDatos;
import Modelo.UsuarioActivo;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;

public class PerfilUsuarioController {


    VistasController controladorVistas = new VistasController();

    @FXML
    private Label lblUsername; // Etiqueta para el nombre de usuario

    @FXML
    private Label lblPoints; // Etiqueta para los puntos del usuario

    @FXML
    private ImageView imgFotoPerfil; // Imagen para mostrar la foto de perfil del usuario

    @FXML
    private Button btnActualizarFoto; // Botón para actualizar la foto de perfil

    @FXML
    public void initialize() {
        cargarDatosUsuario();
        if(UsuarioActivo.getInstance().getUsuario().getTipo_usuario().equals("premium")){
            lblUsername.getStyleClass().clear();
            lblUsername.getStyleClass().add("titulo-premium");
            lblPoints.getStyleClass().clear();
            lblPoints.getStyleClass().add("titulo-premium");
        }

    }

    private void cargarDatosUsuario() {
        if (UsuarioActivo.getInstance().estaLogueado()) {
            var usuario = UsuarioActivo.getInstance().getUsuario();
            lblUsername.setText(usuario.getUsername()); // Establecer el nombre de usuario
            lblPoints.setText(String.valueOf(usuario.getPoints())); // Establecer los puntos

            // Cargar la imagen del perfil si existe
            try {
                if (usuario.getFotoPerfilUrl() != null && !usuario.getFotoPerfilUrl().isEmpty()) {
                    File fotoFile = new File(usuario.getFotoPerfilUrl());
                    if (fotoFile.exists()) {
                        imgFotoPerfil.setImage(new Image(new FileInputStream(fotoFile)));
                    }
                }
            } catch (Exception e) {
                System.err.println("Error cargando la foto de perfil: " + e.getMessage());
            }
        } else {
            mostrarAlerta("Error", "No hay usuario logueado", "Por favor, inicie sesión.");
        }
    }

    @FXML
    private void Actualizar() {
        if (!UsuarioActivo.getInstance().estaLogueado()) {
            mostrarAlerta("Error", "No hay usuario logueado", "Por favor, inicie sesión.");
            return;
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Foto de Perfil");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imágenes", "*.png", "*.jpg", "*.jpeg"));

        File archivoSeleccionado = fileChooser.showOpenDialog(btnActualizarFoto.getScene().getWindow());
        if (archivoSeleccionado != null) {
            try {
                // Ruta de destino para guardar la foto
                String carpetaDestino = "src/main/resources/FotosPerfiles/";
                File directorioDestino = new File(carpetaDestino);
                if (!directorioDestino.exists()) {
                    directorioDestino.mkdirs(); // Crear la carpeta si no existe
                }

                String nuevaRutaFoto = carpetaDestino + archivoSeleccionado.getName();
                File archivoDestino = new File(nuevaRutaFoto);

                // Copiar el archivo seleccionado a la carpeta destino
                try (FileInputStream fis = new FileInputStream(archivoSeleccionado);
                     FileOutputStream fos = new FileOutputStream(archivoDestino)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        fos.write(buffer, 0, length);
                    }
                }

                // Actualizar en la base de datos
                var usuario = UsuarioActivo.getInstance().getUsuario();
                boolean actualizado = BaseDatos.getInstance().actualizarFotoPerfil(usuario.getUsername(), nuevaRutaFoto);
                if (actualizado) {
                    usuario.setFotoPerfilUrl(nuevaRutaFoto); // Actualizar el usuario activo
                    imgFotoPerfil.setImage(new Image(new FileInputStream(archivoDestino))); // Mostrar la nueva foto
                    mostrarAlerta("Éxito", "Foto de perfil actualizada", "La nueva foto de perfil se guardó correctamente.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mostrarAlerta("Error", "No se pudo actualizar la foto de perfil", "Por favor, intente nuevamente.");
            }
        }
    }

    private void mostrarAlerta(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }


    public void MisJuegos(ActionEvent event) throws IOException {
        controladorVistas.MisJuegos(event);
    }

    public void menuPrincipalView(ActionEvent event) throws IOException {
        controladorVistas.menuPrincipalView(event);
    }

    public void LogOut(ActionEvent event) throws IOException {
        UsuarioActivo.getInstance().cerrarSesion();
        controladorVistas.LogOut(event);
    }
    @FXML
    private void ComprarPremium(){
        if(!UsuarioActivo.getInstance().getUsuario().getTipo_usuario().equals("premium")){
            //valor premium 800 puntos
            if(UsuarioActivo.getInstance().getUsuario().getPoints()>=800){
                //ejecutar sentencia que actualiza a usuario, descuenta los puntos y cambia el tipo de usuario a premium
                boolean actualizado= BaseDatos.getInstance().actualizarPuntosUsuario(UsuarioActivo.getInstance().getUsuario().getUsername(), -800);
                if(actualizado){
                    //cambiar estado de usuario a premium
                    boolean prem= BaseDatos.getInstance().hacerUsuarioPremium(UsuarioActivo.getInstance().getUsuario().getUsername());
                    if(prem){
                        mostrarAlerta("Felicitacionesss", "ahora eres un angel premium", "ahora puedes comprar todos los juegos sin gastar tus puntos");
                    }
                }
            }else{
                mostrarAlerta("Lo Lamentamos", "actualmente no cuentas con los puntos necesarios para ser premium", "siempre puedes recargar mas,no esperes mas para ser premium ");
            }


        }else{
            mostrarAlerta("Ya eres premium!!!!!", "actualmente disfrutas de los beneficios premium que ofrecemos", " ");
        }

    }


}
