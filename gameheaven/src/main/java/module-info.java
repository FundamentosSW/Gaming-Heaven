module com.example.gameheaven {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jbcrypt;


    opens com.example.gameheaven to javafx.fxml;
    opens Controladores to javafx.fxml;
    exports com.example.gameheaven;
    exports Controladores;
    opens Modelo to javafx.fxml;
    exports Modelo;
}