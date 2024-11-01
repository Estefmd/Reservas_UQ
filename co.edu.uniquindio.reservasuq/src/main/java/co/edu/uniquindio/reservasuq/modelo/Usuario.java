package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
@Builder
public class Usuario {
    private String cedula;
    private String nombre;
    private String correo;
    private TipoUsuario tipoUsuario;
    private String contrasenia;
}
