package org.proyectosemestral.Decoradores;

import org.proyectosemestral.Participante;

//Patron Decorator

/** DecoradorParticipante es una clase abstracta que extiende la clase Participante.
 * Esta clase sirve como base para los decoradores de participantes, permitiendo agregar
 * funcionalidades adicionales a los participantes sin modificar su estructura original.
 *
 * Es la clase principal en la aplicación del patrón de diseño Decorator, y se extiende en dos
 * subclases concretas para decorar participantes en los dos tipos de torneo: Bracket y Liga.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public abstract class DecoradorParticipante extends Participante{
    protected Participante participanteDecorado;

    public DecoradorParticipante(Participante participante){
        super(participante.getNombre(), participante.getCorreo(), participante.getNumero());
        this.participanteDecorado = participante;
    }
}
