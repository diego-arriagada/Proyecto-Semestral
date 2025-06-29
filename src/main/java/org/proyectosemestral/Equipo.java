package org.proyectosemestral;

public class Equipo extends Participante {
    private Stats stats;

    public Equipo(String nombre,String correo,String numero){
        super(nombre,correo,numero);
        stats = new Stats();
    }
}
