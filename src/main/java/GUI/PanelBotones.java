package GUI;

import javax.print.attribute.standard.PrinterName;
import javax.swing.*;
import java.awt.*;
import GUI.Factories.ButtonFactory;

public class PanelBotones extends JPanel{
    private JButton botonCrearTorneo;


    public PanelBotones(){
        super();
        this.setLayout(new GridLayout());

        botonCrearTorneo = ButtonFactory.crearBoton("Crear Torneo",null);
        this.add(botonCrearTorneo);

        this.setVisible(true);
    }
}
