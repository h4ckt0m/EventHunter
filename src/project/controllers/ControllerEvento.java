package project.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import project.App;
import project.Main;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;


public class ControllerEvento implements Initializable{

    @FXML
    private Label titulo;
    @FXML
    private Label direccion;
    @FXML
    private Label fecha;
    @FXML
    private Label precio;
    @FXML
    private Button inscrib;

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
        titulo.setText(Main.consulta.get(0));
        direccion.setText(Main.consulta.get(1));
        fecha.setText(Main.consulta.get(2));
        precio.setText(Main.consulta.get(5) + "€");
        inscrib.setId(String.valueOf(ControllerVentanaBusqSimple.i));
    }

    public void enrol(MouseEvent event) throws Exception{
        LocalDateTime ldt = LocalDateTime.now().plusDays(0);
        ArrayList<String> temp = (ArrayList<String>) Main.loggeduser.get(1).get(0);
        String nombre = temp.get(0);
        System.out.println("Te usuario "+nombre+" se incribe al evento: ");
        System.out.println("| Nombre de evento: "+titulo.getText()+"| En la direccion: "+direccion.getText()+"| Se celebra: "+fecha.getText()+"| Te apuntaste el: "+ldt);
        /*App.request("Insert into APUNTA (NOMBREUSUARIO,NOMBREEVENTO,DIRECCION,FECHA,FECHAAPUNTADO) " +
                "values ('nomUsu1','Partido Futbol Real Madrid - Barsa','Avda. de Concha Espina, 1'" +
                ",TO_DATE('2020-03-17 09:00:00', 'YYYY-MM-DD HH24:MI:SS'),TO_DATE('2020-03-10 09:00:00'," +
                " 'YYYY-MM-DD HH24:MI:SS'))");
                
         */
    }
}
