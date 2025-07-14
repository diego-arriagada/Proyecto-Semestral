package org.proyectosemestral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Decoradores.ParticipanteBracket;
import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;
import java.util.ArrayList;

class ComportamientoBracketTest {
    private ComportamientoBracket comportamiento;
    private Lista<Participante> participantes;
    private Torneo torneo;

    @BeforeEach
    void setUp() {
        comportamiento = new ComportamientoBracket();
        participantes = new Lista<>();
        torneo = new Torneo("Bracket Test", comportamiento);

        // Crear 4 participantes (potencia de 2)
        for(int i=0; i<4; i++) {
            Participante p = new Participante("Equipo "+i, "e"+i+"@test.com", "123");
            participantes.añadirParticipante(new ParticipanteBracket(p));
            torneo.añadirParticipante(new ParticipanteBracket(p));
        }

        // Inicializar partidos en el torneo
        torneo.iniciarTorneo();
        torneo.generarPartidos();
    }

    @Test
    void testGenerarPartidosBracket() {
        ArrayList<Partido> partidos = new ArrayList<>();
        partidos = comportamiento.generarPartidos(participantes, partidos);

        // 4 equipos en bracket = 2 partidos iniciales
        assertEquals(2, partidos.size());
    }

    @Test
    void testGenerarPartidosConCantidadNoPotenciaDe2() {
        // Añadir un 5to participante (no potencia de 2)
        participantes.añadirParticipante(new ParticipanteBracket(new Participante("Equipo 5", "e5@test.com", "123")));

        assertThrows(IllegalArgumentException.class, () -> {
            comportamiento.generarPartidos(participantes, new ArrayList<>());
        });
    }

    @Test
    void testJugarPartidosGeneraSiguienteFase() {
        ArrayList<Partido> partidos = torneo.getPartidos();

        // Jugar ambos partidos de la primera fase
        comportamiento.jugarPartidoSiguiente(torneo, partidos, 0, 2, 1);
        comportamiento.jugarPartidoSiguiente(torneo, partidos, 1, 3, 2);

        // Debería haberse generado la final (3er partido)
        assertEquals(3, partidos.size());
    }

    @Test
    void testFinalizarTorneoConCampeon() {
        ArrayList<Partido> partidos = torneo.getPartidos();

        // Jugar primera fase
        comportamiento.jugarPartidoSiguiente(torneo, partidos, 0, 2, 1);
        comportamiento.jugarPartidoSiguiente(torneo, partidos, 1, 3, 2);

        // Jugar final
        comportamiento.jugarPartidoSiguiente(torneo, partidos, 2, 1, 0);

        assertNotNull(torneo.getGanador());
        assertTrue(torneo.isFinalizado());
    }
}