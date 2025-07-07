package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteBracket;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

public class FabricaBracket implements FabricaTorneo {

    @Override
    public Torneo crearTorneo(String nombre){
        return new Torneo(nombre,new ComportamientoBracket());
    }

    @Override
    public Participante crearParticipante(String nombre,String correo,String numero){
        Participante participante;
        participante = new Participante(nombre,correo,numero);

        return new ParticipanteBracket(participante);
    }
}
