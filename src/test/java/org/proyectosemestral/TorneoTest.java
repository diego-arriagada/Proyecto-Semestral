package org.proyectosemestral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Fabricas.FabricaBracket;
import org.proyectosemestral.Fabricas.FabricaLiga;

class TorneoTest {
    private Torneo torneo;
    private Participante participante1;
    private Participante participante2;
    private FabricaLiga fabricaLiga;

    @BeforeEach
    void setUp() {
        fabricaLiga = new FabricaLiga();
        torneo = fabricaLiga.crearTorneo("Torneo de Prueba");
        participante1 = fabricaLiga.crearParticipante("Equipo A", "a@test.com", "123");
        participante2 = fabricaLiga.crearParticipante("Equipo B", "b@test.com", "456");
    }

    @Test
    void testParticipantesTorneoNoIniciado() {
        assertTrue(torneo.añadirParticipante(participante1));
        assertTrue(torneo.añadirParticipante(participante2));
        assertEquals(2, torneo.tamañoLista());
    }

    @Test
    void testParticipantesTorneoIniciado() {
        torneo.iniciarTorneo();
        assertFalse(torneo.añadirParticipante(participante1));
    }

    @Test
    void testGeneracionPartidos() {
        torneo.añadirParticipante(participante1);
        torneo.añadirParticipante(participante2);
        torneo.iniciarTorneo();
        torneo.generarPartidos();

        assertNotNull(torneo.getPartidos());
        assertEquals(2, torneo.getPartidos().size()); // 2 equipos en liga = 2 partidos (ida y vuelta)
    }

    @Test
    void testActualizaEstadisticas() {
        torneo.añadirParticipante(participante1);
        torneo.añadirParticipante(participante2);
        torneo.iniciarTorneo();
        torneo.generarPartidos();

        ParticipanteLiga p1 = (ParticipanteLiga) participante1;
        ParticipanteLiga p2 = (ParticipanteLiga) participante2;

        torneo.jugarPartidoSiguiente(2, 1);

        assertEquals(3, p1.getStats().getPuntos());
        assertEquals(0, p2.getStats().getPuntos());
        assertEquals(1, p1.getStats().getVictorias());
        assertEquals(1, p2.getStats().getDerrotas());
    }

    @Test
    void testFinalizarTorneoBracket() {
        FabricaBracket fabricaBracket = new FabricaBracket();
        Torneo torneoBracket = fabricaBracket.crearTorneo("Torneo Bracket");

        // Añadir 4 participantes (potencia de 2)
        for(int i=0; i<4; i++) {
            torneoBracket.añadirParticipante(fabricaBracket.crearParticipante("Equipo "+i, "e"+i+"@test.com", "123"));
        }

        torneoBracket.iniciarTorneo();
        torneoBracket.generarPartidos();

        // Jugar todos los partidos
        while(!torneoBracket.isFinalizado()) {
            torneoBracket.jugarPartidoSiguiente(1, 0);
        }

        assertNotNull(torneoBracket.getGanador());
        assertTrue(torneoBracket.isFinalizado());
    }
}