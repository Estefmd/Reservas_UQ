package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import co.edu.uniquindio.reservasuq.utils.EnvioEmail;
import co.edu.uniquindio.reservasuq.utils.ValidacionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ReservasUq implements ServiciosReservasUQ {

    private List<Usuario> listaUsuarios;
    private List<Instalacion> listaInstalaciones;
    private List<Reserva> listaReservas;

    public ReservasUq() {
        listaUsuarios = new ArrayList<>();
        listaInstalaciones = new ArrayList<>();
        listaReservas = new ArrayList<>();
    }

    @Override
    public Usuario login(String correo, String contrasenia) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCorreo().equals(correo) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void registrarUsuario(String cedula, String nombre, String correo, TipoUsuario tipoUsuario, String contrasenia) throws Exception {
        validarDatosEntradaUsuario(cedula, nombre, correo, tipoUsuario);

        Usuario usuarioBuscado = buscarUsuario(cedula);
        if (usuarioBuscado != null) {
            throw new Exception("Ya existe un usuario registrado");
        }

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
    public Usuario buscarUsuario(String cedula) throws Exception {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getCedula().equals(cedula)) {
                return usuario;
            }
        }
        return null;
    }

    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {

        List<Horario> horariosCopia = new ArrayList<>(horarios);
        Instalacion instalacion = new Instalacion.InstalacionBuilder()
                .id(UUID.randomUUID().toString())
                .nombreInstalacion(nombre)
                .cuposInstalacion(aforo)
                .precioInstalacion(costo)
                .horariosInstalacion(horariosCopia)
                .build();

        listaInstalaciones.add(instalacion);
        System.out.println(listaInstalaciones);
    }

    public Instalacion buscarInstalacion(String instalacionId) {
        for (Instalacion instalacion : getListaInstalaciones()) {
            if (instalacion.getId().equals(instalacionId)) {
                return instalacion;
            }
        }

        return null;
    }

    @Override
    public void eliminarInstalacion(String instalacionId) throws Exception {
        Instalacion instalacion = buscarInstalacion(instalacionId);
        if (instalacion == null) {
            throw new Exception("La instalación no existe");
        }

        getListaInstalaciones().remove(instalacion);
    }

    @Override
    public Reserva crearReserva(String instalacionId, String cedulaPersona, LocalDate diaReserva, Horario horaReserva) throws Exception {

        Usuario usuario = buscarUsuario(cedulaPersona);
        LocalDateTime fechaReserva = LocalDateTime.of(diaReserva, horaReserva.getHoraInicio());

        Instalacion instalacion = buscarInstalacion(instalacionId);
        if (instalacion == null) {
            throw new Exception("No es posible crear la reserva. No se encuentra la instalación");
        }

        if (puedeReservar(diaReserva)) {
            throw new Exception("Solo se puede reservar una instalación con al menos 2 días de anticipación");
        }

        horaReserva.setFecha(diaReserva);
        Reserva reserva = Reserva.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .instalacion(instalacion)
                .usuario(usuario)
                .fecha(fechaReserva)
                .hora(horaReserva)
                .build();

        listaReservas.add(reserva);
        enviarNotificacion(reserva);
        return reserva;
    }

    public Reserva buscarReserva(String reservaId) {
        for (Reserva reserva : getListaReservas()) {
            if (reserva.getId().equals(reservaId)) {
                return reserva;
            }
        }

        return null;
    }

    @Override
    public void cancelarReserva(String reservaId) throws Exception {
        if (reservaId == null) {
            throw new Exception("Debe seleccionar una reserva");
        }

        Reserva reserva = buscarReserva(reservaId);
        getListaReservas().remove(reserva);
    }

    @Override
    public List<Reserva> listarTodasReservas() {
        return null;
    }

    @Override
    public List<Reserva> listarReservasPorPersona(String cedulaPersona) {
        List<Reserva> aux = new ArrayList<>();
        for (Reserva reserva : getListaReservas()) {
            if (reserva.getUsuario().getCedula().equalsIgnoreCase(cedulaPersona)) {
                aux.add(reserva);
            }
        }

        return aux;
    }

    public List<Horario> crearHorarios() {
        List<Horario> horarios = new ArrayList<>();
        for (int j = 0; j < 10; j += 2) {
            horarios.add(
                    new Horario(
                            LocalTime.of(8 + j, 0, 0),
                            LocalTime.of(10 + j, 0, 0)));
        }

        return horarios;
    }

    public int contarReservas(String instalacionId, LocalDate fechaSeleccionada, LocalTime hora){

        int contador = 0;

        for(Reserva reserva : listaReservas){
            if( reserva.getInstalacion().getId().equals(instalacionId)
                    && reserva.getHora().getFecha().equals(fechaSeleccionada)
                    && reserva.getHora().getHoraInicio().equals(hora)
            ){
                contador ++;
            }
        }

        return contador;

    }

    public List<Horario> obtenerHorarioDisponiblePorFecha(String instalacionId, LocalDate fechaSeleccionada) throws Exception {
        Instalacion instalacion = buscarInstalacion(instalacionId);
        if (instalacion == null) {
            throw new Exception("Debe seleccionar una instalación");
        }

        List<Horario> horarios = new ArrayList<>(instalacion.getHorariosInstalacion());
        for (Reserva reserva : getListaReservas()) {
            if (reserva.getInstalacion().getId().equals(instalacionId)) {
                if (reserva.getHora().getFecha().isEqual(fechaSeleccionada)) {
                    for(Horario horario: instalacion.getHorariosInstalacion()){
                        if( contarReservas(instalacionId, fechaSeleccionada, horario.getHoraInicio()) > instalacion.getCuposInstalacion()-1 ){
                            horarios.remove(horario);
                        }
                    }
                }
            }
        }

        return horarios;
    }

    public void validarDatosEntradaUsuario(String cedula, String nombre, String correo, TipoUsuario tipoUsuario)
            throws Exception {

        ValidacionUtil.validarCampo(cedula, "Cedula");
        ValidacionUtil.validarCampo(nombre, "Nombre");
        ValidacionUtil.validarCampo(correo, "Correo");
        ValidacionUtil.validarTipoUsuario(tipoUsuario);

        ValidacionUtil.validarCorreo(correo);
        ValidacionUtil.validarTipoUsuario(tipoUsuario);
    }

    private void enviarNotificacion(Reserva reserva) {
        String mensaje = reserva.getUsuario()
                + "\nInstalación: " + reserva.getInstalacion().getNombreInstalacion()
                + "\nFecha: " + reserva.getFecha()
                + "\nTotal: " + reserva.calcularTotal();

        EnvioEmail.enviarNotificacion(reserva.getUsuario().getCorreo(), "Confirmación reserva", mensaje);
    }

    public boolean puedeReservar(LocalDate fechaInstalacion) {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaActual, fechaInstalacion);
        int diasDiferencia = periodo.getDays();

        return (diasDiferencia < 2);
    }
}
