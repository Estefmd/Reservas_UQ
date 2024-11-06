package co.edu.uniquindio.reservasuq.controladores;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.observador.Observador;
import co.edu.uniquindio.reservasuq.observador.VentanaObservable;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


public class ControladorPrincipal implements ServiciosReservasUQ {

    private static ControladorPrincipal INSTANCIA;

    private final ReservasUq reservasUq;

    @SneakyThrows
    private ControladorPrincipal() {
        reservasUq = new ReservasUq();

        reservasUq.registrarUsuario("12345", "Stiven", "stiven95rengifo@gmail.com", TipoUsuario.EXTERNO,"123");
        reservasUq.registrarUsuario("1234", "Valentina", "v@gmail.com", TipoUsuario.ADMIN,"123");

        List<Horario> piscinaHorarios = List.of(
                new Horario(LocalTime.of(8,0,0), LocalTime.of(10,0,0)),
                new Horario(LocalTime.of(12,0,0), LocalTime.of(14,0,0)),
                new Horario(LocalTime.of(16,0,0), LocalTime.of(18,0,0)));

        List<Horario> campoHorarios = List.of(
                new Horario(LocalTime.of(15,0,0), LocalTime.of(17,0,0)),
                new Horario(LocalTime.of(17,0,0), LocalTime.of(18,0,0)));

        reservasUq.crearInstalacion("Piscina", 2, 12000, piscinaHorarios);
        reservasUq.crearInstalacion("Campo de futbol", 2, 150000, campoHorarios);

    }

    public static ControladorPrincipal getInstancia() {
        if (INSTANCIA == null) {
            INSTANCIA = new ControladorPrincipal();
        }
        return INSTANCIA;
    }

    @Override
    public Usuario login(String correo, String contrasenia) {
        return reservasUq.login(correo, contrasenia);
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
    public void eliminarInstalacion(String instalacionId) throws Exception {
        reservasUq.eliminarInstalacion(instalacionId);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, Horario horaReserva) throws Exception {
        return reservasUq.crearReserva(idInstalacion, cedulaPersona, diaReserva, horaReserva);
    }


    @Override
    public void cancelarReserva(String reservaId) throws Exception {
        reservasUq.cancelarReserva(reservaId);
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return reservasUq.listarTodasReservas();
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return reservasUq.listarReservasPorPersona(cedulaPersona);
    }

    @Override
    public List<Horario> obtenerHorarioDisponiblePorFecha(String instalacionId, LocalDate fechaSeleccionada) throws Exception {
        return reservasUq.obtenerHorarioDisponiblePorFecha(instalacionId, fechaSeleccionada);
    }

    public List<Instalacion> listarInstalaciones() {
        return reservasUq.getListaInstalaciones();
    }

    public List<Horario> listarHorarios() {
        return reservasUq.crearHorarios();
    }


    public void navegarVentana(String nombreArchivoFxml, String tituloVentana, Observador observador) {
        try {
            // Cargar la vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource(nombreArchivoFxml));
            Parent root = loader.load();

            if (observador != null) {
                VentanaObservable ventanaObservable = loader.getController();
                ventanaObservable.setObservador(observador);
            }

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
        navegarVentana("/ventanaLogin.fxml", "Login", null );
    }
}
