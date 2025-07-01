package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Equipo;
import org.proyectosemestral.Jugador;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

public class FabricaLiga implements FabricaTorneo {

    @Override
    public Torneo crearTorneo(String nombre){
        return new Torneo(nombre,new ComportamientoLiga());
    }

    @Override
    public Participante crearParticipante(String nombre,String correo,String numero,Boolean esJugador){
        Participante participante;
        if(esJugador){
            participante = new Jugador(nombre,correo,numero);
        }else{
            participante = new Equipo(nombre,correo,numero);
        }
        return new ParticipanteLiga(participante);
    }
}
