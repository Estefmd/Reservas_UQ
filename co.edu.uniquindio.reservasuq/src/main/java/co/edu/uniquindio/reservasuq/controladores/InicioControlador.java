package co.edu.uniquindio.reservasuq.controladores;


import javafx.event.ActionEvent;


public class InicioControlador {


    private final ControladorPrincipal controladorPrincipal;


    public InicioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void irIniciarSesion(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/ventanaLogin.fxml","Iniciar Sesión");
    }


    public void irRegistroCliente(ActionEvent actionEvent) {
        controladorPrincipal.navegarVentana("/ventanaRegistro.fxml", "Registro Persona");
    }
}