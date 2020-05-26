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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControllerVentanaBusqSimple  implements Initializable {
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



    public static String busqueda;
    public static int i;

    public static HashMap<String, String> resp = App.request("select * from evento");
    public static ArrayList<ArrayList> A = App.stringToArray(resp.get("data"));
    public static ArrayList<String> consulta;

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

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Cbox0.setValue(localDate.minusDays(0));
        Cbox1.setValue("Seleccione la zona que desea:");
        Cbox1.setItems(zonas);
        Cbox2.setValue("Seleccione el tipo de evento que desea:");
        Cbox2.setItems(tipos);
        int size = A.get(1).size();
        Node[] nodes = new Node[size];
            for (i = 0 ; i < size; i++) {
                try {
                    consulta = (ArrayList<String>) A.get(1).get(i);
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
            root = FXMLLoader.load(getClass().getResource("../scenes/VentanaPrincipal.fxml"));
        }else if(node.getId().equals("login")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
        }else if(node.getId().equals("register")){
            root = FXMLLoader.load(getClass().getResource("../scenes/Register.fxml"));
        }else if(node.getId().equals("boton0")){
            System.out.println(Cbox0.getValue());
        }else if(node.getId().equals("boton1")){
            System.out.println(Cbox1.getValue());
        }else if(node.getId().equals("boton2")){
            System.out.println(Cbox2.getValue());
        }
        Scene scene = new Scene(root, Main.stage.getScene().getWidth(), Main.stage.getScene().getHeight());
        Main.stage.setScene(scene);
    }

    public void busqueda(MouseEvent event) throws Exception{

        Node node = (Node) event.getSource();
        if(node.getId().equals("boton0")){
            LocalDateTime ldt = LocalDateTime.now().plusDays(0);
            DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.GERMANY);
            String formatter = formmat1.format(ldt);
            System.out.println(formatter);
            String fecha = Cbox0.getValue().toString();
            if (fecha.equals(formatter)) {
                System.out.println("Nada ha cambiado");
                return;
            }
            System.out.println("La fecha es: " + fecha);
            busqueda = "select * from evento where fecha like '"+fecha+"%'";
            System.out.println(busqueda);
        }else if(node.getId().equals("boton1")){
            String zona = Cbox1.getValue().toString();
            if (zona.equals("Seleccione la zona que desea:")) {
                System.out.println("Nada ha cambiado");
                return;
            }
            System.out.println("La zona es: " + zona);
        }else if(node.getId().equals("boton2")){
            String tipo = Cbox2.getValue().toString();
            if (tipo.equals("Seleccione el tipo de evento que desea:")) {
                System.out.println("Nada ha cambiado");
                return;
            }
            System.out.println("El tipo de evento es: " + tipo);
        }
    }

}

