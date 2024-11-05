package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import co.edu.uniquindio.reservasuq.utils.AlertaUtil;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.ResourceBundle;

public class ReservaControlador extends VentanaObservable implements Initializable {
    @FXML
    private ComboBox<Instalacion> cbInstalacion;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private ComboBox<Horario> cbHora;

    private Observador observador;

    private ControladorPrincipal controladorPrincipal;

    public ReservaControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void crearReserva(ActionEvent actionEvent) {

        Instalacion instalacion = cbInstalacion.getValue();
        LocalDate fecha = dpFecha.getValue();
        Horario hora = cbHora.getValue();

        Usuario usuario = Sesion.getInstancia().getUsuario();
        try {
            controladorPrincipal.crearReserva(instalacion.getId(), usuario.getCedula(), fecha, hora);
            observador.notificar();
            limpiarCampos();
            AlertaUtil.mostrarAlerta("La reserva fue creada", Alert.AlertType.INFORMATION);
            controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("No fue posible realizar la reserva", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbInstalacion.setItems(FXCollections.observableArrayList(controladorPrincipal.listarInstalaciones()));
        dpFecha.valueProperty().addListener((observable, oldValue, newValue) -> actualizarFechasDisponibles(newValue));
    }

    @Override
    public void setObservador(Observador observador) {
        this.observador = observador;
    }


    private void limpiarCampos() {
        cbInstalacion.setValue(null);
        cbHora.setValue(null);
        dpFecha.setValue(null);
    }

    private void actualizarFechasDisponibles(LocalDate fechaSeleccionada) {
        Instalacion instalacion = cbInstalacion.getValue();

        try {
            if (instalacion != null) {
                List<Horario> horarios = controladorPrincipal.obtenerHorarioDisponiblePorFecha(instalacion.getId(), fechaSeleccionada);
                cbHora.getItems().clear();
                cbHora.getItems().addAll(horarios);
            }

        } catch (Exception e) {
           AlertaUtil.mostrarAlerta("Seleccione una instalación a reservar", Alert.AlertType.ERROR);
        }
    }
}
