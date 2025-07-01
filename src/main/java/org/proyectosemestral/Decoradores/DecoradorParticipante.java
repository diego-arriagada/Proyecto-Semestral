package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;

    //Patron Decorator

public abstract class DecoradorParticipante extends Participante{
    protected Participante participanteDecorado;

    public DecoradorParticipante(Participante participante){
        super(participante.getNombre(), participante.getCorreo(), participante.getNumero());
        this.participanteDecorado = participante;
    }
}
