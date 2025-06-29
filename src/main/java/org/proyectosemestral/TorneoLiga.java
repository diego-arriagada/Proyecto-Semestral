package org.proyectosemestral;

public class TorneoLiga extends Torneo {
    Boolean iniciado = false;
    private Lista listaParticipantes;

    public TorneoLiga(int tipo){
        super(tipo);
        listaParticipantes = new Lista();
    }
}
