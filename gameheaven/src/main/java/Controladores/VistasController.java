package Controladores;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class VistasController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    public void Login(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/gameheaven/Login.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void RegisterAngel(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/gameheaven/RegisterAngel.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void menuPrincipalView(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(("/com/example/gameheaven/MenuPrincipal.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
