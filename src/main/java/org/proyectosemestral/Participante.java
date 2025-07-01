package org.proyectosemestral;
import java.util.ArrayList;

public abstract class Participante{;
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
