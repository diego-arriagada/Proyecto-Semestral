package org.proyectosemestral;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestClassOrder;

import static org.junit.jupiter.api.Assertions.*;

class TorneoTest{
    private TorneoLiga torneoLiga1;
    private TorneoLiga torneoLiga2;
    private Torneo torneoBracket1;
    private Torneo torneoBracket2;


    @BeforeEach
    void setUp() {
        torneoLiga1 = new TorneoLiga(1);
        torneoLiga2 = new TorneoLiga(2);
        torneoBracket1 = new TorneoBracket(1);
        torneoBracket2 = new TorneoBracket(2);
    }

    @AfterEach
    void tearDown() {
        torneoLiga1.limpiarLista();
        torneoLiga2.limpiarLista();
        torneoBracket1.limpiarLista();
        torneoBracket2.limpiarLista();
    }

    @Test
    void testAñadirParticipante() {
        Equipo equipo1 = new Equipo("equipo1","equipo1@prueba.cl","e111");
        Jugador jugador1 = new Jugador("jugador1","jugador1@prueba.cl","j111");

        torneoLiga1.añadirParticipante(jugador1);
        torneoLiga1.añadirParticipante(equipo1);
        assertTrue(torneoLiga1.buscarParticipante(jugador1));
        assertFalse(torneoLiga1.buscarParticipante(equipo1));

        torneoBracket1.añadirParticipante(jugador1);
        torneoBracket1.añadirParticipante(equipo1);
        assertTrue(torneoBracket1.buscarParticipante(jugador1));
        assertFalse(torneoBracket1.buscarParticipante(equipo1));

        torneoLiga2.añadirParticipante(jugador1);
        torneoLiga2.añadirParticipante(equipo1);
        assertFalse(torneoLiga2.buscarParticipante(jugador1));
        assertTrue(torneoLiga2.buscarParticipante(equipo1));

        torneoBracket2.añadirParticipante(jugador1);
        torneoBracket2.añadirParticipante(equipo1);
        assertFalse(torneoBracket2.buscarParticipante(jugador1));
        assertTrue(torneoBracket2.buscarParticipante(equipo1));

        Jugador jugadorNulo = null;
        torneoLiga1.añadirParticipante(jugadorNulo);
        torneoBracket1.añadirParticipante(jugadorNulo);
        assertFalse(torneoLiga1.buscarParticipante(jugadorNulo));
        assertFalse(torneoBracket1.buscarParticipante(jugadorNulo));

        Jugador jugadorDuplicado = new Jugador("jugadorDuplicado","jugadorDuplicado@prueba.cl","duplicado");
        torneoLiga1.getListaParticipantes().getLista().clear();
        torneoLiga1.añadirParticipante(jugadorDuplicado);
        torneoLiga1.añadirParticipante(jugadorDuplicado);
        assertFalse(torneoLiga1.tamañoLista()==2);

        torneoBracket1.getListaParticipantes().getLista().clear();
        torneoBracket1.añadirParticipante(jugadorDuplicado);
        torneoBracket1.añadirParticipante(jugadorDuplicado);
        assertFalse(torneoBracket1.tamañoLista()==2);

    }

    @Test
    void testEliminarParticipante() {
        Equipo equipo1 = new Equipo("equipo1","equipo1@prueba.cl","e111");
        Jugador jugador1 = new Jugador("jugador1","jugador1@prueba.cl","j111");
        torneoLiga1.añadirParticipante(jugador1);
        torneoLiga1.eliminarParticipante(jugador1);
        torneoLiga2.añadirParticipante(equipo1);
        torneoLiga2.eliminarParticipante(equipo1);
        assertFalse(torneoLiga1.buscarParticipante(jugador1));
        assertFalse(torneoLiga2.buscarParticipante(equipo1));

        torneoBracket1.añadirParticipante(jugador1);
        torneoBracket1.eliminarParticipante(jugador1);
        torneoBracket2.añadirParticipante(equipo1);
        torneoBracket2.eliminarParticipante(equipo1);
        assertFalse(torneoBracket1.buscarParticipante(jugador1));
        assertFalse(torneoBracket2.buscarParticipante(equipo1));

    }
    @Test
    void testIniciarTorneo(){
        Jugador jugadorIniciado1 = new Jugador("jugadorIniciado1","jugadorIniciado1@prueba.cl","jI111");
        Jugador jugadorIniciado2 = new Jugador("jugadorIniciado2","jugadorIniciado2@prueba.cl","jI222");

        torneoLiga1.añadirParticipante(jugadorIniciado1);

        torneoLiga1.iniciarTorneo();
        torneoLiga1.añadirParticipante(jugadorIniciado2);
        assertTrue(torneoLiga1.buscarParticipante(jugadorIniciado1));
        assertFalse(torneoLiga1.buscarParticipante(jugadorIniciado2));

        torneoBracket1.añadirParticipante(jugadorIniciado1);

        torneoBracket1.iniciarTorneo();
        torneoBracket1.añadirParticipante(jugadorIniciado2);
        assertTrue(torneoBracket1.buscarParticipante(jugadorIniciado1));
        assertFalse(torneoBracket1.buscarParticipante(jugadorIniciado2));


    }
    @Test
    void testLimpiarLista(){
        Jugador jugadorLimpiado = new Jugador("jugadorLimpiado","jugadorLimpiado@prueba.cl","jL111");

        torneoLiga1.añadirParticipante(jugadorLimpiado);
        torneoLiga1.limpiarLista();
        assertFalse(torneoLiga1.buscarParticipante(jugadorLimpiado));

        torneoBracket1.añadirParticipante(jugadorLimpiado);
        torneoBracket1.limpiarLista();
        assertFalse(torneoBracket1.buscarParticipante(jugadorLimpiado));
    }
}