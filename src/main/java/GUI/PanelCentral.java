package GUI;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;
import org.proyectosemestral.Torneo;

import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelCentral extends JPanel {
    private ComportamientoTorneo comportamiento;
    private JTable tabla;
    private JTable tablaCalendario;
    private JPanel panelContenido;
    private CardLayout cardLayout;
    private Torneo torneoActual;

    public PanelCentral(Torneo torneoActual,ComportamientoTorneo comportamiento) {
        this.comportamiento = comportamiento;
        this.torneoActual = torneoActual;
        setLayout(new BorderLayout());

        cardLayout = new CardLayout();
        panelContenido = new JPanel(cardLayout);

        initUI();
        add(panelContenido, BorderLayout.CENTER);
    }

    private void initUI() {
        resetearVista();  // Usamos nuestro nuevo metodo para la config inicial
    }

    public void resetearVista() {
        panelContenido.removeAll();

        if (comportamiento instanceof ComportamientoLiga) {
            panelContenido.add(crearPanelLiga(), "LIGA");
        } else if (comportamiento instanceof ComportamientoBracket) {
            panelContenido.add(crearPanelBracket(), "BRACKET");
        }
        cardLayout.show(panelContenido, comportamiento instanceof ComportamientoLiga ? "LIGA" : "BRACKET");
        revalidate();
        repaint();
    }

    private JPanel crearPanelLiga() {
        // Panel principal con BorderLayout
        JPanel panel = new JPanel(new BorderLayout());

        // Nombre del torneo arriba
        JLabel nombreTorneo = new JLabel(torneoActual.toString());
        nombreTorneo.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(nombreTorneo, BorderLayout.NORTH);

        // Pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña de clasificación
        tabla = new JTable(crearModeloTablaLiga(torneoActual));
        JScrollPane scrollClasificacion = new JScrollPane(tabla);
        tabbedPane.addTab("Clasificación", scrollClasificacion);

        // Pestaña de calendario
        tablaCalendario = new JTable(crearTablaCalendario(torneoActual));
        JScrollPane scrollCalendario = new JScrollPane(tablaCalendario);
        tabbedPane.addTab("Calendario", scrollCalendario);

        // Añadir pestañas al centro del panel
        panel.add(tabbedPane, BorderLayout.CENTER);

        return panel;
    }

    private DefaultTableModel crearModeloTablaLiga(Torneo torneo) {
        String[] columnas = {"Nombre","PJ","G", "E", "P", "GF", "GC", "DG", "PTS"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Agrega los participantes actuales
        for (Participante p : torneo.getListaParticipantes().getLista()) {
            if(p instanceof ParticipanteLiga){
                ParticipanteLiga pL = (ParticipanteLiga)p;
                Object[] fila = {pL.getNombre(), pL.getStats().getPartidosJugados(), pL.getStats().getVictorias(), pL.getStats().getEmpates(), pL.getStats().getDerrotas(), pL.getStats().getGoles(), pL.getStats().getGolesEnContra(), pL.getStats().getGoles()-pL.getStats().getGolesEnContra(), pL.getStats().getPuntos()};
                modelo.addRow(fila);
            }

        }
        this.torneoActual.setModeloTabla(modelo);
        return modelo;
    }
    public void actualizarTablaLiga(Torneo torneo) {
        DefaultTableModel modelo = (DefaultTableModel) getTabla().getModel();
        // Limpia la tabla antes de llenarla de nuevo
        modelo.setRowCount(0);
        torneo.getListaParticipantes().getLista().sort(null);
        Collections.reverse(torneo.getListaParticipantes().getLista());
        for (Participante p : torneo.getListaParticipantes().getLista()) {
            if (p instanceof ParticipanteLiga) {
                ParticipanteLiga pL = (ParticipanteLiga) p;
                Object[] fila = {pL.getNombre(),
                        pL.getStats().getPartidosJugados(),
                        pL.getStats().getVictorias(),
                        pL.getStats().getEmpates(),
                        pL.getStats().getDerrotas(),
                        pL.getStats().getGoles(),
                        pL.getStats().getGolesEnContra(),
                        pL.getStats().getGoles()-pL.getStats().getGolesEnContra(),
                        pL.getStats().getPuntos()
                };
                modelo.addRow(fila);
            } else {
                // Si el participante no es de tipo Liga, muestra solo datos básicos
                Object[] fila = {p.getNombre(), "-", "-"};
                modelo.addRow(fila);
            }
        }
    }

    private DefaultTableModel crearTablaCalendario(Torneo torneo) {
        String[] columnas = {"Jornada","Local","Resultado","Visitante"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        int i = 1;
        if(torneoActual.getPartidos()!=null) {
            for (Partido p : torneoActual.getPartidos()) {
                Object[] fila = {i, p.getLocal().getNombre(), p.getGolesLocal() + "-" + p.getGolesVisitante(), p.getVisita().getNombre()};
                modelo.addRow(fila);
                i++;
            }
        }
        torneoActual.setModeloCalendario(modelo);
        return modelo;
    }

    public void actualizarTablaCalendario(){
        DefaultTableModel modelo = (DefaultTableModel) getTablaCalendario().getModel();
        // Limpia la tabla antes de llenarla de nuevo
        modelo.setRowCount(0);

        int i = 1;
        for( Partido p : torneoActual.getPartidos()){
            Object[] fila = {i,p.getLocal().getNombre(),p.getGolesLocal() + "-" + p.getGolesVisitante(),p.getVisita().getNombre()};
            modelo.addRow(fila);
            i++;
        }
    }

    private JPanel crearPanelBracket() {
        JPanel panel = new JPanel(new BorderLayout());
        JTextArea areaBracket = new JTextArea(generarTextoBracket());
        areaBracket.setEditable(false);
        panel.add(new JScrollPane(areaBracket), BorderLayout.CENTER);
        return panel;
    }

    private String generarTextoBracket() {
        return "Ronda 1:\nEquipo A vs Equipo B\nEquipo C vs Equipo D\n\n" +
                "Ronda 2:\nGanador 1 vs Ganador 2\n\n" +
                "Final:\nGanador R2 vs Ganador R3";
    }

    public void setComportamiento(ComportamientoTorneo comportamiento) {
        this.comportamiento = comportamiento;
        resetearVista();  // Reutilizamos nuestro método para actualizar la vista
    }

    public Torneo getTorneoActual(){
        return torneoActual;
    }

    public JTable getTabla(){
        return tabla;
    }

    public JTable getTablaCalendario(){
        return tablaCalendario;
    }
}