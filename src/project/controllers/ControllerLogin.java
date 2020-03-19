package project.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private VBox logForm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        logForm.setTranslateX(500);
        logForm.setTranslateY(-300);
        logForm.setScaleX(0);
        logForm.setScaleY(0);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), logForm);
        translateTransition.setByX(-500);
        translateTransition.setByY(300);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), logForm);
        scaleTransition.setByY(1);
        scaleTransition.setByX(1);
        scaleTransition.play();
    }

    public void exit(){
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), logForm);
        translateTransition.setByX(500);
        translateTransition.setByY(-300);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), logForm);
        scaleTransition.setOnFinished(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../scenes/Ventana Principal.fxml"));
                Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
                Main.stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        scaleTransition.setByX(-1);
        scaleTransition.setByY(-1);
        scaleTransition.play();
    }
}
