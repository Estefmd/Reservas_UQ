package co.edu.uniquindio.reservasuq.controladores;


import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.time.LocalDate;
import java.util.List;


public class ControladorPrincipal implements ServiciosReservasUQ {


    private static ControladorPrincipal INSTANCIA;
    private final ReservasUq reservasUq;


    private ControladorPrincipal() {
        reservasUq = new ReservasUq();
    }


    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }


    @Override
    public Usuario login(String correo, String contrasena) throws Exception {
        return reservasUq.login(correo, contrasena);
    }

    @Override
    public void registrarUsuario(String cedula, String nombre, String correo, TipoUsuario tipoUsuario, String contrasenia) throws Exception {
       reservasUq.registrarUsuario(cedula,nombre,correo,tipoUsuario,contrasenia);
    }

    @Override
    public Usuario buscarUsuario(String cedula) throws Exception {
        return reservasUq.buscarUsuario(cedula);
    }


    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {
        reservasUq.crearInstalacion(nombre, aforo, costo, horarios);
    }


    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return reservasUq.crearReserva(idInstalacion, cedulaPersona, diaReserva, horaReserva);
    }




    @Override
    public void cancelarReserva(String idReserva) throws Exception {
       // return reservasUq.cancelarReserva(idReserva);
    }



    @Override
    public List<Reserva> listarTodasReservas() {
        return reservasUq.listarTodasReservas();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return reservasUq.listarReservasPorPersona(cedulaPersona);
    }


    public void navegarVentana(String nombreArchivoFxml, String tituloVentana) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            // Crear la escena
            Scene scene = new Scene(root);


            // Crear un nuevo escenario (ventana)
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle(tituloVentana);


            // Mostrar la nueva ventana
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cerrarVentana(Node node) {
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public void cerrarSesion(){
        Sesion.getInstancia().cerrarSesion();
    }
}
