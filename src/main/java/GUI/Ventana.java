package GUI;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private PanelBotones panelBotones;
    private PanelCentral panelCentral;

    public Ventana() {
        this.setTitle("Torneos");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);

        this.setLayout(new BorderLayout());

        this.

        setVisible(true);
    }

    private void initUI() {

    }

    private void initPaneles() {
        panelCentral = new PanelCentral();
        add(panelCentral, BorderLayout.CENTER);

        panelBotones = new PanelBotones();
        add(panelBotones, BorderLayout.EAST);
    }
}