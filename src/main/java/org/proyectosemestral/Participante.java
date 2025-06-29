package org.proyectosemestral;
import java.util.ArrayList;

public abstract class Participante{;
    private String nombre;
    private String correo;
    private String numero;
    private Stats stats;
    public Participante(String nombre,String correo,String numero){
    this.nombre = nombre;
    this.correo = correo;
    this.numero = numero;
    this.stats = new Stats();
    }

    public String getNombre() {
        return nombre;
    }

    public Stats getStats() {
        return stats;
    }
}
