package com.tarifa;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TarifaAereaTest {

    private TarifaAerea tarifa;

    @BeforeEach
    void setUp() {
        tarifa = new TarifaAerea();
    }

    // ===================================
    // 1) Tarifa Pajarillo (Edad < 18 y Vuelos Anuales >= 6)
    // ===================================
    @Test
    void testPajarillo_CondicionesCumplidas() {
        assertEquals("Pajarillo",
                tarifa.calcularTarifaAerea(17, 6, false, false, true, "turista", "Europa", 0, false));
    }
    
    @Test
    void testPajarillo_FallaEdad() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(18, 6, false, false, true, "turista", "Europa", 0, false));
    }

    @Test
    void testPajarillo_FallaVuelos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(17, 5, false, false, true, "turista", "Europa", 0, false));
    }

    // ===================================
    // 2) Tarifa Gorrión (18 <= Edad <= 25, Estudiante, Turista, Vuelos >= 12)
    // ===================================
    @Test
    void testGorrion_CondicionesCumplidas() {
        assertEquals("Gorrión",
                tarifa.calcularTarifaAerea(20, 12, true, false, true, "turista", "Europa", 0, false));
    }

    @Test
    void testGorrion_FallaVuelos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(20, 11, true, false, true, "turista", "Europa", 0, false));
    }
    
    @Test
    void testGorrion_FallaClase() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(20, 12, true, false, true, "business", "Europa", 0, false));
    }
    
    // TEST AÑADIDO: Falla Estudiante para cubrir la instrucción 'esEstudianteFuera'
    @Test
    void testGorrion_FallaEstudiante() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(20, 12, false, false, true, "turista", "Europa", 0, false));
    }

    // ===================================
    // 3) Bloque Joven Trabajador (18 <= Edad <= 25, Trabaja)
    // ===================================

    // RAMA 3a: Vive Con Padres, Clase turista, Vuelos >= 3 -> "Viaja ahora que puedes"
    @Test
    void testViajaAhoraQuePuedes_CondicionesCumplidas() {
        assertEquals("Viaja ahora que puedes",
                tarifa.calcularTarifaAerea(22, 3, false, true, true, "turista", "Europa", 0, false));
    }

    @Test
    void testViajaAhoraQuePuedes_FallaVuelos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(22, 2, false, true, true, "turista", "Europa", 0, false));
    }
    
    @Test
    void testViajaAhoraQuePuedes_FallaClase() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(22, 3, false, true, true, "business", "Europa", 0, false));
    }

    // RAMA 3b: NO Vive Con Padres -> "Atreviéndose a saltar del Nido"
    @Test
    void testAtreviendoseASaltarDelNido_CondicionesCumplidas() {
        assertEquals("Atreviéndose a saltar del Nido",
                tarifa.calcularTarifaAerea(23, 2, false, true, false, "turista", "Europa", 0, false));
    }
    
    // ===================================
    // 4) Bloque Conoce Europa (Edad > 25, Ingresos 20k-35k, Vuelos >= 6, Turista, Destino Europa)
    // ===================================

    // RAMA 4a: Sin Niños
    @Test
    void testConoceEuropaSinNinos() {
        assertEquals("Conoce Europa",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "turista", "Europa", 30000, false));
    }

    // RAMA 4b: Con Niños
    @Test
    void testConoceEuropaConNinos() {
        assertEquals("Conoce Europa con tus peques",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "turista", "Europa", 30000, true));
    }
    
    // Falla Vuelos
    @Test
    void testConoceEuropa_FallaVuelos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(30, 5, false, false, false, "turista", "Europa", 30000, false));
    }

    // Falla Clase
    @Test
    void branch_ConoceEuropa_FallaClase() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "business", "Europa", 30000, false));
    }

    // Falla Ingresos (Límite inferior no incluido)
    @Test
    void testConoceEuropa_FallaIngresosBajos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "turista", "Europa", 20000, false));
    }
    
    // Falla Ingresos (Límite superior no incluido)
    @Test
    void testConoceEuropa_FallaIngresosAltos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "turista", "Europa", 35000, false));
    }
    
    // Falla Destino
    @Test
    void branch_ConoceEuropa_DestinoIncorrecto() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(35, 6, false, false, false, "turista", "Asia", 30000, false));
    }

    // ===================================
    // 5) Bloque Conoce el Mundo (Edad > 25, Ingresos > 35k, Vuelos >= 6, Business, Destino Asia/America)
    // ===================================

    // RAMA 5a: Sin Niños (Destino Asia)
    @Test
    void testConoceMundoSinNinos_DestinoAsia() {
        assertEquals("Conoce el Mundo",
                tarifa.calcularTarifaAerea(40, 8, false, true, false, "business", "Asia", 50000, false));
    }
    
    // RAMA 5a: Sin Niños (Destino America)
    @Test
    void testConoceMundoSinNinos_DestinoAmerica() {
        assertEquals("Conoce el Mundo",
                tarifa.calcularTarifaAerea(40, 8, false, true, false, "business", "America", 50000, false));
    }

    // RAMA 5b: Con Niños
    @Test
    void testConoceMundoConNinos() {
        assertEquals("Conoce el Mundo con tus peques",
                tarifa.calcularTarifaAerea(45, 8, false, true, false, "business", "America", 55000, true));
    }
    
    // Falla Vuelos
    @Test
    void branch_ConoceMundo_FallaVuelos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(40, 5, false, true, false, "business", "Asia", 50000, false));
    }

    // Falla Clase
    @Test
    void testConoceMundo_FallaClase() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(40, 8, false, true, false, "turista", "Asia", 50000, false));
    }
    
    // Falla Ingresos (Límite inferior no incluido)
    @Test
    void testConoceMundo_FallaIngresos() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(40, 8, false, true, false, "business", "Asia", 35000, false));
    }

    // Falla Destino
    @Test
    void branch_ConoceMundo_DestinoNoValido() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(40, 6, false, false, false, "business", "Oceania", 40000, false));
    }
    
    // ===================================
    // 6) Sin Tarifa Disponible (Fallo General)
    // ===================================

    @Test
    void testSinTarifaDisponible_FalloGeneral() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(50, 1, false, false, true, "turista", "Europa", 10000, false));
    }
    
    // ===================================
    // 7) Cobertura de método main
    // ===================================
    @Test
    void testMainMethodCoverage() {
        // Llama al método main de la clase TarifaAerea para cubrir las instrucciones
        // de inicialización de variables y System.out.println.
        TarifaAerea.main(new String[]{});
        // Este test pasa si no lanza ninguna excepción.
    }
}