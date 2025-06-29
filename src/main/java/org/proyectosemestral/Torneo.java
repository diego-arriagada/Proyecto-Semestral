package org.proyectosemestral;

public abstract class Torneo{
    private Lista listaParticipantes;
    int tipo;
    public Torneo(int tipo){
        listaParticipantes = new Lista();
    }

    public void añadirParticipante(Participante p){
        if(p!=null) {
            if (tipo == 1 && p instanceof Jugador) {
                listaParticipantes.añadirParticipante(p);
            }
            if (tipo == 2 && p instanceof Equipo) {
                listaParticipantes.añadirParticipante(p);
            } else {
                //error
            }
        }
    }
    public void eliminarParticipante(Participante p){
            listaParticipantes.eliminarParticipante(p);

    }
}


