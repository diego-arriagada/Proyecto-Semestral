package org.proyectosemestral;
import java.util.ArrayList;

public class Lista<Participante>{
    ArrayList<Participante> lista;

    public Lista(){
    lista = new ArrayList<Participante>();
    }
    public void añadirParticipante(Participante p){
        if(p!=null){
        lista.add(p);
        }
        if(lista.contains(p)){
            //error
        }
    }
    public void eliminarParticipante(Participante p){
        if(lista.contains(p)){
            lista.remove(p);
        }else{
            //error
        }
    }
}
