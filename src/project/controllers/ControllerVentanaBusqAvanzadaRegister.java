package project.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerVentanaBusqAvanzadaRegister implements Initializable {
    @FXML
    private VBox RegForm;
    double tx = Main.stage.getScene().getWidth()/2;
    double ty = Main.stage.getScene().getHeight()/2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RegForm.setTranslateX(tx);
        RegForm.setTranslateY(-ty);
        RegForm.setScaleX(0);
        RegForm.setScaleY(0);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), RegForm);
        translateTransition.setByX(-tx);
        translateTransition.setByY(ty);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), RegForm);
        scaleTransition.setByX(1);
        scaleTransition.setByY(1);
        scaleTransition.play();

    }

    public void exit(){
        tx = Main.stage.getScene().getWidth()/2;
        ty = Main.stage.getScene().getHeight()/2;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), RegForm);
        translateTransition.setByX(tx);
        translateTransition.setByY(-ty);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), RegForm);
        scaleTransition.setOnFinished(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../scenes/Ventana Principal.fxml"));
                Scene scene = new Scene(root, tx*2, ty*2);
                Main.stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        scaleTransition.setByX(-1);
        scaleTransition.setByY(-1);
        scaleTransition.play();
    }

    public void goTo(MouseEvent event) throws Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("eventhunter")){
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaBusqAvanzada.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }
}