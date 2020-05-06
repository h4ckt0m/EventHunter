package project.controllers;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
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
import javafx.util.Duration;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerScrollProfile implements Initializable {
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox hbox;
    @FXML
    private VBox scrollProf;


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //scrollProf.setTranslateX(200);
        scrollProf.setTranslateY(-150);
        //scrollProf.setScaleX(0);
        scrollProf.setScaleY(0);

        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), scrollProf);
        //translateTransition.setByX(-200);
        translateTransition.setByY(150);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), scrollProf);
        //scaleTransition.setByX(1);
        scaleTransition.setByY(1);
        scaleTransition.play();
    }

    public void exit(){
        double tx = Main.stage.getScene().getWidth()/2;
        double ty = Main.stage.getScene().getHeight()/2;
        TranslateTransition translateTransition = new TranslateTransition(Duration.millis(500), scrollProf);
        translateTransition.setByX(150);
        translateTransition.setByY(-150);
        translateTransition.play();

        ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(500), scrollProf);
        scaleTransition.setOnFinished(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../scenes/Logged.fxml"));
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
    public void prof(){
        exit();
    }

    public void goTo(MouseEvent event) throws  Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("logout")){
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaPrincipal.fxml"));
        }else if(node.getId().equals("myprofile")){
            root = FXMLLoader.load(getClass().getResource("../scenes/MyProfile.fxml"));
        }else if(node.getId().equals("myevents")){
            root = FXMLLoader.load(getClass().getResource("../scenes/MyEvents.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }

}
