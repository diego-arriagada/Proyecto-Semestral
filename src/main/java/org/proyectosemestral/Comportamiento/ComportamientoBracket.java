package org.proyectosemestral.Comportamiento;

import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;
import org.proyectosemestral.Torneo;

import java.util.ArrayList;
import java.util.Collections;
/** * ComportamientoBracket es una implementación de la interfaz ComportamientoTorneo que define el comportamiento
 * específico para un torneo en formato de bracket (eliminación directa).
 *
 * Esta clase se encarga de generar los partidos iniciales, gestionar los resultados y avanzar a las siguientes fases.
 * Trabaja por rondas, donde cada ronda elimina a la mitad de los participantes hasta que queda un campeón.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class ComportamientoBracket implements ComportamientoTorneo {

    public ComportamientoBracket() {}

    /**
     * Genera los partidos iniciales del torneo en formato bracket.
     * Los equipos se mezclan aleatoriamente y se emparejan para formar los partidos de la primera ronda.
     *
     * @param participantes Lista de participantes en el torneo.
     * @param partidos Lista donde se almacenarán los partidos generados.
     * @return Lista de partidos generados.
     */
    @Override
    public ArrayList<Partido> generarPartidos(Lista<Participante> participantes, ArrayList<Partido> partidos) {
        //calcula cuantos partidos habran en base a los equipos que hay, comprueba si es viable con la cantidad
        //de equipos dada y hace una mezcla para ir sacando de a pares los equipos, llena la lista de partidos y luego
        //retorna la lista con los enfrentamientos
        int cantidadEquipos = participantes.getLista().size();

        if (cantidadEquipos < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 equipos");
        }

        if (!((cantidadEquipos & (cantidadEquipos - 1)) == 0)) {
            throw new IllegalArgumentException("Se necesita una cantidad que sea potencia de 2 (2, 4, 8, 16, ...)");
        }


        ArrayList<Participante> participantesAleatorios = new ArrayList<>(participantes.getLista());
        Collections.shuffle(participantesAleatorios); // Mezclamos para emparejamientos aleatorios

        int totalPartidos = cantidadEquipos - 1;

        // Generamos los partidos de la primera fase (cuartos, semis, etc.)
        for (int i = 0; i < cantidadEquipos / 2; i++) {
            Participante local = participantesAleatorios.get(2 * i);
            Participante visitante = participantesAleatorios.get(2 * i + 1);
            partidos.add(new Partido(local, visitante));
        }

        return partidos;
    }

    /**
     * Juega el siguiente partido del torneo, actualizando los resultados y determinando el ganador.
     * Si todos los partidos de la fase actual han sido jugados, genera la siguiente fase.
     *
     * @param partidos Lista de partidos del torneo.
     * @param partidoPorJugar Índice del partido que se va a jugar.
     * @param resultado1 Goles del equipo local.
     * @param resultado2 Goles del equipo visitante.
     */
    @Override
    public void jugarPartidoSiguiente(Torneo torneo, ArrayList<Partido> partidos, int partidoPorJugar, int resultado1, int resultado2) {

        if (debeGenerarNuevaFase(partidos, partidoPorJugar)) {
            generarSiguienteFase(torneo, partidos);
        }

        if (partidoPorJugar >= partidos.size()) {
            throw new IllegalArgumentException("No hay más partidos disponibles");
        }

        Partido partidoActual = partidos.get(partidoPorJugar);

        if (partidoActual.getGanador() != null) {
            throw new IllegalStateException("Este partido ya ha sido jugado");
        }

        partidoActual.setGolesLocal(resultado1);
        partidoActual.setGolesVisitante(resultado2);
        partidoActual.jugarPartido();
        partidoActual.setGanador();

        // Si es necesario generar nuevos partidos (siguiente fase)
    }
    /** Verifica si es necesario generar una nueva fase del torneo.
     * Esto ocurre cuando todos los partidos de la fase actual han sido jugados.
     *
     * @param partidos Lista de partidos de la fase actual.
     * @param partidoActual Índice del partido actual que se está jugando.
     * @return true si se debe generar una nueva fase, false en caso contrario.
     */
    private boolean debeGenerarNuevaFase(ArrayList<Partido> partidos, int partidoActual) {
        // Verifica si todos los partidos de la fase actual han sido jugados
        System.out.println("a");
        int partidosPorFase = partidos.size() - partidoActual;
        for (int i = partidoActual; i < partidos.size(); i++) {
            if (partidos.get(i).getGanador() == null) {
                return false;
            }
        }
        return true;
    }

    /** Genera la siguiente fase del torneo basándose en los ganadores de la fase actual.
     * Si solo queda un ganador, se declara campeón.
     * Si hay más de un ganador, se generan nuevos partidos para la siguiente ronda.
     *
     * @param partidos Lista de partidos de la fase actual.
     */
    private void generarSiguienteFase(Torneo torneo, ArrayList<Partido> partidos) {
        ArrayList<Participante> ganadores = new ArrayList<>();

        // Obtenemos los ganadores de la fase anterior
        for (Partido p : partidos) {
            if (p.getGanador() != null) {
                ganadores.add(p.getGanador());
            }
        }


        if (ganadores.size() == 1) {
            System.out.println("Campeón: " + ganadores.get(0).getNombre());
            return;
        }

        // genera nuevos partidos de la misma forma
        for (int i = 0; i < ganadores.size() / 2; i++) {
            Participante local = ganadores.get(2 * i);
            Participante visitante = ganadores.get(2 * i + 1);
            torneo.getPartidos().add(new Partido(local, visitante));
        }
    }
}