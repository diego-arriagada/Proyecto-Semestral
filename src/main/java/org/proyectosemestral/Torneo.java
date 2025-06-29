package org.proyectosemestral;

public abstract class Torneo{
    private Boolean iniciado;
    private Lista<Participante> listaParticipantes;
    int tipo = 0;
    public Torneo(int tipo){
        this.tipo = tipo;
        this.iniciado = false;
        listaParticipantes = new Lista();
    }

    public void añadirParticipante(Participante p){
        if(iniciado!=true){
            if (p != null) {
                if (tipo == 1 && p instanceof Jugador) {
                    listaParticipantes.añadirParticipante(p);
                }
                if (tipo == 2 && p instanceof Equipo) {
                    listaParticipantes.añadirParticipante(p);
                } else {
                    //error participante nulo o tipo de torneo equivocado
                }
            }
        }else{
            //error torneo ya iniciado
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


