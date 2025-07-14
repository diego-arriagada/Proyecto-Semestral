package org.proyectosemestral;


/** Clase que representa un partido de futbol entre dos equipos participantes.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class Partido{
    private Participante p1;
    private Participante p2;
    private int golesLocal;
    private int golesVisitante;
    private boolean ganadorLocal;
    private boolean partidoJugado;
    private Participante ganador;
    //true si gana el local, false si gana el visitante
    public Partido(Participante p1,Participante p2){
        this.p1 = p1;
        this.p2 = p2;
        this.partidoJugado = false;
    }

    public Participante getLocal(){
        return p1;
    }

    public Participante getVisita(){
        return p2;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesLocal(int goles) {
        this.golesLocal = goles;
    }

    public void setGolesVisitante(int goles){
        this.golesVisitante = goles;
    }

    public void setGanador(){
        // Determinamos el ganador
        if (golesLocal > golesVisitante) {
            this.ganadorLocal = true;
        } else if (golesLocal < golesVisitante) {
            this.ganadorLocal = false;
        } else { //falta definir que pasa en empate

        }
    }

    public boolean getPartidoJugado(){
        return partidoJugado;
    }

    public void jugarPartido(){
        this.partidoJugado = true;
    }

    public Participante getGanador(){
        if (partidoJugado == false){
            return null;
        }
        if (ganadorLocal == true){
            return p1;
        }
        else{
            return p2;
        }
    }
}
