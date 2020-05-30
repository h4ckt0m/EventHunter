package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Application {
    public static Stage stage;
    public static ArrayList<ArrayList> loggeduser;
    public static String nombusq;
    public static String estoylogueado = "no";

    public static HashMap<String, String> resp = App.request("select * from evento");
    public static ArrayList<ArrayList> A = App.stringToArray(resp.get("data"));
    public static ArrayList<String> consulta;


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("scenes/VentanaPrincipal.fxml"));
        stage.setTitle("Event Hunter");
        stage.setScene(new Scene(root));
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
