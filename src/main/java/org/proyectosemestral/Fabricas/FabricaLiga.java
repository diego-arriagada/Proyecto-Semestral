package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteBracket;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

public class FabricaLiga implements FabricaTorneo {

    @Override
    public Torneo crearTorneo(String nombre){
        return new Torneo(nombre,new ComportamientoLiga());
    }

    @Override
    public Participante crearParticipante(String nombre,String correo,String numero){
        Participante participante;
        participante = new Participante(nombre,correo,numero);

        return new ParticipanteLiga(participante);
    }
}
