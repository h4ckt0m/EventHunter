package project.controllers;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import project.App;
import project.Main;
import project.controllers.windows.MessageWindow;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class ControllerLogin implements Initializable {
    @FXML
    private VBox logForm;
    @FXML
    public TextField usertext;
    @FXML
    public TextField passtext;
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

    public void login() throws Exception {
        Parent root = null;
        String user = usertext.getText();
        String pass = passtext.getText();
        HashMap<String, String> resp = App.request("select * from usuario where nombreusuario='" + user + "' and contrasena='" + pass + "'");

        if(resp.get("data").equals("[]")){
            //Vaciamos los textfield para el siguiente intento
            usertext.setText("");
            passtext.setText("");
            MessageWindow msg = new MessageWindow("Oye que la has liao");
        }else{
            //Cargamos los datos del usuario en la variable global de loggeduser para poder acceder a ellos desde el ControllerLogged
            Main.loggeduser = App.stringToArray(resp.get("data"));
            root = FXMLLoader.load(getClass().getResource("../scenes/Logged.fxml"));
            Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
            Main.stage.setScene(scene);
        }


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
}
