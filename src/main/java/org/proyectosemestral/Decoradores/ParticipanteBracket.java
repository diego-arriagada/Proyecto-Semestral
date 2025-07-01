package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;

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
