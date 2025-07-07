package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

    //Patron Factory

public interface FabricaTorneo {
    Torneo crearTorneo(String nombre);
    Participante crearParticipante(String nombre,String correo,String numero);
}
