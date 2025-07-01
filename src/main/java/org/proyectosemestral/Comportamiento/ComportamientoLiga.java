package org.proyectosemestral.Comportamiento;

import org.proyectosemestral.Participante;
import org.proyectosemestral.Lista;
import java.util.Collections;
import java.util.List;

import java.util.ArrayList;

public class ComportamientoLiga implements ComportamientoTorneo {

    public ComportamientoLiga(){}

    @Override
    public int[][] generarPartidos(Lista<Participante> lista){
        int cantidadEquipos = lista.getLista().size();

        if (cantidadEquipos < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 equipos");
        }

        boolean tieneDescanso = cantidadEquipos % 2 != 0;
        int totalEquipos = tieneDescanso ? cantidadEquipos + 1 : cantidadEquipos;
        int totalEnfrentamientos = cantidadEquipos * (cantidadEquipos - 1);
        int[][] matriz = new int[2][totalEnfrentamientos];
        int index = 0;

        // Creamos lista de IDs (1..n)
        List<Integer> equipos = new ArrayList<>();
        for (int i = 1; i <= cantidadEquipos; i++) {
            equipos.add(i);
        }
        if (tieneDescanso) {
            equipos.add(-1); // ID para descanso
        }

        // Doble vuelta (ida y vuelta)
        for (int vuelta = 0; vuelta < 2; vuelta++) {
            for (int jornada = 0; jornada < totalEquipos - 1; jornada++) {
                for (int i = 0; i < totalEquipos / 2; i++) {
                    int local = equipos.get(i);
                    int visitante = equipos.get(totalEquipos - 1 - i);

                    // Alternar localía en la vuelta de regreso
                    if (vuelta == 1) {
                        int temp = local;
                        local = visitante;
                        visitante = temp;
                    }

                    // Solo añadir si no es descanso
                    if (local != -1 && visitante != -1) {
                        matriz[0][index] = local;
                        matriz[1][index] = visitante;
                        index++;
                    }
                }

                // Rotar equipos (excepto el primero)
                Collections.rotate(equipos.subList(1, totalEquipos), 1);
            }
        }

        return matriz;

    }

    @Override
    public void calcularResultado(){}
}
