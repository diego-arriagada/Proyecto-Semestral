package org.proyectosemestral;

public class Partido{
    private Participante p1;
    private Participante p2;
    public Partido(Participante p1,Participante p2){
        this.p1 = p1;
        this.p2 = p2;
    }

    public Participante getP1() {
        return p1;
    }

    public Participante getP2() {
        return p2;
    }
}
