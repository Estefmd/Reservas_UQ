package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.Usuario;
import co.edu.uniquindio.reservasuq.utils.AlertaUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class ReservaControaldor implements Initializable {
    @FXML
    private ComboBox<Instalacion> cbInstalacion;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private ComboBox<String> cbHora;

    private ControladorPrincipal controladorPrincipal;

    public ReservaControaldor() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void crearReserva() {

        Instalacion instalacion = cbInstalacion.getValue();
        LocalDate fecha = dpFecha.getValue();
        String hora = cbHora.getValue();

        Usuario usuario = Sesion.getInstancia().getUsuario();
        try {
            controladorPrincipal.crearReserva(instalacion.getId(),usuario.getCedula(),fecha,hora);
        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("ZCSD", Alert.AlertType.ERROR);

        }
    }


    public void cancelarReserva(){

    }








    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //cbInstalacion.getItems().addAll(Instalacion);
    }
}
