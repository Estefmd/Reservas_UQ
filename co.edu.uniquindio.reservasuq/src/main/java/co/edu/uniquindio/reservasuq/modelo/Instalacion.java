package co.edu.uniquindio.reservasuq.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Instalacion {

    private String id;
    private String nombreInstalacion;
    private LocalDateTime horariosInstalacion;
    private int cuposInstalacion;
    private double precioInstalacion;

}
