package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;

/**
 * ParticipanteBracket es una clase decoradora que extiende DecoradorParticipante, añadiendo la funcionalidad
 * de eliminar un participante en un torneo tipo bracket con una propieadad booleana que indica si el participante ha sido eliminado.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class ParticipanteBracket extends DecoradorParticipante{
    private Boolean eliminado;

    public ParticipanteBracket(Participante participante){
        super(participante);
        this.eliminado = false;
    }

    public void eliminar(){
        if(eliminado = false){
            this.eliminado = true;
        }
    }


}
