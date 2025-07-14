package org.proyectosemestral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Fabricas.FabricaLiga;

class FabricaLigaTest {
    private FabricaLiga fabrica = new FabricaLiga();

    @Test
    void testCrearTorneoLiga() {
        Torneo torneo = fabrica.crearTorneo("Liga Test");
        assertNotNull(torneo);
        assertTrue(torneo.getComportamiento() instanceof ComportamientoLiga);
    }

    @Test
    void testCrearParticipanteConStats() {
        Participante participante = fabrica.crearParticipante("Jugador", "test@test.com", "123");
        assertTrue(participante instanceof ParticipanteLiga);

        ParticipanteLiga pLiga = (ParticipanteLiga) participante;
        assertNotNull(pLiga.getStats());
    }

    @Test
    void testParticipantesStatsIniciadas() {
        ParticipanteLiga pLiga = (ParticipanteLiga) fabrica.crearParticipante("Jugador", "test@test.com", "123");
        assertEquals(0, pLiga.getStats().getPuntos());
        assertEquals(0, pLiga.getStats().getPartidosJugados());
    }
}