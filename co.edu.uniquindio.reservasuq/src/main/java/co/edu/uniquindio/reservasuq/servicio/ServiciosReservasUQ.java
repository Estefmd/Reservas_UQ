package co.edu.uniquindio.reservasuq.servicio;


import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Instalacion;
import co.edu.uniquindio.reservasuq.modelo.Usuario;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import java.time.LocalDate;
import java.util.List;


public interface ServiciosReservasUQ {

    // LOGIN
    Usuario login(String correo, String contrasena) throws Exception;

    // CRUD USUARIO
    void registrarUsuario(String cedula, String nombre, String correo , TipoUsuario tipoUsuario,String contrasenia) throws Exception;

    Usuario buscarUsuario(String cedula) throws Exception;


    // CRUD INSTALACIÓN
    void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios);

    void eliminarInstalacion(String instalacionId) throws Exception;

    // CRUD RESERVA

    Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, Horario horaReserva) throws Exception;

    void cancelarReserva(String reservaId) throws Exception;


    List<Horario> obtenerHorarioDisponiblePorFecha(String instalacionId, LocalDate fechaSeleccionada) throws Exception;

    // Listar
    List<Reserva> listarTodasReservas();

    List<Reserva> listarReservasPorPersona(String cedulaPersona);
}
