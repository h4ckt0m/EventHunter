package project.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import project.App;
import project.Main;
import project.controllers.windows.MessageWindow;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


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

    public static int i;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titulo.setText(Main.consulta.get(0));
        direccion.setText(Main.consulta.get(1));
        fecha.setText(Main.consulta.get(2));
        precio.setText(Main.consulta.get(5) + "€");
        inscrib.setId(String.valueOf(i));
    }

    public void inscribirse(MouseEvent event) throws Exception{
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        Date date = new Date(System.currentTimeMillis());
        if(Main.estoylogueado=="no"){
            MessageWindow msg = new MessageWindow("Primero tines que loguearte...");
        }else if (Main.estoylogueado=="si"){
            String primario = fecha.getText();
            String fecha = primario.substring(0, 10);
            String hora = primario.substring(11, 19);
            ArrayList<String> temp = (ArrayList<String>) Main.loggeduser.get(1).get(0);
            String nombre = temp.get(0);
            HashMap response = App.request("INSERT INTO APUNTA (NOMBREUSUARIO,NOMBREEVENTO,DIRECCION,FECHA,FECHAAPUNTADO) VALUES ('" + nombre + "','" + titulo.getText() + "','" + direccion.getText() + "',TO_DATE('" +
                    fecha + " " + hora + "','YYYY-MM-DD HH24:MI:SS'),TO_DATE('" + (formatter.format(date)).toString() + "', 'YYYY-MM-DD HH24:MI:SS'))");
            System.out.println(response);
            if (response.get("exito").equals("true")) {
                MessageWindow msg = new MessageWindow("Inscrito con éxito.");
            }else{
                MessageWindow msg = new MessageWindow("Ha habido un error al realizar la inscripción.\nComprueba si no estás ya inscrito.");
            }
        }
    }
}