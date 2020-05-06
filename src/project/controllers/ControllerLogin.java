package project.controllers;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private VBox logForm;
    double tx = Main.stage.getScene().getWidth()/2;
    double ty = Main.stage.getScene().getHeight()/2;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logForm.setTranslateX(tx);
        logForm.setTranslateY(-ty);
        logForm.setScaleX(0);
        logForm.setScaleY(0);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), logForm);
        translateTransition.setByX(-tx);
        translateTransition.setByY(ty);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), logForm);
        scaleTransition.setByX(1);
        scaleTransition.setByY(1);
        scaleTransition.play();

    }

    public void exit(){
        tx = Main.stage.getScene().getWidth()/2;
        ty = Main.stage.getScene().getHeight()/2;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), logForm);
        translateTransition.setByX(tx);
        translateTransition.setByY(-ty);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), logForm);
        scaleTransition.setOnFinished(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../scenes/VentanaPrincipal.fxml"));
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
    public void login(){
        exit();
    }
}
