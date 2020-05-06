package project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.Main;


import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogged implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox hbox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void goTo(MouseEvent event) throws  Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("profile")){
            root = FXMLLoader.load(getClass().getResource("../scenes/ScrollProfile.fxml"));
        }else if(node.getId().equals("searchsimple")){
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaBusqSimple.fxml"));
        }else if(node.getId().equals("searchadvance")){
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaBusqAvanzada.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }

}
