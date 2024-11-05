package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class InstalacionControlador extends VentanaObservable implements Initializable {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCupo;

    @FXML
    private TextField txtPrecio;

    @FXML
    private ListView<Horario> listViewHorario;

    private Observador observador;

    private ControladorPrincipal controladorPrincipal;

    public InstalacionControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Horario> items = FXCollections.observableArrayList(controladorPrincipal.listarHorarios());
        listViewHorario.setItems(items);
        listViewHorario.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    }

    public void crearInstalacion(ActionEvent actionEvent) {
        String nombre = txtNombre.getText();
        int cupo = Integer.parseInt(txtCupo.getText());
        float precio = Float.parseFloat(txtPrecio.getText());
        List<Horario> horarios = listViewHorario.getSelectionModel().getSelectedItems();

        controladorPrincipal.crearInstalacion(nombre, cupo, precio, horarios);
        limpiarCampos();
        observador.notificar();
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
    }

    @Override
    public void setObservador(Observador observador) {
        this.observador = observador;
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtCupo.setText("");
        txtPrecio.setText("");
        listViewHorario.getItems().clear();
    }
}