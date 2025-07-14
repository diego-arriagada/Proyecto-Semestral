package org.proyectosemestral.Comportamiento;

import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Lista;
import org.proyectosemestral.Partido;
import org.proyectosemestral.Torneo;

import java.util.Collections;
import java.util.List;

import java.util.ArrayList;

/** ComportamientoLiga es una implementación de la interfaz ComportamientoTorneo, y define el comportamiento específico
 * de un torneo tipo liga.
 *
 * Esta clase se encarga de generar los partidos de la liga, asegurando que cada equipo juegue contra todos los demás
 * equipos en dos ocasiones (ida y vuelta).
 * Además, maneja la lógica para actualizar las estadísticas de los participantes después de cada partido.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class ComportamientoLiga implements ComportamientoTorneo {

    public ComportamientoLiga(){}

    /**
     * Genera los partidos de la liga.
     * Cada equipo juega contra todos los demás equipos en dos ocasiones (ida y vuelta).
     *
     * @param participantes Lista de participantes en la liga.
     * @param partidos Lista donde se almacenarán los partidos generados.
     * @return Lista de partidos generados.
     */
    @Override
    public ArrayList<Partido> generarPartidos(Lista<Participante> participantes, ArrayList<Partido> partidos) {
        int n = participantes.getLista().size();

        if (n < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 equipos");
        }

        int totalPartidos = n * (n - 1);
        int[][] matriz = new int[2][totalPartidos];
        int partidoActual = 0;

        // Primera vuelta (ida)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matriz[0][partidoActual] = i;  // Local
                matriz[1][partidoActual] = j;  // Visitante
                partidoActual++;
            }
        }

        // Segunda vuelta (vuelta)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matriz[0][partidoActual] = j;  // Local (invertido)
                matriz[1][partidoActual] = i;  // Visitante (invertido)
                partidoActual++;
            }
        }

        for (int i = 0; i < totalPartidos; i++) {
            int indiceP1 = matriz[0][i];
            int indiceP2 = matriz[1][i];
            partidos.add(new Partido(
                    participantes.getLista().get(indiceP1),
                    participantes.getLista().get(indiceP2)
            ));
        }

        ArrayList<Partido> partidos1 = new ArrayList<Partido>();
        for (int i = 0; i< totalPartidos; i++){
            if (i%2==0){
                partidos1.add(partidos.get(i));
            }else {
                partidos1.add(partidos.get(totalPartidos-i));
            }
        }
        return partidos1;
    }

    /**
     * Juega el siguiente partido de la liga, actualizando las estadísticas de los participantes.
     *
     * @param partidos Lista de partidos de la liga.
     * @param partidoSiguiente Índice del partido a jugar.
     * @param resultado1 Resultado del primer participante (local).
     * @param resultado2 Resultado del segundo participante (visitante).
     */
    @Override
    public void jugarPartidoSiguiente(Torneo torneo, ArrayList<Partido> partidos, int partidoSiguiente, int resultado1, int resultado2){
        Partido partidoActual = partidos.get(partidoSiguiente);
        partidoActual.setGolesLocal(resultado1);
        partidoActual.setGolesVisitante(resultado2);
        partidoActual.jugarPartido();
        ParticipanteLiga P1 = (ParticipanteLiga) partidoActual.getLocal();
        ParticipanteLiga P2 = (ParticipanteLiga) partidoActual.getVisita();
        if(resultado1 > resultado2){
            P1.getStats().setPuntos(P1.getStats().getPuntos() + 3);
            P1.getStats().setPartidosJugados(P1.getStats().getPartidosJugados() + 1);
            P2.getStats().setPartidosJugados(P2.getStats().getPartidosJugados() + 1);
            P1.getStats().setVictorias(P1.getStats().getVictorias() + 1);
            P2.getStats().setDerrotas(P2.getStats().getDerrotas() + 1);
            P1.getStats().setGoles(resultado1);
            P2.getStats().setGoles(resultado2);
            P1.getStats().setGolesEnContra(resultado2);
            P2.getStats().setGolesEnContra(resultado1);
        }
        if(resultado1 < resultado2){
            P2.getStats().setPuntos(P2.getStats().getPuntos() + 3);
            P1.getStats().setPartidosJugados(P1.getStats().getPartidosJugados() + 1);
            P2.getStats().setPartidosJugados(P2.getStats().getPartidosJugados() + 1);
            P1.getStats().setDerrotas(P1.getStats().getDerrotas() + 1);
            P2.getStats().setVictorias(P2.getStats().getVictorias() + 1);
            P1.getStats().setGoles(resultado1);
            P2.getStats().setGoles(resultado2);
            P1.getStats().setGolesEnContra(resultado2);
            P2.getStats().setGolesEnContra(resultado1);
        }
        if(resultado1 == resultado2){
            P1.getStats().setPuntos(P1.getStats().getPuntos() + 1);
            P2.getStats().setPuntos(P2.getStats().getPuntos() + 1);
            P1.getStats().setPartidosJugados(P1.getStats().getPartidosJugados() + 1);
            P2.getStats().setPartidosJugados(P2.getStats().getPartidosJugados() + 1);
            P1.getStats().setEmpates(P1.getStats().getEmpates() + 1);
            P2.getStats().setEmpates(P2.getStats().getEmpates() + 1);
            P1.getStats().setGoles(resultado1);
            P2.getStats().setGoles(resultado2);
            P1.getStats().setGolesEnContra(resultado2);
            P2.getStats().setGolesEnContra(resultado1);
        }
        if(partidoSiguiente == partidos.size()-1){
            torneo.setFinalizado(true);
            torneo.setGanador(torneo.getListaParticipantes().getLista().getFirst());
        }
    }
}
