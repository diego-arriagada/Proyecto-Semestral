package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Stats;

/**
 * ParticipanteLiga es una clase decoradora que extiende DecoradorParticipante, añadiendo la funcionalidad principal
 * de manejar las Stats de un participante en un torneo tipo liga, permitiendo comparar participantes por puntos y diferencia de goles.
 *
 * Esta clase implementa la interfaz Comparable para permitir la comparación entre participantes de la liga.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class ParticipanteLiga  extends DecoradorParticipante implements Comparable<ParticipanteLiga> {
    private Stats stats;

    @Override
    public int compareTo(ParticipanteLiga p){
        int comparadorPts = Integer.compare(this.getStats().getPuntos(), p.getStats().getPuntos());
        if (comparadorPts != 0){
            return comparadorPts;
        }
        else return Integer.compare(this.getStats().getGoles()-this.getStats().getGolesEnContra(), p.getStats().getGoles()-p.getStats().getGolesEnContra());
    }
    public ParticipanteLiga(Participante participante){
        super(participante);
        this.stats = new Stats();
    }


    public Stats getStats(){
        return stats;
    }
}
