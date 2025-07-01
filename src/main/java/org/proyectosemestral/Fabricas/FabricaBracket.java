package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteBracket;
import org.proyectosemestral.Equipo;
import org.proyectosemestral.Jugador;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

public class FabricaBracket implements FabricaTorneo {

    @Override
    public Torneo crearTorneo(String nombre){
        return new Torneo(nombre,new ComportamientoBracket());
    }

    @Override
    public Participante crearParticipante(String nombre,String correo,String numero,Boolean esJugador){
        Participante participante;
        if(esJugador){
            participante = new Jugador(nombre,correo,numero);
        }else{
            participante = new Equipo(nombre,correo,numero);
        }
        return new ParticipanteBracket(participante);
    }
}
