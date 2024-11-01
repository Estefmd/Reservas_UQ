package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import co.edu.uniquindio.reservasuq.utils.ValidacionUtil;

import java.time.LocalDate;
import java.util.List;

public class ReservasUq implements ServiciosReservasUQ {
    @Override
    public Usuario login(String correo, String contrasena) throws Exception {
        return null;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, String correo, TipoUsuario tipoUsuario, String contrasenia) throws Exception {

    }


    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {

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

    public void validarDatosEntradaUsuario(String cedula, String nombre,String correo, TipoUsuario tipoUsuario)
            throws Exception {

        ValidacionUtil.validarCampo(cedula, "Cedula");
        ValidacionUtil.validarCampo(nombre, "Nombre");
        ValidacionUtil.validarCampo(correo, "Correo");
        ValidacionUtil.validarTipoUsuario(tipoUsuario);

        ValidacionUtil.validarCorreo(correo);
        ValidacionUtil.validarTipoUsuario(tipoUsuario);
    }
}
