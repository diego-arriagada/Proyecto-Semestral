package org.proyectosemestral.Comportamiento;

    // Patron Strategy

import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;

import java.util.ArrayList;

public interface ComportamientoTorneo {
    ArrayList<Partido> generarPartidos(Lista<Participante> participantes,ArrayList<Partido> partidos);
    void jugarPartidoSiguiente(ArrayList<Partido> partidos,int partidoActual);
}
