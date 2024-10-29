package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import lombok.Data;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor

public class Usuario {
    private String cedula;
    private String nombre;
    private String correo;
    private TipoUsuario tipoUsuario;
}
