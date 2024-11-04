package co.edu.uniquindio.reservasuq.modelo;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Reserva {
    private String id;
    private Instalacion instalacion;
    private Usuario usuario;
    private LocalDate fecha;
    private LocalTime hora;
}
