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
import javafx.scene.layout.StackPane;
import project.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVentanaBusqAvanzada implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private StackPane panelsuperior;
    @FXML
    private HBox hboxEH;
    @FXML
    private HBox hboxlog;

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
        }else if(node.getId().equals("eventhunter")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Ventana Principal.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }
}
