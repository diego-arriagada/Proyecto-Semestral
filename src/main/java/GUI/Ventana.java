package GUI;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Fabricas.FabricaBracket;
import org.proyectosemestral.Fabricas.FabricaLiga;
import org.proyectosemestral.Fabricas.FabricaTorneo;
import org.proyectosemestral.Torneo;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private PanelBotones panelBotones;
    private PanelCentral panelCentral;
    private ComportamientoTorneo comportamiento;
    private JMenuBar menuBar;
    private FabricaTorneo fabricaTorneo;
    private Torneo torneoActual;

    public Ventana() {
        initConfiguracionBasica();
        initUI();
    }

    private void initConfiguracionBasica() {
        this.setTitle("Sistema de Gestión de Torneos");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
    }

    private void initUI() {
        initMenuBar();
        mostrarPanelBienvenida();
    }

    private void initMenuBar() {
        menuBar = new JMenuBar();

        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem itemNuevoTorneo = new JMenuItem("Nuevo Torneo");
        JMenuItem itemSalir = new JMenuItem("Salir");

        itemNuevoTorneo.addActionListener(e -> mostrarDialogoNuevoTorneo());
        itemSalir.addActionListener(e -> System.exit(0));

        menuArchivo.add(itemNuevoTorneo);
        menuArchivo.addSeparator();
        menuArchivo.add(itemSalir);

        JMenu menuAyuda = new JMenu("Ayuda");
        JMenuItem itemAcercaDe = new JMenuItem("Acerca de");
        itemAcercaDe.addActionListener(e -> mostrarAcercaDe());
        menuAyuda.add(itemAcercaDe);

        menuBar.add(menuArchivo);
        menuBar.add(menuAyuda);
        this.setJMenuBar(menuBar);
    }

    private void mostrarPanelBienvenida() {
        JPanel panelBienvenida = new JPanel(new GridBagLayout());
        JLabel lbl = new JLabel("<html><h2>Bienvenido al Sistema de Gestión de Torneos</h2><br>Seleccione 'Archivo &rarr; Nuevo Torneo' para comenzar.</html>");
        panelBienvenida.add(lbl);
        this.add(panelBienvenida, BorderLayout.CENTER);
    }

    private void mostrarDialogoNuevoTorneo() {
        JDialog dialogo = new JDialog(this, "Nuevo Torneo", true);
        dialogo.setLayout(new BorderLayout());
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(this);

        JPanel panelContenido = new JPanel(new GridLayout(6, 1, 10, 10));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblNombreTorneo = new JLabel("Ingrese nombre del torneo:");
        JTextField tfNombreTorneo = new JTextField(20);

        JLabel lblTitulo = new JLabel("Seleccione el tipo de torneo:");
        JRadioButton rbLiga = new JRadioButton("Torneo de Liga", true);
        JRadioButton rbEliminatorio = new JRadioButton("Torneo Eliminatorio");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbLiga);
        grupo.add(rbEliminatorio);

        JButton btnCrear = new JButton("Crear");
        btnCrear.addActionListener(e -> {
            String nombreTorneo = tfNombreTorneo.getText();
            if (nombreTorneo.isEmpty()) {
                JOptionPane.showMessageDialog(dialogo, "Debes ingresar un nombre para el torneo.");
                return;
            }
            if (rbLiga.isSelected()) {
                this.comportamiento = new ComportamientoLiga();
                this.fabricaTorneo = new FabricaLiga();

            } else {
                this.comportamiento = new ComportamientoBracket();
                this.fabricaTorneo = new FabricaBracket();

            }
            torneoActual = fabricaTorneo.crearTorneo(nombreTorneo);
            reiniciarPaneles();
            dialogo.dispose();
        });

        panelContenido.add(lblNombreTorneo);
        panelContenido.add(tfNombreTorneo);
        panelContenido.add(lblTitulo);
        panelContenido.add(rbLiga);
        panelContenido.add(rbEliminatorio);
        panelContenido.add(btnCrear);

        dialogo.add(panelContenido, BorderLayout.CENTER);
        dialogo.setVisible(true);
    }

    private void mostrarAcercaDe() {
        JOptionPane.showMessageDialog(this,
                "Sistema de Gestión de Torneos\nVersión 1.0\n© 2023",
                "Acerca de",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void reiniciarPaneles() {
        // Elimina todos los componentes (incluye panel bienvenida)
        this.getContentPane().removeAll();

        // Si quieres volver a mostrar la barra de menús después de removeAll
        this.setJMenuBar(menuBar);

        panelCentral = new PanelCentral(torneoActual, comportamiento);
        this.add(panelCentral, BorderLayout.CENTER);

        panelBotones = new PanelBotones(panelCentral,fabricaTorneo);
        this.add(panelBotones, BorderLayout.EAST);

        this.revalidate();
        this.repaint();
    }

    public Torneo getTorneoActual() {
        return torneoActual;
    }

    public FabricaTorneo getFabricaTorneo() {
        return fabricaTorneo;
    }
}