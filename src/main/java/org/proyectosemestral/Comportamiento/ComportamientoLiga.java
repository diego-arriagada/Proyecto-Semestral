package org.proyectosemestral.Comportamiento;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Lista;
import org.proyectosemestral.Partido;

import java.util.Collections;
import java.util.List;

import java.util.ArrayList;

public class ComportamientoLiga implements ComportamientoTorneo {

    public ComportamientoLiga(){}

    @Override
    public ArrayList<Partido> generarPartidos(Lista<Participante> participantes,ArrayList<Partido> partidos) {
        int n = participantes.getLista().size();

        if (n < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 equipos");
        }

        int totalPartidos = n * (n - 1);
        int[][] matriz = new int[2][totalPartidos];
        int partidoActual = 0;

        // Primera vuelta (ida)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matriz[0][partidoActual] = i + 1;  // Local (equipo i+1)
                matriz[1][partidoActual] = j + 1;  // Visitante (equipo j+1)
                partidoActual++;
            }
        }

        // Segunda vuelta (vuelta)
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matriz[0][partidoActual] = j + 1;  // Invertimos localía
                matriz[1][partidoActual] = i + 1;
                partidoActual++;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                partidos.add(new Partido(participantes.getLista().get(matriz[0][partidoActual]),participantes.getLista().get(matriz[1][partidoActual])));
                partidoActual++;
            }
        }
        return partidos;
    }

    @Override
    public void jugarPartidoSiguiente(ArrayList<Partido> partidos,int partidoSiguiente){

    }
}
