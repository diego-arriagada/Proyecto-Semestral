package org.proyectosemestral;

public class Jugador extends Participante {
    private Stats stats;
    public Jugador(String nombre,String correo,String numero){
        super(nombre,correo,numero);
        stats = new Stats();
    }
}
