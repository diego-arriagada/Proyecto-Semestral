package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Stats;

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
