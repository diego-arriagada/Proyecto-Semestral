package org.proyectosemestral;

import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Fabricas.FabricaLiga;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.table.DefaultTableModel;

class TorneoTest {
    private Torneo torneo;
    private FabricaLiga fabricaLiga;
    private Participante participante1;
    private Participante participante2;

    @BeforeEach
    void setUp() {
        fabricaLiga = new FabricaLiga();
        torneo = fabricaLiga.crearTorneo("Torneo de Prueba");
        participante1 = fabricaLiga.crearParticipante("Jugador 1", "jugador1@test.com", "+56912345678");
        participante2 = fabricaLiga.crearParticipante("Jugador 2", "jugador2@test.com", "+56987654321");
    }

    @Test
    void testAñadirParticipante() {
        assertTrue(torneo.añadirParticipante(participante1));
        assertEquals(1, torneo.tamañoLista());
    }

    @Test
    void testAñadirParticipanteTorneoIniciado() {
        torneo.iniciarTorneo();
        assertFalse(torneo.añadirParticipante(participante1));
    }

    @Test
    void testEliminarParticipante() {
        torneo.añadirParticipante(participante1);
        torneo.eliminarParticipante(participante1);
        assertEquals(0, torneo.tamañoLista());
    }

    @Test
    void testBuscarParticipante() {
        torneo.añadirParticipante(participante1);
        assertTrue(torneo.buscarParticipante(participante1));
        assertFalse(torneo.buscarParticipante(participante2));
    }

    @Test
    void testIniciarTorneo() {
        assertTrue(torneo.iniciarTorneo());
        assertTrue(torneo.getIniciado());
    }

    @Test
    void testIniciarTorneoYaIniciado() {
        torneo.iniciarTorneo();
        assertFalse(torneo.iniciarTorneo());
    }

    @Test
    void testGenerarPartidos() {
        torneo.añadirParticipante(participante1);
        torneo.añadirParticipante(participante2);
        torneo.iniciarTorneo();
        torneo.generarPartidos();
        assertNotNull(torneo.getPartidos());
        assertEquals(2, torneo.getPartidos().size());
    }

    @Test
    void testJugarPartidoSiguiente() {
        torneo.añadirParticipante(participante1);
        torneo.añadirParticipante(participante2);
        torneo.iniciarTorneo();
        torneo.generarPartidos();

        // Verificar que los participantes son instancias de ParticipanteLiga
        assertTrue(participante1 instanceof ParticipanteLiga);
        assertTrue(participante2 instanceof ParticipanteLiga);

        torneo.jugarPartidoSiguiente(2, 1);
        assertEquals(1, torneo.getPartidoActual());

        // Acceder a las estadísticas a través del decorador
        ParticipanteLiga p1 = (ParticipanteLiga) participante1;
        ParticipanteLiga p2 = (ParticipanteLiga) participante2;

        assertEquals(3, p1.getStats().getPuntos());
        assertEquals(0, p2.getStats().getPuntos());
        assertEquals(1, p1.getStats().getVictorias());
        assertEquals(1, p2.getStats().getDerrotas());
    }

    @Test
    void testLimpiarLista() {
        torneo.añadirParticipante(participante1);
        torneo.añadirParticipante(participante2);
        torneo.limpiarLista();
        assertEquals(0, torneo.tamañoLista());
    }

    @Test
    void testLimpiarListaTorneoIniciado() {
        torneo.añadirParticipante(participante1);
        torneo.iniciarTorneo();
        torneo.limpiarLista();
        assertEquals(1, torneo.tamañoLista());
    }

    @Test
    void testGetComportamiento() {
        assertNotNull(torneo.getComportamiento());
    }

    @Test
    void testToString() {
        assertEquals("Torneo de Prueba", torneo.toString());
    }

    @Test
    void testModeloTabla() {
        DefaultTableModel modelo = new DefaultTableModel();
        torneo.setModeloTabla(modelo);
        assertEquals(modelo, torneo.getModeloTabla());
    }
}