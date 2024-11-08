package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Instalacion;
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

public class VentanaAdminControlador implements Initializable, Observador {

    @FXML
    private TableView<Instalacion> tablaInstalacion;

    @FXML
    private TableColumn<Instalacion, String> colId;

    @FXML
    private TableColumn<Instalacion, String> colInstalacion;

    @FXML
    private TableColumn<Instalacion, String> colHorarios;

    @FXML
    private TableColumn<Instalacion, String> colCupo;

    @FXML
    private TableColumn<Instalacion, String> colPrecio;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelTipoUsuario;

    private ObservableList<Instalacion> observableList;

    private ControladorPrincipal controladorPrincipal;

    public VentanaAdminControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreInstalacion()));
        colHorarios.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().obtenerFormatoHorarios()));
        colCupo.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getCuposInstalacion())));
        colPrecio.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioInstalacion())));

        observableList = FXCollections.observableArrayList();
        observableList.setAll(new ArrayList<>());
        tablaInstalacion.setItems(observableList);
        actualizarTabla();

        Usuario usuario = Sesion.getInstancia().getUsuario();
        labelNombre.setText(usuario.getNombre());
        labelTipoUsuario.setText(usuario.getTipoUsuario().name());
    }

    @Override
    public void notificar() {
      actualizarTabla();
    }

    public void irVistaInstalacion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/ventanaInstalacion.fxml", "Panel instalación", this);
    }

    public void eliminarInstalacion(ActionEvent actionEvent) {
        Instalacion instalacion = tablaInstalacion.getSelectionModel().getSelectedItem();

        try {
            controladorPrincipal.eliminarInstalacion(instalacion.getId());
            AlertaUtil.mostrarAlerta("La instalación fue eliminada", Alert.AlertType.INFORMATION);
            actualizarTabla();

        } catch (Exception e) {
            AlertaUtil.mostrarAlerta("No fue posible eliminar la instalación", Alert.AlertType.ERROR);
        }
    }

    public void cerrarSesion(ActionEvent actionEvent){
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.cerrarSesion();
    }

    private void actualizarTabla() {
        observableList.setAll(controladorPrincipal.listarInstalaciones());
    }
}
