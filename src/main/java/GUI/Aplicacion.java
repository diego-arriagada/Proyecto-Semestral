package GUI;

import org.proyectosemestral.Fabricas.FabricaLiga;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Torneo;

public class Aplicacion {
    public static void main(String[] args){
        Ventana v = new Ventana();
        FabricaLiga fl = new FabricaLiga();
        Torneo ligahola = fl.crearTorneo("hola");
        Participante p = fl.crearParticipante("hola","hola","hola",true);

        ligahola.añadirParticipante(p);

    }
}
