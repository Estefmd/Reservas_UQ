package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.utils.AlertaUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lombok.AllArgsConstructor;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroUsuarioControlador implements Initializable {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private ComboBox<TipoUsuario> cbTipoUsuario;

    @FXML
    private TextField txtContrasenia;

    private ControladorPrincipal controladorPrincipal;

    public RegistroUsuarioControlador() {
        this.controladorPrincipal = ControladorPrincipal.getInstancia();
    }


    public void registrarUsuario() throws Exception {

        String cedula = txtCedula.getText();
        String nombre = txtNombre.getText();
        String correo = txtCorreo.getText();
        TipoUsuario tipoUsuario = cbTipoUsuario.getValue();
        String contrasenia = txtContrasenia.getText();


        try {
            controladorPrincipal.registrarUsuario(cedula, nombre, correo, tipoUsuario, contrasenia);
            AlertaUtil.mostrarAlerta("Usuario registrado con éxito", Alert.AlertType.INFORMATION);
        } catch (Exception ex) {
            AlertaUtil.mostrarAlerta(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbTipoUsuario.getItems().addAll(TipoUsuario.values());
    }
}