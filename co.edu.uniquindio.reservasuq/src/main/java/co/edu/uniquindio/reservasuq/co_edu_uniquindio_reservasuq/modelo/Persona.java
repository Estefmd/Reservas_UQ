package co.edu.uniquindio.reservasuq.co_edu_uniquindio_reservasuq.modelo;

import co.edu.uniquindio.reservasuq.co_edu_uniquindio_reservasuq.enums.TipoUsuario;
import lombok.Data;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor

public class Persona {
    private String cedula;
    private String nombre;
    private String correo;
    private TipoUsuario tipoUsuario;
}
