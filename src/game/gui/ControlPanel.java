package game.gui;

import game.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

/**
 * Main control panel of the game. Placed on the top part of the frame.
 */
public class ControlPanel extends JPanel implements ActionListener {

    JButton pauseButton;
    JButton playButton;
    JButton restartButton;
    JButton quitButton;
    JCheckBox checkBox;
    JSlider volumeSlider;
    JButton displayLeadButton;

    private Game game;
    private MyView view;
    private StopWatch stopWatch;

    /**
     * The constructor that places each button, sliding bar and checkbox.
     * @param game the current running game
     * @param view the view that displays the world
     * @param stopWatch stopWatch running during the game. Used in the process of saving the game status.
     */
    public ControlPanel(Game game, MyView view, StopWatch stopWatch) {
        setBackground(Color.DARK_GRAY);
        this.game = game;
        this.view = view;
        this.stopWatch = stopWatch;

        pauseButton = new JButton("Pause");
        this.add(pauseButton);
        pauseButton.addActionListener(this);
        pauseButton.setBackground(Color.DARK_GRAY);
        pauseButton.setForeground(Color.WHITE);

        //used to add a small space between the buttons to create a neater appearance of the control panel
        add(new FillerBox(1));

        playButton = new JButton("Play");
        this.add(playButton);
        playButton.addActionListener(this);
        playButton.setBackground(Color.DARK_GRAY);
        playButton.setForeground(Color.WHITE);

        add(new FillerBox(1));

        restartButton = new JButton("Restart");
        this.add(restartButton);
        restartButton.setEnabled(false); //as the game is initially paused, this is to prevent the user from restarting the game while it is paused
        restartButton.addActionListener(this);
        restartButton.setBackground(Color.DARK_GRAY);
        restartButton.setForeground(Color.WHITE);

        add(new FillerBox(1));

        quitButton = new JButton("Quit!");
        this.add(quitButton);
        quitButton.addActionListener(this);
        quitButton.setBackground(Color.DARK_GRAY);
        quitButton.setForeground(Color.WHITE);

        add(new FillerBox(5));

        JTextField setVolumeText = new JTextField("Music volume: ");
        setVolumeText.setBackground(Color.DARK_GRAY);
        setVolumeText.setForeground(Color.WHITE);
        setVolumeText.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(setVolumeText);

        addVolumeSlider();

        add(new FillerBox(1));

        addCheckBox();

        add(new FillerBox(1));

        addComboBox();

    }

    /**
    Slider that changes the volume of the background music. Used in the constructor of the ControlPanel.
     */
    private void addVolumeSlider() {
        volumeSlider = new JSlider(0, 100, 40);

        add(volumeSlider);
        volumeSlider.setBackground(Color.DARK_GRAY);
        volumeSlider.addChangeListener(new ChangeListener() {

            public void stateChanged(ChangeEvent e) {

                //Can only change the volume from the slider if the "mute" checkbox
                //is not selected.
                if (!checkBox.isSelected()) {
                    double valueFromSlider = ((JSlider) e.getSource()).getValue();
                    if (valueFromSlider <= 3.0) {
                        game.changeVolume(0.001);

                    } else if (valueFromSlider > 5.0) {
                        game.changeVolume(valueFromSlider * 0.02);

                    }

                }

            }
        });
    }

    /**
    Check box that mutes the background music. Called when creating an instance of the Control Panel.
     */
    private void addCheckBox() {
        checkBox = new JCheckBox("Mute music");
        add(checkBox);

        checkBox.setBackground(Color.DARK_GRAY);
        checkBox.setForeground(Color.WHITE);

        //listener for the "Mute" checkbox.
        checkBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == 1) { //if the checkbox is ticked
                    game.changeVolume(0.001); //0.01 mutes the background music

                } else { //if the checkbox is not ticked.
                    game.changeVolume(0.5); //set the background music volume to half.

                }
            }
        });
    }

    /**
    Adding the control panel for saving the current game &
    loading the latest saved one. Called when creating the control panel.
     Here the game level writers and readers are called.
     */
    private void addComboBox() {
        //the options to be displayed in the dropbox
        String[] options = {"Save/Load game here", "Save game", "Load last save"};

        JComboBox saveLoadControlPanel = new JComboBox(options);
        add(saveLoadControlPanel);

        saveLoadControlPanel.setBackground(Color.DARK_GRAY);
        saveLoadControlPanel.setForeground(Color.WHITE);

        saveLoadControlPanel.setSelectedIndex(0); //the initial selection of the drop box.

        //Action listener is triggered when the user selects an option from the
        //drop box.
        saveLoadControlPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //Logic for saving the current game
                if (saveLoadControlPanel.getSelectedIndex() == 1) {
                    GameSaver gameSaver = new GameSaver("data/save1.txt", game);
                    try {
                        gameSaver.saveGame(stopWatch);

                    } catch (IOException ex) {
                        ex.printStackTrace();

                    }

                    //Logic for loading the last game saved
                } else if (saveLoadControlPanel.getSelectedIndex() == 2) {
                    GameLoader loader = new GameLoader("data/save1.txt", game, view);
                    try {
                        //loader.loadGame();
                        GameLevel loadedGame = loader.loadGame(stopWatch);
                        game.goToLevel(loadedGame);

                    } catch (IOException ex) {
                        ex.printStackTrace();


                    }
                }
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == playButton) {
            restartButton.setEnabled(true);
            game.resume();

        } else if (e.getSource() == pauseButton) {
            game.pause();
            restartButton.setEnabled(false);

        } else if (e.getSource() == restartButton) {
            if (!game.getGameState().equals("paused")) { //won't restart the level if game is paused
                game.restartLevel();
            }

        } else if (e.getSource() == quitButton) {
            System.exit(0);

        }
    }

    public boolean isMuteBoxSelected() {
        return checkBox.isSelected();
    }
}
