package Controladores;

import java.time.LocalTime;

public class HoraActual {
    public static String determinarPeriodo() {
        LocalTime horaActual = LocalTime.now();
        if (horaActual.isBefore(LocalTime.NOON)) {
            return "manana"; // Antes de mediodía
        } else {
            return "noche"; // Después de las 6 PM
        }
    }
}
