package org.proyectosemestral.Fabricas;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

//Patron Factory

/** La interfaz FabricaTorneo define los métodos para crear instancias de Torneo y Participante.
 * Esta interfaz es utilizada por las fábricas concretas FabricaLiga y FabricaBracket, y es la base
 * de la implementación del patrón de diseño Factory Method.
 *
 * El programa decidirá cual de las dos fábricas usar dependiendo del comportamiento del torneo que se desee crear.
 * En ese sentido, Factory Method y Strategy trabajan en conjunto dentro de este proyecto.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public interface FabricaTorneo {
    Torneo crearTorneo(String nombre);
    Participante crearParticipante(String nombre,String correo,String numero);
}
