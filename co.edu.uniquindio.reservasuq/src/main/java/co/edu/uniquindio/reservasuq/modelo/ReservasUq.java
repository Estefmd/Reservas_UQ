package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import co.edu.uniquindio.reservasuq.servicio.ServiciosReservasUQ;
import co.edu.uniquindio.reservasuq.utils.ValidacionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.UUID;

@Data
@AllArgsConstructor

public class ReservasUq implements ServiciosReservasUQ {

    private List<Usuario> listaUsuarios;
    private List<Instalacion> listaInstalaciones;
    private List<Reserva> listaReservas;


    public ReservasUq(){
        listaUsuarios = new ArrayList<>();
        listaInstalaciones = new ArrayList<>();
        listaReservas = new ArrayList<>();
    }

    @Override
    public Usuario login(String correo, String contrasenia) {

        System.out.println("Correo: " + correo);
        System.out.println("Contrasenia: " + contrasenia);
        for (Usuario usuario: listaUsuarios) {
            if(usuario.getCorreo().equals(correo) && usuario.getContrasenia().equals(contrasenia)){
               return usuario;
            }
        }
        return null;
    }


    @Override
    public void registrarUsuario(String cedula, String nombre, String correo, TipoUsuario tipoUsuario, String contrasenia) throws Exception {
        validarDatosEntradaUsuario(cedula,nombre,correo,tipoUsuario);

        Usuario usuario = new Usuario.UsuarioBuilder()
                .cedula(cedula)
                .nombre(nombre)
                .correo(correo)
                .tipoUsuario(tipoUsuario)
                .contrasenia(contrasenia)
                .build();
        listaUsuarios.add(usuario);
        System.out.println(listaUsuarios);
    }

    @Override
    public Usuario buscarUsuario(String cedula) throws Exception {
        for(Usuario usuario : listaUsuarios){
            if (usuario.getCedula().equals(cedula)){
                return usuario;
            }
        }
        return null;
    }


    @Override
    public void crearInstalacion(String nombre, int aforo, float costo, List<Horario> horarios) {

       Instalacion instalacion = new Instalacion.InstalacionBuilder()
                .nombreInstalacion(nombre)
                .cuposInstalacion(aforo)
                .precioInstalacion(costo)
                .build();

       listaInstalaciones.add(instalacion);
        System.out.println(listaInstalaciones);

    }



    @Override
    public Reserva crearReserva(String idInstalacion, String cedulaPersona, LocalDate diaReserva, String horaReserva) throws Exception {

        Usuario usuario= buscarUsuario(cedulaPersona);
        LocalDateTime fechaReserva = LocalDateTime.of(diaReserva, LocalTime.parse(horaReserva));
        Reserva reserva = Reserva.builder()
                .id(String.valueOf(UUID.randomUUID()))
                .usuario(usuario)
                .fecha(LocalDate.from(fechaReserva))
                .build();

        return reserva;
    }


    @Override
    public void cancelarReserva(String idReserva) throws Exception {

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
