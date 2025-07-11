package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Stats;

public class ParticipanteLiga extends DecoradorParticipante{
    private Stats stats;

    public ParticipanteLiga(Participante participante){
        super(participante);
        this.stats = new Stats();
    }


    public Stats getStats(){
        return stats;
    }
}
