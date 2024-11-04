package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class PerfilUsuarioControlador implements Initializable {

    @FXML
    private TableView<Reserva> tablaReservas;

    @FXML
    private TableColumn<Reserva,String> colId;

    @FXML
    private TableColumn<Reserva,String> colFecha;

    @FXML
    private TableColumn<Reserva, String> colInstalacion;

    private ControladorPrincipal controladorPrincipal;

    public PerfilUsuarioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colId.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getId()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFecha().toString()));
        colInstalacion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getInstalacion().toString()));
    }

    public void cerrarSesion(){
        controladorPrincipal.cerrarSesion();
    }
}
