package org.proyectosemestral;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Decoradores.ParticipanteBracket;
import org.proyectosemestral.Fabricas.FabricaBracket;

class FabricaBracketTest {
    private FabricaBracket fabrica = new FabricaBracket();

    @Test
    void testCrearTorneoBracket() {
        Torneo torneo = fabrica.crearTorneo("Bracket Test");
        assertNotNull(torneo);
        assertTrue(torneo.getComportamiento() instanceof ComportamientoBracket);
    }

    @Test
    void testCrearParticipanteBracket() {
        Participante participante = fabrica.crearParticipante("Jugador", "test@test.com", "123");
        assertTrue(participante instanceof ParticipanteBracket);
    }

    @Test
    void testParticipanteBracketNoEliminadoInicialmente() {
        ParticipanteBracket pBracket = (ParticipanteBracket) fabrica.crearParticipante("Jugador", "test@test.com", "123");
        assertFalse(pBracket.eliminado);
    }
}