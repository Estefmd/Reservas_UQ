package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistroControlador {

    @FXML
    private TextField txtCedula;

    @FXML
    private TextField txtnombre;

    @FXML
    private TextField txtCorreo;

    @FXML
    private ComboBox<TipoUsuario> cbTipoUsuario = new ComboBox<>();

    @FXML
    private PasswordField pfContrasenia;






}
