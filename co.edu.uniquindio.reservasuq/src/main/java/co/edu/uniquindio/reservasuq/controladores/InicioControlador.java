package co.edu.uniquindio.reservasuq.controladores;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class InicioControlador {

    private final ControladorPrincipal controladorPrincipal;

    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarVentana("/ventanaLogin.fxml","Iniciar Sesión", null);
    }

    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.cerrarVentana((Node) actionEvent.getSource());
        controladorPrincipal.navegarVentana("/ventanaRegistro.fxml", "Registro Persona", null);
    }
}
