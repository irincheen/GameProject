package game.gui;
import game.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Placed on the left hand side of the game window.
 * Its purpose is to collect the user's name and display instructions
 * for the user.
 */
public class LeftPanel {
    private JButton button1; //the submit button.
    private JTextField textField1; //the box the user enters their name in.
    private JPanel mainPanel;
    private String inputValue; //the name of the user.

    /**
     * The constructor for the panel. It builds the buttons, input box and
     * other information to guide the user.
     * @param game the current running game.
     */
    public LeftPanel(Game game) {
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setForeground(Color.WHITE);


        button1.setEnabled(false);

        //This action listener will update the "enabled" value of button1
        //button1 will be available to click once the input length is > 0.
        textField1.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (textField1.getText().length() > 0){
                    button1.setEnabled(true);

                } else {
                    button1.setEnabled(false);

                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (textField1.getText().length() > 0){
                    button1.setEnabled(true);
                } else {
                    button1.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (textField1.getText().length() > 0){
                    button1.setEnabled(true);
                } else {
                    button1.setEnabled(false);
                }
            }
        });

        //once button1 is enabled, inputValue is set to the user input.
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inputValue = textField1.getText();
                game.setCurrentPlayerName(inputValue);
                System.out.println("Text from input: " + textField1.getText());
            }
        });
    }

    /**
     * Returns the main JPanel holding the information that guides the user.
     * This is used the adding it to the main window of the game.
     * @return main panel.
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

}