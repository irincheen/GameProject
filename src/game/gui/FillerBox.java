package game.gui;

import javax.swing.*;
import java.awt.*;

/**
 * An empty textbox.
 */
public class FillerBox extends JTextField {

    /**
     * The empty text box contstructor. It is used to create space between components.
     * It gives a neater look to the GUI.
     * @param unitsAcross how wide the empty box should be.
     */
    public FillerBox(int unitsAcross) {
       setBackground(Color.DARK_GRAY);

       setBorder(javax.swing.BorderFactory.createEmptyBorder());
       String oneUnit = " ";
       setText(oneUnit.repeat(unitsAcross));
    }

}
