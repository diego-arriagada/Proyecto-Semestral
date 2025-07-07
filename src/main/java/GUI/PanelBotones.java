package GUI;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Fabricas.FabricaTorneo;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Stats;
import org.proyectosemestral.Torneo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

import static org.proyectosemestral.Validador.esCorreoValido;
import static org.proyectosemestral.Validador.esTelefonoValido;

public class PanelBotones extends JPanel {
    private final PanelCentral panelCentral;
    private JButton btnAñadirParticipante;
    private JButton btnEliminarParticipante;
    private JButton btnComenzarTorneo;
    private JButton btnReporte;
    private JToggleButton btnCambiarVista;
    private Torneo torneoActual;
    private FabricaTorneo fabricaTorneo;
    public PanelBotones(PanelCentral panelCentral, FabricaTorneo fabricaTorneo) {
        this.panelCentral = panelCentral;
        this.torneoActual = panelCentral.getTorneoActual();
        this.fabricaTorneo = fabricaTorneo;
        initComponents();
        setupLayout();
        setupListeners();
    }

    private void initComponents() {
        btnAñadirParticipante = new JButton("Añadir participante");
        btnAñadirParticipante.setToolTipText("Añade un nuevo participante al torneo actual");

        btnEliminarParticipante = new JButton("Eliminar participante");
        btnEliminarParticipante.setToolTipText("Elimina el participante de la tabla seleccionado");

        btnComenzarTorneo = new JButton("Comenzar torneo");
        btnComenzarTorneo.setToolTipText("Inicia el torneo(Esto bloqueara la lista de participantes)");

        btnReporte = new JButton("Reporte");
        btnReporte.setToolTipText("Generar reporte del torneo");

        btnCambiarVista = new JToggleButton("Modo Eliminatorio");
        btnCambiarVista.setToolTipText("Cambiar entre vista de Liga y Eliminatoria");
    }

    private void setupLayout() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(240, 240, 240));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 5, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        buttonPanel.add(btnAñadirParticipante);
        buttonPanel.add(btnEliminarParticipante);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(btnCambiarVista);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(btnComenzarTorneo);
        buttonPanel.add(btnReporte);

        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    private void setupListeners() {
        btnAñadirParticipante.addActionListener(this::accionAñadirParticipante);
        btnEliminarParticipante.addActionListener(this::accionEliminarParticipante);
        btnComenzarTorneo.addActionListener(this::accionComenzarTorneo);
        btnReporte.addActionListener(this::accionReporte);
        btnCambiarVista.addActionListener(this::accionCambiarVista);
    }

    private void accionAñadirParticipante(ActionEvent e) {
        if(!torneoActual.getIniciado()){
            JDialog dialogo = new JDialog();
            dialogo.setLayout(new BorderLayout());
            dialogo.setSize(400, 300);
            dialogo.setLocationRelativeTo(this);
            dialogo.setVisible(true);

            JPanel panelContenido = new JPanel(new GridLayout(7, 1, 10, 10));
            panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel ingresarNombre = new JLabel("Ingrese nombre de participante:");
            JTextField tfNombreParticipante = new JTextField(20);

            JLabel ingresarCorreo = new JLabel("Ingrese correo del participante:");
            JTextField tfCorreoParticipante = new JTextField(20);

            JLabel ingresarNumero = new JLabel("Ingrese numero del participante:");
            JTextField tfNumeroParticipante = new JTextField(12);


            JButton btnAñadirParticipante = new JButton("Añadir");
            btnAñadirParticipante.addActionListener(e1 -> {
                String nombreParticipante = tfNombreParticipante.getText();
                String correoParticipante = tfCorreoParticipante.getText();
                String numeroParticipante = tfNumeroParticipante.getText();
                if(!esCorreoValido(correoParticipante)){
                    JOptionPane.showMessageDialog(dialogo, "Debes ingresar un correo valido");
                    return;
                } else if(!esTelefonoValido(numeroParticipante)){
                    JOptionPane.showMessageDialog(dialogo, "Debes ingresar un numero valido");
                    return;
                }
                Participante p = fabricaTorneo.crearParticipante(nombreParticipante,correoParticipante,numeroParticipante);
                if(p instanceof ParticipanteLiga){
                    Stats stats = ((ParticipanteLiga) p).getStats();
                    torneoActual.añadirParticipante(p);
                    DefaultTableModel modelo = (DefaultTableModel)torneoActual.getModeloTabla();
                    modelo.addRow(new Object[]{p.getNombre(),stats.getPuntos(),stats.getPartidosJugados()});
                }else{

                }

                dialogo.dispose();
                JOptionPane.showMessageDialog(null,
                        "Participante añadido",
                        "Notificacion", JOptionPane.INFORMATION_MESSAGE);


            });

            dialogo.setLocationRelativeTo(null);
            panelContenido.add(ingresarNombre);
            panelContenido.add(tfNombreParticipante);
            panelContenido.add(ingresarCorreo);
            panelContenido.add(tfCorreoParticipante);
            panelContenido.add(ingresarNumero);
            panelContenido.add(tfNumeroParticipante);
            panelContenido.add(btnAñadirParticipante);
            dialogo.add(panelContenido,BorderLayout.CENTER);
            dialogo.setVisible(true);
            panelContenido.setVisible(true);


            panelCentral.resetearVista();
        } else{
            JOptionPane.showMessageDialog(null,
                    "Torneo ya iniciado (No se puede añadir participante cuando el torneo ha comenzado)",
                    "Notificacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    private void accionEliminarParticipante(ActionEvent e) {
        if(!torneoActual.getIniciado()){
            int filaSeleccionada = panelCentral.getTabla().getSelectedRow();
            if (filaSeleccionada != -1) {
                torneoActual.getListaParticipantes().getLista().remove(filaSeleccionada);

                DefaultTableModel modelo = (DefaultTableModel) panelCentral.getTabla().getModel();
                modelo.removeRow(filaSeleccionada);

            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar una fila para eliminar.",
                        "Notificacion",JOptionPane.INFORMATION_MESSAGE);
            }

            JOptionPane.showMessageDialog(null,
                    "Participante eliminado",
                    "Notificacion", JOptionPane.INFORMATION_MESSAGE);
        } else{
            JOptionPane.showMessageDialog(null,
                    "Torneo ya iniciado (No se puede eliminar participante cuando el torneo ha comenzado)",
                    "Notificacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    private void accionComenzarTorneo(ActionEvent e) {
        boolean iniciado = torneoActual.iniciarTorneo();
        if (iniciado) {
            JOptionPane.showMessageDialog(this, "Torneo iniciado correctamente",
                    "Notificación", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "El torneo ya esta iniciado",
                    "Notificacion", JOptionPane.WARNING_MESSAGE);
        }
    }


    private void accionReporte(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Reporte generado",
                "Reporte", JOptionPane.INFORMATION_MESSAGE);
    }

    private void accionCambiarVista(ActionEvent e) {
        if (btnCambiarVista.isSelected()) {
            btnCambiarVista.setText("Modo Liga");
            panelCentral.setComportamiento(new ComportamientoBracket());
        } else {
            btnCambiarVista.setText("Modo Eliminatorio");
            panelCentral.setComportamiento(new ComportamientoLiga());
        }
    }
}