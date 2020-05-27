package project.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import project.App;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;


public class ControllerEvento extends ControllerVentanaBusqSimple implements Initializable{

    @FXML
    private Label titulo;
    @FXML
    private Label direccion;
    @FXML
    private Label fecha;
    @FXML
    private Label precio;
    @FXML
    public Button inscrib;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*HashMap<String, String> resp = App.request("select NombreEvento from evento WHERE evento.zona = 'zona"+ControllerVentanaBusqSimple.i+"'");
        titulo.setText(App.request("select NombreEvento from evento WHERE evento.zona = 'zona"+ControllerVentanaBusqSimple.i+"'").toString());
        direccion.setText(App.request("select direccion from evento WHERE evento.zona = 'zona"+ControllerVentanaBusqSimple.i+"'").toString());
        fecha.setText(App.request("select fecha from evento WHERE evento.zona = 'zona"+ControllerVentanaBusqSimple.i+"'").toString());
        precio.setText(App.request("select precio from evento WHERE evento.zona = 'zona"+ControllerVentanaBusqSimple.i+"'").toString());
        Inscrib.setId(String.valueOf(ControllerVentanaBusqSimple.i));*/
        //HashMap<String, String> resp = App.request(ControllerVentanaBusqSimple.busqueda);
        //ArrayList<ArrayList> A = App.stringToArray(resp.get("data"));
        //System.out.println(A.get(1).get(0));
        //ArrayList<String> e1 = (ArrayList<String>) A.get(1).get(0);
        titulo.setText(consulta.get(0));
        direccion.setText(consulta.get(1));
        fecha.setText(consulta.get(2));
        precio.setText(consulta.get(5) + "€");
        inscrib.setId(String.valueOf(ControllerVentanaBusqSimple.i));
    }

    public void enrol(MouseEvent event) throws Exception{
        System.out.println("entro1");
        Node node = (Node) event.getSource();
        if(node.getId().equals("inscrib")){
            System.out.println("node.getId()");
        }
    }
}
