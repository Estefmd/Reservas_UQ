package co.edu.uniquindio.reservasuq.controladores;



import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.*;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;


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
    public void registrarPersona(String cedula, String nombre, String correo, TipoUsuario tipoUsuario, String contrasenia) throws Exception {


    }


    @Override
    public void crearInstalacion(String nombre,int aforo, float costo, List<Horario> horarios) {
        reservasUq.crearInstalacion(nombre, aforo, costo, horarios);
    }

    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {
        return null;
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return null;
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        return null;
    }


    private Parent cargarPanel(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent node = loader.load();

            return node;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
