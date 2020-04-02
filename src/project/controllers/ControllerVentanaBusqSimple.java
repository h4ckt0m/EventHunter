package project.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import project.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVentanaBusqSimple  implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox hbox;
    @FXML
    private ComboBox Cbox1;
    @FXML
    private ComboBox Cbox2;

    ObservableList<String> zonas =
            FXCollections.observableArrayList(
                    "Madrid centro",
                    "Madrid norte",
                    "Madrid sur",
                    "Madrid este",
                    "Madrid oeste"
            );
    ObservableList<String> tipos =
            FXCollections.observableArrayList(
                    "Evento deportivo",
                    "Evento musical",
                    "Evento cultural"
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Cbox1.setValue("Seleccione la zona que desea:");
        Cbox1.setItems(zonas);
        Cbox2.setValue("Seleccione el tipo de evento que desea:");
        Cbox2.setItems(tipos);
    }

    public void goTo(MouseEvent event) throws Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("logo")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Ventana Principal.fxml"));
        }else if(node.getId().equals("login")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
        }else if(node.getId().equals("register")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Register.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }

}

