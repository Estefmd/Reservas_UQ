package co.edu.uniquindio.reservasuq.modelo;

import co.edu.uniquindio.reservasuq.enums.TipoUsuario;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reserva {
    private String id;
    private Instalacion instalacion;
    private Usuario usuario;
    private LocalDateTime fecha;
    private Horario hora;

    public String calcularTotal() {
        System.out.println(getUsuario().getTipoUsuario().name());
        if (TipoUsuario.EXTERNO.name().equals(getUsuario().getTipoUsuario().name())) {
            return "$" + getInstalacion().getPrecioInstalacion();
        }

        return "$0.00";
    }
}
