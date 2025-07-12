package org.proyectosemestral;
import java.lang.classfile.attribute.MethodParameterInfo;
import java.util.ArrayList;

/** La clase Lista representa una coleccion de participantes en un torneo, siendo la base para la gestion de los mismos.
 * El objetivo de esta clase es permitir la adición, eliminación y búsqueda de participantes, para finalmente conseguir
 * que un Torneo esté compuesto por su lista de participantes y no por individuos.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
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
