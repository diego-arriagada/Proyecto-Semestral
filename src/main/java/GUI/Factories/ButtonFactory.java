package GUI.Factories;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ButtonFactory {
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 10);
    private static final Color COLOR = Color.WHITE;
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Dimension BUTTON_SIZE = new Dimension(60, 20);

    public static JButton crearBoton(String textoBoton,ActionListener listenerBoton){
        JButton boton = new JButton(textoBoton);

        boton.setFont(BUTTON_FONT);
        boton.setForeground(TEXT_COLOR);
        boton.setPreferredSize(BUTTON_SIZE);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        if(listenerBoton != null){
            boton.addActionListener(listenerBoton);
        }

        return boton;
    }
}
