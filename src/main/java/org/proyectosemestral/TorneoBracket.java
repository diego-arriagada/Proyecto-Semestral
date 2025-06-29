package org.proyectosemestral;

public class TorneoBracket extends Torneo{
    private Boolean iniciado = false;
    private Lista<Participante> listaParticipantes;
    public TorneoBracket(int tipo){
        super(tipo);
        listaParticipantes = new Lista<Participante>();
    }
}

