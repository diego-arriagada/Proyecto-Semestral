package GUI;

import org.proyectosemestral.Comportamiento.ComportamientoTorneo;
import org.proyectosemestral.Comportamiento.ComportamientoLiga;
import org.proyectosemestral.Comportamiento.ComportamientoBracket;
import org.proyectosemestral.Decoradores.ParticipanteLiga;
import org.proyectosemestral.Fabricas.FabricaTorneo;
import org.proyectosemestral.Participante;
import org.proyectosemestral.Partido;
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
    private JButton btnSiguientePartido;
    private JToggleButton btnDatosParticipante;
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

        btnSiguientePartido = new JButton("Partido Siguiente");
        btnSiguientePartido.setToolTipText("Abre la ventana para ingresar resultado del siguiente partido");

        btnDatosParticipante = new JToggleButton("Datos participante");
        btnDatosParticipante.setToolTipText("Despliega los datos del participante seleccionado");
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
        buttonPanel.add(btnDatosParticipante);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        buttonPanel.add(btnComenzarTorneo);
        buttonPanel.add(btnSiguientePartido);

        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    private void setupListeners() {
        btnAñadirParticipante.addActionListener(this::accionAñadirParticipante);
        btnEliminarParticipante.addActionListener(this::accionEliminarParticipante);
        btnComenzarTorneo.addActionListener(this::accionComenzarTorneo);
        btnSiguientePartido.addActionListener(this::accionSiguientePartido);
        btnDatosParticipante.addActionListener(this::accionDatosParticipante);
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

            JLabel ingresarCorreo = new JLabel("Ingrese correo del participante (xxx@yyy.zzz):");
            JTextField tfCorreoParticipante = new JTextField(20);

            JLabel ingresarNumero = new JLabel("Ingrese numero de telefono (9 + 8 digitos):");
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
                    panelCentral.actualizarTablaLiga(torneoActual);
                }else{
                    torneoActual.añadirParticipante(p);
                    panelCentral.actualizarTablaBracket(torneoActual);
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

                actualizarInterfaz();

                JOptionPane.showMessageDialog(null,
                        "Participante eliminado",
                        "Notificacion", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "Debes seleccionar una fila para eliminar.",
                        "Notificacion",JOptionPane.INFORMATION_MESSAGE);
            }

        } else{
            JOptionPane.showMessageDialog(null,
                    "Torneo ya iniciado (No se puede eliminar participante cuando el torneo ha comenzado)",
                    "Notificacion", JOptionPane.INFORMATION_MESSAGE);
        }
    }



    private void accionComenzarTorneo(ActionEvent e) {
        Boolean tamañoCorrecto = torneoActual.getListaParticipantes().getLista().size() >= 2;
        Boolean tamañoCorrectoBracket = ((torneoActual.getListaParticipantes().getLista().size() & torneoActual.getListaParticipantes().getLista().size()-1) == 0);
        if (torneoActual.getComportamiento() instanceof ComportamientoLiga) {


            if (tamañoCorrecto) {
                boolean iniciado = torneoActual.iniciarTorneo();
                if (iniciado) {
                    torneoActual.generarPartidos();
                    panelCentral.actualizarTablaCalendario();
                    JOptionPane.showMessageDialog(null, "Torneo iniciado correctamente",
                            "Notificación", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "El torneo ya esta iniciado",
                            "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El torneo no tiene el tamaño minimo de participantes (dos participantes)",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (torneoActual.getComportamiento() instanceof ComportamientoBracket){
            if (tamañoCorrecto && tamañoCorrectoBracket) {
                boolean iniciado = torneoActual.iniciarTorneo();
                if (iniciado) {
                    torneoActual.generarPartidos();
                    panelCentral.actualizarTablaCalendario();
                    JOptionPane.showMessageDialog(null, "Torneo iniciado correctamente",
                            "Notificación", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "El torneo ya esta iniciado",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El torneo no tiene el tamaño adecuado de participantes (debe ser potencia de 2 mayor o igual a 2)",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    private void accionSiguientePartido(ActionEvent e) {
        // Verificar si hay partidos disponibles
        if (torneoActual.getPartidos() == null || torneoActual.getPartidos().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay partidos programados",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el torneo ha finalizado
        if (torneoActual.isFinalizado()) {
            JOptionPane.showMessageDialog(this, "El torneo ha finalizado",
                    "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Obtener el partido actual
        Partido partidoActual = torneoActual.getPartidos().get(torneoActual.getPartidoActual());
        String nombre1 = partidoActual.getLocal().getNombre();
        String nombre2 = partidoActual.getVisita().getNombre();

        // Crear diálogo para ingresar resultados
        JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Ingresar Resultado", true);
        dialogo.setSize(350, 180);
        dialogo.setLocationRelativeTo(null);
        dialogo.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblP1 = new JLabel(nombre1 + ":");
        JTextField tfP1 = new JTextField();

        JLabel lblP2 = new JLabel(nombre2 + ":");
        JTextField tfP2 = new JTextField();

        panel.add(lblP1);
        panel.add(tfP1);
        panel.add(lblP2);
        panel.add(tfP2);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(ev -> {
            try {
                int resultado1 = Integer.parseUnsignedInt(tfP1.getText().trim());
                int resultado2 = Integer.parseUnsignedInt(tfP2.getText().trim());

                // Manejo de empates en torneos de bracket
                if (torneoActual.getComportamiento() instanceof ComportamientoBracket && resultado1 == resultado2) {
                    manejarEmpateBracket(partidoActual, resultado1, resultado2, dialogo);
                } else {
                    // Jugar partido normal
                    torneoActual.jugarPartidoSiguiente(resultado1, resultado2);
                    actualizarInterfaz();
                    if(torneoActual.isFinalizado()){
                        Participante ganador = torneoActual.getGanador();
                        JOptionPane.showMessageDialog(null, "Torneo finalizado, el ganador es: "+ ganador.getNombre(),
                                "Torneo Finalizado", JOptionPane.INFORMATION_MESSAGE);
                    }
                    dialogo.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogo, "Ingrese números válidos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);

        dialogo.add(panel, BorderLayout.CENTER);
        dialogo.add(panelBoton, BorderLayout.SOUTH);
        dialogo.setVisible(true);
    }

    private void manejarEmpateBracket(Partido partido, int resultado1, int resultado2, JDialog dialogoPadre) {
        String nombre1 = partido.getLocal().getNombre();
        String nombre2 = partido.getVisita().getNombre();

        JDialog dialogoPenales = new JDialog(dialogoPadre, "Ingresar Resultado De Penales", true);
        dialogoPenales.setSize(350, 180);
        dialogoPenales.setLocationRelativeTo(dialogoPadre);
        dialogoPenales.setLayout(new BorderLayout());

        JPanel panelPenales = new JPanel(new GridLayout(3, 2, 10, 10));
        panelPenales.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel lblP1Penales = new JLabel(nombre1 + " (penales):");
        JTextField tfP1Penales = new JTextField();

        JLabel lblP2Penales = new JLabel(nombre2 + " (penales):");
        JTextField tfP2Penales = new JTextField();

        panelPenales.add(lblP1Penales);
        panelPenales.add(tfP1Penales);
        panelPenales.add(lblP2Penales);
        panelPenales.add(tfP2Penales);

        JButton btnGuardarPenales = new JButton("Guardar");
        btnGuardarPenales.addActionListener(e -> {
            try {
                int resultado1Penales = Integer.parseUnsignedInt(tfP1Penales.getText().trim());
                int resultado2Penales = Integer.parseUnsignedInt(tfP2Penales.getText().trim());

                if (resultado1Penales == resultado2Penales) {
                    JOptionPane.showMessageDialog(null, "No puede haber empate en penales",
                            "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Usar el resultado de los penales como marcador final
                    torneoActual.jugarPartidoSiguiente(resultado1Penales,resultado2Penales);
                    actualizarInterfaz();

                    if(torneoActual.isFinalizado()){
                        Participante ganador = torneoActual.getGanador();
                        JOptionPane.showMessageDialog(null, "Torneo finalizado, el ganador es: "+ ganador.getNombre(),
                                "Torneo Finalizado", JOptionPane.INFORMATION_MESSAGE);
                    }
                    dialogoPenales.dispose();
                    dialogoPadre.dispose();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialogoPenales, "Ingrese números válidos",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBotonPenales = new JPanel();
        panelBotonPenales.add(btnGuardarPenales);

        dialogoPenales.add(panelPenales, BorderLayout.CENTER);
        dialogoPenales.add(panelBotonPenales, BorderLayout.SOUTH);
        dialogoPenales.setVisible(true);
    }

    private void actualizarInterfaz() {
        if (torneoActual.getComportamiento() instanceof ComportamientoLiga) {
            panelCentral.actualizarTablaLiga(torneoActual);
        } else if (torneoActual.getComportamiento() instanceof ComportamientoBracket) {
            panelCentral.actualizarTablaBracket(torneoActual);
        }
        panelCentral.actualizarTablaCalendario();
    }

    private void accionDatosParticipante(ActionEvent e) {
        int filaSeleccionada = panelCentral.getTabla().getSelectedRow();
        if (filaSeleccionada != -1){
            Participante p = torneoActual.getListaParticipantes().getLista().get(filaSeleccionada);
            JDialog dialogo = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Datos del participante", true);
            dialogo.setSize(350, 180);
            dialogo.setLocationRelativeTo(null);
            dialogo.setLayout(new BorderLayout());

            JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
            panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            JLabel lblNombre = new JLabel("Nombre: "+p.getNombre());
            JLabel lblCorreo = new JLabel("Correo: "+p.getCorreo());
            JLabel lblNumero = new JLabel("Numero: "+p.getNumero());


            panel.add(lblNombre);
            panel.add(lblCorreo);
            panel.add(lblNumero);
            dialogo.add(panel);
            dialogo.setVisible(true);
        }
    }
}