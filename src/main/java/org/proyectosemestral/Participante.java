package org.proyectosemestral;
import java.util.ArrayList;

/** Clase generica que representa a un participante de un torneo. Su principal funcion es proveer una base a ser
 * decorada dependiendo al formato del torneo en el que participe.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class Participante{;
    private String nombre;
    private String correo;
    private String numero;
    public Participante(String nombre,String correo,String numero){
    this.nombre = nombre;
    this.correo = correo;
    this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo(){
        return correo;
    }

    public String getNumero(){
        return numero;
    }

}
