package org.proyectosemestral;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/** Clase principal que representa un torneo.
 *
 * Esta clase permite gestionar un torneo, incluyendo la adición de participantes,
 * la generación de partidos, el inicio del torneo y la gestión de los resultados de los partidos.
 * La clase utiliza el patrón Strategy para definir diferentes comportamientos de torneo,
 * como el comportamiento de un torneo tipo bracket o liga.
 *
 * @author Diego Arriagada
 * @author Victor Galaz
 * @author Matias Catril
 * @version 1.0
 */
public class Torneo{
    private String nombreTorneo;
    private ComportamientoTorneo comportamiento;
    private Boolean iniciado;
    private Boolean partidosGenerados;
    private Lista<Participante> listaParticipantes;
    private ArrayList<Partido> partidos;
    private int partidoActual;
    private DefaultTableModel modeloTabla;
    private DefaultTableModel modeloCalendario;
    private int fase;
    /** Constructor de la clase Torneo.
     *
     * @param nombreTorneo Nombre del torneo.
     * @param comportamiento Comportamiento del torneo, que define cómo se generan los partidos y se manejan los resultados (bracket o liga).
     */
    public Torneo(String nombreTorneo,ComportamientoTorneo comportamiento){
        this.nombreTorneo = nombreTorneo;
        this.comportamiento = comportamiento;
        this.iniciado = false;
        this.partidosGenerados = false;
        this.partidoActual = 0;
        this.fase = 0;
        listaParticipantes = new Lista();
    }

    /** Metodo para generar los partidos del torneo.
     *
     * Este metodo genera los partidos del torneo utilizando el comportamiento definido (bracket o liga).
     * Debe ser llamado después de iniciar el torneo y antes de jugar los partidos.
     */
    public void generarPartidos(){
        if(iniciado && !partidosGenerados) {
            ArrayList<Partido> partidos = new ArrayList<>();
            this.partidos = comportamiento.generarPartidos(listaParticipantes,partidos);
            this.partidosGenerados = true;
        }
        else{
            //torneo no iniciado
        }
    }

    /** Metodo para jugar el siguiente partido del torneo.
     *
     * Este metodo permite jugar el siguiente partido del torneo, actualizando los resultados y avanzando al siguiente partido.
     * Debe ser llamado después de que los partidos hayan sido generados.
     * Al igual que todos los metodos de Torneo, su funcionamiento depende del comportamiento definido (bracket o liga).
     *
     * @param resultado1 Resultado del primer participante (local).
     * @param resultado2 Resultado del segundo participante (visitante).
     */
    public void jugarPartidoSiguiente(int resultado1, int resultado2){
        if(iniciado){
            comportamiento.jugarPartidoSiguiente(this, partidos,partidoActual,resultado1,resultado2);
            this.partidoActual++;
        }
    }

    /** Metodo para añadir un participante al torneo.
     *
     * Este metodo permite añadir un participante al torneo, siempre y cuando el torneo no haya sido iniciado.
     * Si el torneo ya ha sido iniciado, no se pueden añadir más participantes.
     *
     * @param p Participante a añadir al torneo.
     * @return true si el participante fue añadido correctamente, false si el torneo ya ha sido iniciado.
     */
    public Boolean añadirParticipante(Participante p){
        if(!iniciado){
            listaParticipantes.añadirParticipante(p);
            return true;
        }else{
            return false;
        }

    }

    /** Metodo para eliminar un participante del torneo.
     *
     *
     * @param p Participante a eliminar del torneo.
     */
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

    /** Metodo para iniciar el torneo.
     *
     * Este metodo inicia el torneo, permitiendo que se generen los partidos y se jueguen.
     * Si el torneo ya ha sido iniciado, no se puede volver a iniciar.
     *
     * @return true si el torneo fue iniciado correctamente, false si ya estaba iniciado.
     */
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

    public int getPartidoActual(){
        return partidoActual;
    }

    public ArrayList<Partido> getPartidos(){
        return partidos;
    }

    public void setModeloCalendario(DefaultTableModel modeloCalendario){
        this.modeloCalendario = modeloCalendario;
    }

    public void aumentarPartidoActual(){
        this.partidoActual++;
    }

    public DefaultTableModel getModeloCalendario(){
        return modeloCalendario;
    }
}
