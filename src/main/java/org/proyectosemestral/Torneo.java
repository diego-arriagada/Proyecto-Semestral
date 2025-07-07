package org.proyectosemestral;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class Torneo{
    private String nombreTorneo;
    private ComportamientoTorneo comportamiento;
    private Boolean iniciado;
    private Boolean partidosGenerados;
    private Lista<Participante> listaParticipantes;
    private ArrayList<Partido> partidos;
    private int partidoActual;
    private DefaultTableModel modeloTabla;

    public Torneo(String nombreTorneo,ComportamientoTorneo comportamiento){
        this.nombreTorneo = nombreTorneo;
        this.comportamiento = comportamiento;
        this.iniciado = false;
        this.partidosGenerados = false;
        this.partidoActual = 0;
        listaParticipantes = new Lista();
    }

    public void generarPartidos(){
        if(iniciado && !partidosGenerados) {
            partidos = comportamiento.generarPartidos(listaParticipantes,partidos);
            this.partidosGenerados = true;
        }
        else{
            //torneo no iniciado
        }
    }
    public void jugarPartidoSiguiente(){
        if(iniciado){
            comportamiento.jugarPartidoSiguiente(partidos,partidoActual);
        }
    }



    public Boolean añadirParticipante(Participante p){
        if(!iniciado){
            listaParticipantes.añadirParticipante(p);
            return true;
        }else{
            return false;
        }

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

    public boolean iniciarTorneo() {
        if (iniciado) {
            return false;
        }else{
        this.iniciado = true;
        return true;
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
    public ComportamientoTorneo getComportamiento(){
        return comportamiento;
    }

    public Boolean getIniciado() {
        return iniciado;
    }

    @Override
    public String toString(){
        return nombreTorneo;
    }

    public void setModeloTabla(DefaultTableModel modeloTabla) {
        this.modeloTabla = modeloTabla;
    }
    public DefaultTableModel getModeloTabla(){
        return modeloTabla;
    }
}


