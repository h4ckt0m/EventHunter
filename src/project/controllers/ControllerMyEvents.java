package project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import project.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMyEvents implements Initializable{
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private StackPane panelsuperior;
    @FXML
    private GridPane grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i=1;
        for (Node node : grid.getChildren()) {
            if (node instanceof HBox) {
                node.setStyle("-fx-background-image: url(\"/resources/"+i+".png\");-fx-background-size: cover;");
            }
            i++;
        }
    }

    public void goTo(MouseEvent event) throws Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("eventhunter")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Logged.fxml"));
        }else if(node.getId().equals("myprofile")){
            root = FXMLLoader.load(getClass().getResource("../scenes/MyProfile.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }

}
