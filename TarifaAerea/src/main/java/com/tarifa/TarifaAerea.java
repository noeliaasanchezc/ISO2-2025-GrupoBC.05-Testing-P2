
package com.tarifa;

 
public class TarifaAerea {
	
    public String calcularTarifaAerea(
            int edad,//Edad del cliente
            int vuelosAnuales,//Frecuencia de viajes (nº de vuelos al año)
            boolean esEstudianteFuera,//Si estudia en otra ciudad
            boolean trabaja,//Si ha empezado a trabajar
            boolean viveConPadres,//Si vive aún con sus padres
            String clase,//Clase en la que viaja ("turista" o "business")
            String destino,//Destino ("Europa", "Asia", "America")
            double ingresos,//Ingresos anuales
            boolean viajaConNiños) {//Si viaja con niños menores de 12

        // 1) Tarifa Pajarillo
        if (edad < 18 && vuelosAnuales >= 6) {
            return "Pajarillo";
        }

        // 2) Tarifa Gorrión
        if (edad >= 18 && edad <= 25 &&
                esEstudianteFuera &&
                clase.equalsIgnoreCase("turista") &&
                vuelosAnuales >= 12) {   // 1 viaje al mes del curso
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
        return "Sin tarifa disponible"; //Nombre de la tarifa
    }

 

        public static void main(String[] args) {

            TarifaAerea tarifa = new TarifaAerea();

            
            // PARÁMETROS PREDEFINIDOS
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