package project.controllers.windows;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import project.Main;

public class MessageWindow {
    @FXML
    private Label textinfo;
    @FXML
    private Button acceptbutton;

    public MessageWindow(String info) throws Exception{
        //SET ROOT AND CONTROLLER (THIS CLASS IS THE CONTROLLER)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/project/scenes/windows/MessageWindow.fxml"));
        loader.setController(this);
        Parent root = loader.load();

        //BLOCK MAIN STAGE
        Stage primaryStage = new Stage();
        primaryStage.initModality(Modality.WINDOW_MODAL); //blocks owner window

        primaryStage.initOwner(Main.stage);

        //SHOW MESSAGE WINDOW
        primaryStage.setTitle("Message");
        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();

        //SET LABEL TEXT
        textinfo.setText(info);

    }

    public void close() {
        Stage s = (Stage) textinfo.getScene().getWindow();
        s.close();
    }

    public void pressEnter(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER) {
            close();
        }
    }

}
