package org.proyectosemestral.Comportamiento;

import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;

import java.util.ArrayList;

public class ComportamientoBracket implements ComportamientoTorneo {

    public ComportamientoBracket(){}

    @Override
    public ArrayList<Partido> generarPartidos(Lista<Participante> participantes,ArrayList<Partido> partidos){

        return null;
    }

    @Override
    public void jugarPartidoSiguiente(ArrayList<Partido> partidos,int partidoSiguiente){}
}
