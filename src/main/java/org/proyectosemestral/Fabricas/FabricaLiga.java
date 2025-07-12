package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Decoradores.ParticipanteBracket;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

/** FabricaLiga es una implementación de la interfaz FabricaTorneo, creando instancias de Torneo y Participante
 * en el caso especifico de una liga.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
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
