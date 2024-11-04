package co.edu.uniquindio.reservasuq.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class InstalacionControlador implements Initializable {
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCupo;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private ComboBox<String> cbHora;

    @FXML
    private TextField txtPrecio;

    private ControladorPrincipal controladorPrincipal;

    public InstalacionControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }







    private String nombreInstalacion;
    private LocalDateTime horariosInstalacion;
    private String cuposInstalacion;
    private double precioInstalacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}