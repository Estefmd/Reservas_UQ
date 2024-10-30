package co.edu.uniquindio.reservasuq.controladores;


import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginControlador {


    @FXML
    private TextField txtCorreo;


    @FXML
    private TextField txtPassword;


    private final ControladorPrincipal controladorPrincipal;


    public LoginControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void login(ActionEvent actionEvent) {

        try {

            String email = txtCorreo.getText();
            String password = txtPassword.getText();


            Usuario usuario = controladorPrincipal.login(email, password);


            if (usuario.getTipoUsuario() != TipoUsuario.ADMIN) {
                //controladorPrincipal.navegarVentana("/panelCliente.fxml", "Panel Usuario");
            } else {
                //controladorPrincipal.navegarVentana("/panelAdmin.fxml", "Panel Administrador");
            }


            //controladorPrincipal.cerrarVentana(txtCorreo);


        } catch (Exception e) {
            //controladorPrincipal.mostrarAlerta(e.getMessage(), "Error", Alert.AlertType.ERROR);
        }


    }

}
