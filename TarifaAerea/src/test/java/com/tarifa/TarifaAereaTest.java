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

    // -----------------------------------------------
    // TESTS ORIGINALES (ya cubren grandes bloques)
    // -----------------------------------------------

    @Test
    void testPajarillo() {
        assertEquals("Pajarillo",
                tarifa.calcularTarifaAerea(17, 6, false, false, true, "turista", "Europa", 0, false));
    }

    @Test
    void testGorrion() {
        assertEquals("Gorrión",
                tarifa.calcularTarifaAerea(20, 12, true, false, true, "turista", "Europa", 0, false));
    }

    @Test
    void testViajaAhoraQuePuedes() {
        assertEquals("Viaja ahora que puedes",
                tarifa.calcularTarifaAerea(22, 3, false, true, true, "turista", "Europa", 0, false));
    }

    @Test
    void testAtreviendoseASaltarDelNido() {
        assertEquals("Atreviéndose a saltar del Nido",
                tarifa.calcularTarifaAerea(23, 2, false, true, false, "turista", "Europa", 0, false));
    }

    @Test
    void testConoceEuropaSinNinos() {
        assertEquals("Conoce Europa",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "turista", "Europa", 30000, false));
    }

    @Test
    void testConoceEuropaConNinos() {
        assertEquals("Conoce Europa con tus peques",
                tarifa.calcularTarifaAerea(30, 6, false, false, false, "turista", "Europa", 30000, true));
    }

    @Test
    void testConoceMundoSinNinos() {
        assertEquals("Conoce el Mundo",
                tarifa.calcularTarifaAerea(40, 8, false, true, false, "business", "Asia", 50000, false));
    }

    @Test
    void testConoceMundoConNinos() {
        assertEquals("Conoce el Mundo con tus peques",
                tarifa.calcularTarifaAerea(45, 8, false, true, false, "business", "America", 55000, true));
    }

    @Test
    void testSinTarifaDisponible() {
        assertEquals("Sin tarifa disponible",
                tarifa.calcularTarifaAerea(50, 1, false, false, true, "turista", "Europa", 10000, false));
    }

    // -----------------------------------------------
    // TESTS NUEVOS PARA SUBIR A +80% EN BRANCHES
    // -----------------------------------------------

    // 1) Joven trabajador, vive con padres PERO clase ≠ turista → Sin tarifa
    @Test
    void branch_JovenTrabaja_ViveConPadres_PeroClaseIncorrecta() {
        String result = tarifa.calcularTarifaAerea(
                22, 5, false, true, true, "business", "Europa", 0, false);
        assertEquals("Sin tarifa disponible", result);
    }

    // 2) Conoce Europa — destino incorrecto
    @Test
    void branch_ConoceEuropa_DestinoIncorrecto() {
        String result = tarifa.calcularTarifaAerea(
                35, 6, false, false, false, "turista", "Asia", 30000, false);
        assertEquals("Sin tarifa disponible", result);
    }

    // 3) Conoce el Mundo — destino NO válido
    @Test
    void branch_ConoceMundo_DestinoNoValido() {
        String result = tarifa.calcularTarifaAerea(
                40, 6, false, false, false, "business", "Oceania", 40000, false);
        assertEquals("Sin tarifa disponible", result);
    }
}

