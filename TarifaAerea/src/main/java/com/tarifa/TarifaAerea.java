package com.tarifa;

import java.util.Scanner;

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

        if (edad < 18 && vuelosAnuales >= 6) {
            return "Pajarillo";
        }

        if (edad >= 18 && edad <= 25 &&
                esEstudianteFuera &&
                clase.equalsIgnoreCase("turista") &&
                vuelosAnuales >= 12) {
            return "Gorrión";
        }

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

        return "Sin tarifa disponible";
    }

    public static void main(String[] args) {

        TarifaAerea tarifa = new TarifaAerea();

        int edad = 30;
        int vuelosAnuales = 8;
        boolean esEstudianteFuera = false;
        boolean trabaja = true;
        boolean viveConPadres = false;
        String clase = "business";
        String destino = "Asia";
        double ingresos = 40000;
        boolean viajaConNinos = true;

        String resultado = tarifa.calcularTarifaAerea(
                edad,
                vuelosAnuales,
                esEstudianteFuera,
                trabaja,
                viveConPadres,
                clase,
                destino,
                ingresos,
                viajaConNinos
        );

        System.out.println("Tarifa asignada: " + resultado);
    }
}

