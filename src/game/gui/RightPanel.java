package game.gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;

/**
 * Panel placed to the right of the game, it holds the leader board.
 * If there are no entries in the leaderboard, it is not displayed.
 * This is called in the main Game class.
 */
public class RightPanel{
    private JPanel mainPanel;
    private JList<String> listBody;
    private JScrollPane scrollPane;
    List<String> myList;

    /**
     * Constructor of the leaderboaard.
     * @param listToDisplay is the array containing user name and time taken to complete the game.
     *                      The array is to be displayed in the pane.
     */
    public RightPanel(List<String> listToDisplay) {
        mainPanel.setBackground(Color.DARK_GRAY);
        mainPanel.setForeground(Color.WHITE);

        //Display the given list on the panel.
        final JList<String> listBody = new JList<String>(listToDisplay.toArray(new String[listToDisplay.size()]));

        scrollPane.setViewportView(listBody);
        scrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_ALWAYS); //always display a horizontal scroll bar
        scrollPane.setPreferredSize(new Dimension(210, 700));
        scrollPane.getViewport().setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        listBody.setLayoutOrientation(JList.VERTICAL);

    }

    /**
     * Returns the main JPanel holding the leaderboard.
     * This is used the adding it to the main window of the game.
     * @return main panel
     */
    public JPanel getMainPanel() {
        return mainPanel;
    }

}
