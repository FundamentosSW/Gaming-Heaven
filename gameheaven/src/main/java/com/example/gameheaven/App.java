package com.example.gameheaven;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class App  extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/com/example/gameheaven/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Gaming Heaven - Login");
        stage.setScene(scene);
        stage.show();
    }
}

