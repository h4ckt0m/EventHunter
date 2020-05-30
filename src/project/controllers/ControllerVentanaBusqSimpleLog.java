package project.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import project.App;
import project.Main;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class ControllerVentanaBusqSimpleLog  implements Initializable {
    @FXML
    private VBox items = null;
    @FXML
    private AnchorPane anchorpane;
    @FXML
    private HBox hbox;
    @FXML
    private DatePicker Cbox0;
    @FXML
    private ComboBox Cbox1;
    @FXML
    private ComboBox Cbox2;
    @FXML
    private ScrollPane Spanel;


    public static int i;

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
                    "eventoDeportivo",
                    "eventoMusical",
                    "eventoCultural"
            );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Spanel.setFitToWidth(true);
        System.out.println(Main.nombusq);
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Cbox0.setValue(localDate.minusDays(0));
        Cbox1.setValue("Seleccione la zona que desea:");
        Cbox1.setItems(zonas);
        Cbox2.setValue("Seleccione el tipo de evento que desea:");
        Cbox2.setItems(tipos);
        Main.resp = App.request("select * from evento where nombreevento LIKE '%"+Main.nombusq+"%'");
        Main.A = App.stringToArray(Main.resp.get("data"));
        int size = Main.A.get(1).size();
        Node[] nodes = new Node[size];
            for (i = 0 ; i < size; i++) {
                try {
                    Main.consulta = (ArrayList<String>) Main.A.get(1).get(i);
                    ControllerEvento.i = i;
                    nodes[i] = FXMLLoader.load(getClass().getResource("../scenes/Evento.fxml"));
                    items.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }


    public void goTo(MouseEvent event) throws Exception{
        Parent root = null;
        Node node = (Node) event.getSource();
        if(node.getId().equals("logo")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Logged.fxml"));
        }else if(node.getId().equals("profile")){
            root = FXMLLoader.load(getClass().getResource("../scenes/ScrollProfile.fxml"));
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }

    public void busqueda(MouseEvent event) throws Exception{

        Node node = (Node) event.getSource();
        if(node.getId().equals("boton0")){
            LocalDate fecha = Cbox0.getValue();
            items.getChildren().clear();
            Main.resp = App.request("SELECT * FROM evento WHERE TO_CHAR(fecha,'yyyy-mm-dd') LIKE '"+fecha+"%'");
            Main.A = App.stringToArray(Main.resp.get("data"));
            int size = Main.A.get(1).size();
            System.out.println(size);
            Node[] nodes = new Node[size];
            for (i = 0 ; i < size; i++) {
                try {
                    Main.consulta = (ArrayList<String>) Main.A.get(1).get(i);
                    nodes[i] = FXMLLoader.load(getClass().getResource("../scenes/Evento.fxml"));
                    items.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("La fecha es: " + fecha);
        }else if(node.getId().equals("boton1")){
            String zona = Cbox1.getValue().toString();
            if (zona.equals("Seleccione la zona que desea:")) {
                return;
            }
            items.getChildren().clear();
            Main.resp = App.request("select * from evento where zona = '"+zona+"'");
            Main.A = App.stringToArray(Main.resp.get("data"));
            int size = Main.A.get(1).size();
            Node[] nodes = new Node[size];
            for (i = 0 ; i < size; i++) {
                try {
                    Main.consulta = (ArrayList<String>) Main.A.get(1).get(i);
                    nodes[i] = FXMLLoader.load(getClass().getResource("../scenes/Evento.fxml"));
                    items.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }else if(node.getId().equals("boton2")){
            String tipo = Cbox2.getValue().toString();
            if (tipo.equals("Seleccione el tipo de evento que desea:")) {
                return;
            }
            items.getChildren().clear();
            Main.resp = App.request("select * from evento where genero = '"+tipo+"'");
            Main.A = App.stringToArray(Main.resp.get("data"));
            int size = Main.A.get(1).size();
            Node[] nodes = new Node[size];
            for (i = 0 ; i < size; i++) {
                try {
                    Main.consulta = (ArrayList<String>) Main.A.get(1).get(i);
                    nodes[i] = FXMLLoader.load(getClass().getResource("../scenes/Evento.fxml"));
                    items.getChildren().add(nodes[i]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

