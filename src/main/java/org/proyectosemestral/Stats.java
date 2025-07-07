package org.proyectosemestral;

public class Stats{
    private int puntos;
    private int partidosJugados;
    private int victorias;
    private int derrotas;
    private int empates;
    private int goles;
    public Stats(){
        this.puntos=0;
        this.partidosJugados=0;
        this.victorias=0;
        this.derrotas=0;
        this.empates=0;
        this.goles=0;
    }

    public void setPuntos(int n){
        this.puntos = n;
    }
    public void setPartidosJugados(int n){
        this.partidosJugados = n;
    }
    public void setVictorias(int n){
        this.victorias = n;
    }
    public void setDerrotas(int n){
        this.derrotas = n;
    }
    public void setEmpates(int n){
        this.empates = n;
    }
    public void setGoles(int n){
        this.goles = n;
    }

    public int getPuntos(){
        return puntos;
    }
    public int getPartidosJugados(){
        return partidosJugados;
    }
    public int getVictorias(){
        return victorias;
    }
    public int getDerrotas(){
        return derrotas;
    }
    public int getEmpates(){
        return empates;
    }
    public int getGoles(){
        return goles;
    }

}
