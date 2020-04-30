package project.controllers;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerEvento implements Initializable {

    @FXML
    private Label titulo;
    @FXML
    private Label direccion;
    @FXML
    private Label fecha;
    @FXML
    private Label precio;
    @FXML
    private Button Inscrib;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titulo.setText("Nombre de Evento de ejemplo");
        direccion.setText("Direccion de ejemplo");
        fecha.setText("Apellido de ejemplo");
        precio.setText("Precio de ejemplo");
        Inscrib.setId(String.valueOf(ControllerVentanaBusqSimple.i));

    }
}
