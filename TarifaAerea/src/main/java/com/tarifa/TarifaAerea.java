package com.tarifa;

public class TarifaAerea {

    
    public String calcularTarifaAerea(
            int edad,
            int vuelosAnuales,
            boolean esEstudianteFuera,
            boolean trabaja,
            boolean viveConPadres,
            String clase,
            String destino,
            double ingresos,
            boolean viajaConNiños) {

        
        // 1) Tarifa Pajarillo
        
        if (edad < 18 && vuelosAnuales >= 6) {
            return "Pajarillo";
        }

        
        // 2) Tarifa Gorrión
        
        if (edad >= 18 && edad <= 25 &&
                esEstudianteFuera &&
                clase.equalsIgnoreCase("turista") &&
                vuelosAnuales >= 12) {
            return "Gorrión";
        }

        
        // 3) Tarifa Viaja ahora que puedes / Atreviéndose a saltar del Nido
       
        if (edad >= 18 && edad <= 25 && trabaja) {

            if (viveConPadres &&
                    clase.equalsIgnoreCase("turista") &&
                    vuelosAnuales >= 3) {
                return "Viaja ahora que puedes";
            }

            if (!viveConPadres) {
                return "Atreviéndose a saltar del Nido";
            }
        }

      
        // 4) Tarifa Conoce Europa / Conoce Europa con tus peques
       
        if (edad > 25 &&
                ingresos > 20000 && ingresos < 35000 &&
                vuelosAnuales >= 6 &&
                clase.equalsIgnoreCase("turista") &&
                destino.equalsIgnoreCase("Europa")) {

            if (viajaConNiños) {
                return "Conoce Europa con tus peques";
            } else {
                return "Conoce Europa";
            }
        }

        
        // 5) Conoce el Mundo / Conoce el Mundo con tus peques
       
        if (edad > 25 &&
                ingresos > 35000 &&
                vuelosAnuales >= 6 &&
                clase.equalsIgnoreCase("business") &&
                (destino.equalsIgnoreCase("Asia") ||
                        destino.equalsIgnoreCase("America"))) {

            if (viajaConNiños) {
                return "Conoce el Mundo con tus peques";
            } else {
                return "Conoce el Mundo";
            }
        }

        
        // Si no cumple ninguna condición
        
        return "Sin tarifa disponible";
    }
}

