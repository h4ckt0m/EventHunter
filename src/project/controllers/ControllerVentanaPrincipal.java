package project.controllers;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVentanaPrincipal implements Initializable{


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void goToLogin() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }
}
