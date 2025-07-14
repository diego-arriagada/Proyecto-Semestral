package org.proyectosemestral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;

import java.util.ArrayList;

class ComportamientoLigaTest {
    private ComportamientoLiga comportamiento;
    private Lista<Participante> participantes;

    @BeforeEach
    void setUp() {
        comportamiento = new ComportamientoLiga();
        participantes = new Lista<>();

        // Crear 3 participantes para pruebas
        for(int i=0; i<3; i++) {
            Participante p = new Participante("Equipo "+i, "e"+i+"@test.com", "123");
            participantes.añadirParticipante(new ParticipanteLiga(p));
        }
    }

    @Test
    void testGenerarPartidosLiga() {
        ArrayList<Partido> partidos = new ArrayList<>();
        partidos = comportamiento.generarPartidos(participantes, partidos);

        // 3 equipos en liga = 6 partidos (3 ida y 3 vuelta)
        assertEquals(6, partidos.size());

        // Verificar que todos contra todos
        for(int i=0; i<3; i++) {
            int count = 0;
            Participante p = participantes.getLista().get(i);
            for(Partido partido : partidos) {
                if(partido.getLocal() == p || partido.getVisita() == p) {
                    count++;
                }
            }
            assertEquals(4, count); // Cada equipo juega 4 partidos (2 contra cada rival)
        }
    }

    @Test
    void testJugarPartidoActualizaEstadisticas() {
        ArrayList<Partido> partidos = new ArrayList<>();
        partidos = comportamiento.generarPartidos(participantes, partidos);

        ParticipanteLiga p1 = (ParticipanteLiga) participantes.getLista().get(0);
        ParticipanteLiga p2 = (ParticipanteLiga) participantes.getLista().get(1);

        Torneo torneo = new Torneo("Test", new ComportamientoLiga());
        comportamiento.jugarPartidoSiguiente(torneo, partidos, 0, 2, 1);

        assertEquals(3, p1.getStats().getPuntos());
        assertEquals(0, p2.getStats().getPuntos());
        assertEquals(2, p1.getStats().getGoles());
        assertEquals(1, p2.getStats().getGoles());
    }
}