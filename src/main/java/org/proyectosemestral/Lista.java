package org.proyectosemestral;
import java.lang.classfile.attribute.MethodParameterInfo;
import java.util.ArrayList;

public class Lista<Participante>{
    ArrayList<Participante> lista;

    public Lista(){
    lista = new ArrayList<Participante>();
    }
    public void añadirParticipante(Participante p){
        if(p!=null){
            if(lista.contains(p)){
                //error
            }else{
                lista.add(p);
            }
        }

    }
    public void eliminarParticipante(Participante p){
        if(lista.contains(p)){
            lista.remove(p);
        }else{
            //error
        }
    }
    public ArrayList<Participante> getLista(){
        return lista;
    }
    public Boolean buscarParticipante(Participante p){
        return lista.contains(p);
    }
}
