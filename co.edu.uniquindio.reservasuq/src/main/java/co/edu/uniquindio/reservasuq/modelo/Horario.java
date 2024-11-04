package co.edu.uniquindio.reservasuq.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Horario {

    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
