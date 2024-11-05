package co.edu.uniquindio.reservasuq.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Instalacion {

    private String id;
    private String nombreInstalacion;
    private List<Horario> horariosInstalacion;
    private int cuposInstalacion;
    private double precioInstalacion;

    public String obtenerFormatoHorarios() {
        String aux = "";
        for (Horario horario: getHorariosInstalacion()) {
            aux += horario + "\n";
        }

        return aux;
    }

    @Override
    public String toString() {
        return String.format("%s [Cupo: %d]: $%.2f", nombreInstalacion, cuposInstalacion, precioInstalacion);
    }
}
