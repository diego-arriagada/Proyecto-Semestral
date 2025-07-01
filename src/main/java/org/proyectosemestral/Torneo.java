package org.proyectosemestral;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;

public class Torneo{
    private String nombreTorneo;
    private ComportamientoTorneo comportamiento;
    private Boolean iniciado;
    private Lista<Participante> listaParticipantes;
    public Torneo(String nombreTorneo,ComportamientoTorneo comportamiento){
        this.nombreTorneo = nombreTorneo;
        this.comportamiento = comportamiento;
        this.iniciado = false;
        listaParticipantes = new Lista();
    }

    public int[][] generarPartidos(){
        if(iniciado) {
            return comportamiento.generarPartidos(listaParticipantes);
        }
        else{
            //torneo no iniciado
            return null;
        }
    }


    public void añadirParticipante(Participante p){
        listaParticipantes.añadirParticipante(p);
    }

    public void eliminarParticipante(Participante p){
        if(iniciado!=true) {
            if (p != null && listaParticipantes.getLista().contains(p)) {
                listaParticipantes.eliminarParticipante(p);
            }else{
                //error participante nulo o participante no esta en torneo
            }
        }else{
            //error torneo ya iniciado
        }
    }

    public void iniciarTorneo(){
        if(iniciado == false){
            this.iniciado = true;
        }else{
            //error torneo ya iniciado
        }
    }

    public Boolean buscarParticipante(Participante p){
        return listaParticipantes.buscarParticipante(p);
    }

    public Lista<Participante> getListaParticipantes(){
        return listaParticipantes;
    }

    public int tamañoLista(){
        return listaParticipantes.getLista().size();
    }

    public void limpiarLista(){
        if(iniciado == false){
            getListaParticipantes().getLista().clear();
        }else{
            //error no puedes limpiar lista de torneo ya iniciado
        }
    }
}


