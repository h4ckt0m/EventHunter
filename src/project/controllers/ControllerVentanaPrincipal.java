package project.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVentanaPrincipal implements Initializable{


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void goTo(MouseEvent event) throws Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("login")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
        }else if(node.getId().equals("register")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Register.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }
}
