package org.proyectosemestral.Comportamiento;

    // Patron Strategy

import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;

import java.util.ArrayList;

public interface ComportamientoTorneo {
    ArrayList generarPartidos(Lista<Participante> lista);
    void calcularResultado();
}
