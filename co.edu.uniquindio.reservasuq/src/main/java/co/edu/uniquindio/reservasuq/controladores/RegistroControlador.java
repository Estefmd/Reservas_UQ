package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroControlador implements Initializable {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtnombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private ComboBox<TipoUsuario> cbTipoUsuario;

    @FXML
    private PasswordField pfContrasenia;

    private ControladorPrincipal controladorPrincipal;

    public RegistroControlador(){
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipoUsuario.getItems().addAll(TipoUsuario.values());
    }

    public void crearRegistro(){

        String nombre = txtnombre.getText();

        //controladorPrincipal.registrarPersona(nombre);
    }
}
