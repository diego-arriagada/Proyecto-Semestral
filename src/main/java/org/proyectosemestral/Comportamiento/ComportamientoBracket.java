package org.proyectosemestral.Comportamiento;

import org.proyectosemestral.Lista;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;

import java.util.ArrayList;
import java.util.Collections;

public class ComportamientoBracket implements ComportamientoTorneo {

    public ComportamientoBracket() {}

    @Override
    public ArrayList<Partido> generarPartidos(Lista<Participante> participantes, ArrayList<Partido> partidos) {
        //calcula cuantos partidos habran en base a los equipos que hay, comprueba si es viable con la cantidad
        //de equipos dada y hace una mezcla para ir sacando de a pares los equipos, llena la lista de partidos y luego
        //retorna la lista con los enfrentamientos
        int cantidadEquipos = participantes.getLista().size();

        if (cantidadEquipos < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 equipos");
        }

        if (!((cantidadEquipos & (cantidadEquipos - 1)) == 0)) {
            throw new IllegalArgumentException("Se necesita una cantidad que sea potencia de 2 (2, 4, 8, 16, ...)");
        }


        ArrayList<Participante> participantesAleatorios = new ArrayList<>(participantes.getLista());
        Collections.shuffle(participantesAleatorios); // Mezclamos para emparejamientos aleatorios

        int totalPartidos = cantidadEquipos - 1;

        // Generamos los partidos de la primera fase (cuartos, semis, etc.)
        for (int i = 0; i < cantidadEquipos / 2; i++) {
            Participante local = participantesAleatorios.get(2 * i);
            Participante visitante = participantesAleatorios.get(2 * i + 1);
            partidos.add(new Partido(local, visitante));
        }

        return partidos;
    }

    @Override
    public void jugarPartidoSiguiente(ArrayList<Partido> partidos, int partidoPorJugar, int resultado1, int resultado2) {
        if (partidoPorJugar >= partidos.size()) {
            throw new IllegalArgumentException("No hay más partidos disponibles");
        }

        Partido partidoActual = partidos.get(partidoPorJugar);

        if (partidoActual.getGanador() != null) {
            throw new IllegalStateException("Este partido ya ha sido jugado");
        }

        //falta poner el ingreso de datos al programa
        partidoActual.setGolesLocal(resultado1);
        partidoActual.setGolesVisitante(resultado2);
        partidoActual.setGanador();

        // Si es necesario generar nuevos partidos (siguiente fase)
        if (debeGenerarNuevaFase(partidos, partidoPorJugar)) {
            generarSiguienteFase(partidos);
        }
    }

    private boolean debeGenerarNuevaFase(ArrayList<Partido> partidos, int partidoActual) {
        // Verifica si todos los partidos de la fase actual han sido jugados
        int partidosPorFase = partidos.size() - partidoActual;
        for (int i = partidoActual; i < partidos.size(); i++) {
            if (partidos.get(i).getGanador() == null) {
                return false;
            }
        }
        return true;
    }

    private void generarSiguienteFase(ArrayList<Partido> partidos) {
        ArrayList<Participante> ganadores = new ArrayList<>();

        // Obtenemos los ganadores de la fase anterior
        for (Partido p : partidos) {
            if (p.getGanador() != null) {
                ganadores.add(p.getGanador());
            }
        }


        if (ganadores.size() == 1) {
            System.out.println("Campeón: " + ganadores.get(0).getNombre());
            return;
        }

        // genera nuevos partidos de la misma forma
        for (int i = 0; i < ganadores.size() / 2; i++) {
            Participante local = ganadores.get(2 * i);
            Participante visitante = ganadores.get(2 * i + 1);
            partidos.add(new Partido(local, visitante));
        }
    }
}