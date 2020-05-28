package project.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.App;
import project.Main;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerVentanaPrincipal implements Initializable{
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox hbox;
    @FXML
    private TextField nombusq;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        HashMap<String, String> resp = App.request("select * from evento");
        App.stringToArray(resp.get("data"));
        String respuesta = resp.toString();
        System.out.println(respuesta);
    }

    public void goTo(MouseEvent event) throws Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("login")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
        }else if(node.getId().equals("register")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Register.fxml"));
        }else if(node.getId().equals("searchsimple")){
            Main.nombusq = nombusq.getText();
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaBusqSimple.fxml"));
        }else if(node.getId().equals("searchadvance")){
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaBusqAvanzada.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }
}
