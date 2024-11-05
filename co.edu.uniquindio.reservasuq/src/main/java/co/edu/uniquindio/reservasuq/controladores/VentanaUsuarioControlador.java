package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Reserva;
import co.edu.uniquindio.reservasuq.modelo.Sesion;
import co.edu.uniquindio.reservasuq.modelo.Usuario;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.utils.AlertaUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaUsuarioControlador  implements Initializable, Observador {

    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva,String> colId;

    @FXML
    private TableColumn<Reserva,String> colFecha;

    @FXML
    private TableColumn<Reserva, String> colInstalacion;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelTipoUsuario;

    private ObservableList observableList;

    private ControladorPrincipal controladorPrincipal;


    public VentanaUsuarioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstalacion().toString()));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaReservas.setItems(observableList);
        actualizarTabla();

        Usuario usuario = Sesion.getInstancia().getUsuario();
        labelNombre.setText(usuario.getNombre());
        labelTipoUsuario.setText(usuario.getTipoUsuario().name());
    }

    @Override
    public void notificar() {
        actualizarTabla();
    }

    public void irVistaReserva(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/ventanaReserva.fxml","Panel reserva", this);
    }

    public void cancelarReserva(ActionEvent actionEvent) {
        Reserva reserva = tablaReservas.getSelectionModel().getSelectedItem();

        try {
            controladorPrincipal.cancelarReserva(reserva.getId());
            actualizarTabla();
            AlertaUtil.mostrarAlerta("La reserva fue cancelada", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("No fue posible cancelar la reserva", Alert.AlertType.ERROR);
        }
    }

    public void cerrarSesion(ActionEvent actionEvent){
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.cerrarSesion();
    }

    private void actualizarTabla() {
        observableList.setAll(controladorPrincipal.listarReservasPorPersona(Sesion.getInstancia().getUsuario().getCedula()));
    }
}
