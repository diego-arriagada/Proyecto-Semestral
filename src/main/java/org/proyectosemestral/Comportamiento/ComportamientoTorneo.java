package org.proyectosemestral.Comportamiento;

    // Patron Strategy

import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;

import java.util.ArrayList;

/** ComportamientoTorneo es una interfaz que define la implementación generica del comportamiento de un torneo.
 * Esta interfaz es implementada por ComportamientoBracket y ComportamientoLiga,
 * permitiendo que el programa pueda decidir en tiempo de ejecución
 * qué comportamiento utilizar dependiendo del tipo de torneo que se desee crear.
 *
 * Esta interfaz implementa el patrón de diseño Strategy, permitiendo que el comportamiento del torneo
 * pueda ser modificado sin necesidad de cambiar la lógica del torneo en sí.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public interface ComportamientoTorneo {
    ArrayList<Partido> generarPartidos(Lista<Participante> participantes,ArrayList<Partido> partidos);
    void jugarPartidoSiguiente(ArrayList<Partido> partidos,int partidoActual,int resultado1,int resultado2);
}
