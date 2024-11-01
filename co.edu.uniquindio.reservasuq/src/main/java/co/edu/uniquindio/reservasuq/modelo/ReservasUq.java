package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import co.edu.uniquindio.reservasuq.utils.ValidacionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.PrimitiveIterator;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ReservasUq implements ServiciosReservasUQ {

    private List<Usuario> listaUsuarios;
    private List<Instalacion> listaInstalaciones;
    private List<Reserva> listaReservas;



    @Override
    public Usuario login(String correo, String contrasena) throws Exception {
        return null;
    }

    @Override
    public void registrarPersona(String cedula, String nombre, String correo, TipoUsuario tipoUsuario, String contrasenia) throws Exception {
        validarDatosEntradaUsuario(cedula,nombre,correo,tipoUsuario);

        Usuario usuario = new Usuario.UsuarioBuilder()
                .cedula(cedula)
                .nombre(nombre)
                .correo(correo)
                .tipoUsuario(tipoUsuario)
                .contrasenia(contrasenia)
                .build();
        listaUsuarios.add(usuario);
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
