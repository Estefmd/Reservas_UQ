package co.edu.uniquindio.reservasuq.servicio;


import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.modelo.Horario;
import co.edu.uniquindio.reservasuq.modelo.Usuario;
import co.edu.uniquindio.reservasuq.modelo.Reserva;
import java.time.LocalDate;
import java.util.List;


public interface ServiciosReservasUQ {


    Usuario login(String correo, String contrasena) throws Exception;


    void registrarPersona(String cedula, String nombre, String correo , TipoUsuario tipoUsuario,String contrasenia) throws Exception;


    void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios);


    Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception;


    List<Reserva> listarTodasReservas();


    List<Reserva> listarReservasPorPersona(String cedulaPersona);


}
