package GUI;

import org.proyectosemestral.Participante;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelTorneoEliminacionDirecta extends JPanel {
    private List<Participante> participantes;
    private List<JLabel> partidosLabels;
    private int rondas;

    // Cambio en el tipo de parámetro
    public PanelTorneoEliminacionDirecta(List<Participante> participantes) {


        this.participantes = new ArrayList<Participante>(participantes);  // Cambio aquí
        this.rondas = (int) (Math.log(participantes.size()) / Math.log(2));
        this.partidosLabels = new ArrayList<>();

        setLayout(new BorderLayout());
        inicializarUI();
    }

    private void inicializarUI() {
        JPanel panelTorneo = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 20, 5, 20);

        int participantesRestantes = participantes.size();
        int partidosPorRonda = participantesRestantes / 2;
        int rondaActual = 1;

        while (participantesRestantes >= 1) {
            gbc.gridx = rondaActual - 1;
            gbc.gridy = 0;
            gbc.gridheight = participantesRestantes;

            JPanel panelRonda = new JPanel(new GridLayout(partidosPorRonda, 1, 5, 20));
            panelRonda.setBorder(BorderFactory.createTitledBorder("Ronda " + rondaActual));

            for (int i = 0; i < partidosPorRonda; i++) {
                JPanel panelPartido = new JPanel(new BorderLayout());
                panelPartido.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                JLabel labelPartido;
                if (rondaActual == 1) {
                    // Único cambio real en la lógica - usar toString() de Participante
                    Participante p1 = participantes.get(i * 2);
                    Participante p2 = participantes.get(i * 2 + 1);
                    labelPartido = new JLabel(p1.toString() + " vs " + p2.toString(),
                            SwingConstants.CENTER);
                } else {
                    labelPartido = new JLabel("Ganador Partido " + (i*2+1) + " vs Ganador Partido " + (i*2+2),
                            SwingConstants.CENTER);
                }

                panelPartido.add(labelPartido, BorderLayout.CENTER);
                partidosLabels.add(labelPartido);
                panelRonda.add(panelPartido);
            }

            panelTorneo.add(panelRonda, gbc);

            participantesRestantes /= 2;
            partidosPorRonda = participantesRestantes / 2;
            rondaActual++;
        }

        JScrollPane scrollPane = new JScrollPane(panelTorneo);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para controles (sin cambios)
        JPanel panelControles = new JPanel();
        JButton btnActualizar = new JButton("Actualizar Resultados");
        btnActualizar.addActionListener(e -> actualizarResultados());
        panelControles.add(btnActualizar);

        add(panelControles, BorderLayout.SOUTH);
    }

    private void actualizarResultados() {
        // Implementación sin cambios
        JOptionPane.showMessageDialog(this, "Implementa la lógica para actualizar resultados aquí");
    }

    // Metodo para agregar como pestaña (ajustado)
    public static void agregarComoPestana(JFrame framePrincipal, List<Participante> participantes) {
        JTabbedPane tabbedPane = new JTabbedPane();

        // Buscar si ya existe un JTabbedPane
        for (Component c : framePrincipal.getContentPane().getComponents()) {
            if (c instanceof JTabbedPane) {
                tabbedPane = (JTabbedPane) c;
                break;
            }
        }

        // Crear y agregar nuestro panel de torneo
        PanelTorneoEliminacionDirecta panelTorneo = new PanelTorneoEliminacionDirecta(participantes);
        tabbedPane.addTab("Torneo Eliminación Directa", panelTorneo);

        // Si no existía un JTabbedPane, lo agregamos al frame
        if (framePrincipal.getContentPane().getComponentCount() == 0) {
            framePrincipal.getContentPane().add(tabbedPane, BorderLayout.CENTER);
            framePrincipal.revalidate();
        }
    }
}