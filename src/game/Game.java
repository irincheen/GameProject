package game;

import city.cs.engine.SoundClip;
import game.gui.ControlPanel;
import game.gui.LeftPanel;
import game.gui.RightPanel;
import game.levels.HealthWriterAndReader;
import game.levels.Level1;
import game.levels.level2.Level2;
import game.levels.level3.Level3;
import game.levels.level4.Level4;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;


/**
 * The computer game.
 */
public class Game {
    private DropBullet bulletDropper;
    private Controller controller; //controller for the main character
    private ControlPanel controlPanel; //main control panel of the game
    private GameLevel world;

    private int currentLevel;
    private MyView view;
    private SoundClip background_music;
    private String gameState = "pause";
    private HealthWriterAndReader healthWriter; //what will transfer the solider's health from one level to the other
    private StopWatch watch; //Stopwatch tracking the duration of the game.
    private LeftPanel leftPanel; //Contains the leader board

    private String currentPlayerName;
    final JFrame frame;


    /** Initialise a new Game. */
    public Game() {


        currentPlayerName = "";
        watch = new StopWatch();
        currentLevel = 1;

        //initialising the levels
        world = new Level1(this);

        healthWriter = new HealthWriterAndReader();

        view = new MyView(world, 1200, 700, world.getPlayer(), this);


        // make a view
        // uncomment this to draw a 1-metre grid over the view
        //view.setGridResolution(1);

        // display the view in a frame
        frame = new JFrame("The floor is lava SPEED RUN edition ");


        controlPanel = new ControlPanel(this, view, watch);
        frame.add(controlPanel, BorderLayout.NORTH);

        leftPanel = new LeftPanel(this);
        frame.add(leftPanel.getMainPanel(), BorderLayout.WEST);

        //Displaying the leaderboard that contains each player and the time taken to complete in seconds
        displayLeaderBoard();

        // quit the application when the game window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);

        // display the world in the window
        frame.add(view);

        // don't let the game window be resized
        frame.setResizable(false);

        // size the game window to fit the world view
        frame.pack();

        // make the window visible
        frame.setVisible(true);

        // get keyboard focus
        frame.requestFocus();

        view.addMouseListener(new GiveFocus(frame));

        bulletDropper = new DropBullet(view, world.getPlayer(), world.getEnemy());
        view.addMouseListener(bulletDropper);

        controller = new Controller(world.getPlayer());
        frame.addKeyListener(controller);



        //Initiating & starting the main background music.
        try {
            background_music = new SoundClip("data/sfx/background_music.wav");
            background_music.loop();  // Set it to continous playback

        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
            System.out.println(e);
        }

        // start!
        world.start();

        //World will initially be in the "paused" state, to continue,
        //user will press "Play"
        pause();

    }

    /**
     * Take the game to the next level.
     * The previous health of the soldier is carried on and the controller and mouse listeners
     * are set the new level bodies.
     * Method is typically called when the previous level is completed.
     */
    public void goToNextLevel(){
        //Saving the solider's health before switching levels
        try {
            healthWriter.saveHealth(world.getPlayer());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //LEVEL 2
        if (currentLevel == 1 && world.isComplete()) {
            currentLevel++;
            world.stop();
            world = new Level2(this);

            view.setWorld(world);
            view.changeBackground("level2");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

            //LEVEL 3
        } else if (currentLevel == 2  && world.isComplete()) {
            setBackgroundMusic("data/sfx/level3.wav");
            currentLevel++;
            world.stop();
            world = new Level3(this);

            view.setWorld(world);
            view.changeBackground("level3");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

            //LEVEL 4
        } else if (currentLevel == 3  && world.isComplete()) {
            currentLevel++;
            world.stop();
            world = new Level4(this);

            view.setWorld(world);
            view.changeBackground("level4");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

        } else if  (currentLevel == 4 && world.isComplete()) {
            watch.stop();
            addTimeToScoreBoard();
            System.out.println("Game finished! Congrats!!!!!!");
            System.exit(0);
        }

    }

    /**
    At the end of the game, nano seconds took the player to finish is loaded to the scoreboard text file.
     */
    private void addTimeToScoreBoard() {
        TimeWriterAndReader timeWriter = new TimeWriterAndReader();

        try {
            timeWriter.saveTime(watch.getNanoTime(), currentPlayerName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    Displays the leader board on the left side. If there are no entries, the pane
    won't be displayed at all.
    */
    public void displayLeaderBoard() {
        TimeWriterAndReader timeWriter = new TimeWriterAndReader();

        List<String> list = null;
        try {
            list = timeWriter.getScores();
            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!list.isEmpty()) { //only display the panel if there is at least one entry in it.
            RightPanel rightPanel = new RightPanel(list);
            frame.add(rightPanel.getMainPanel(), BorderLayout.EAST);
        }

    }

    /**
     * Restarts the current level. The health of the solider is set to the one
     * in the beginning of the level.
     * Typically called when the "Reset" button is pressed in the control panel.
     */
    public void restartLevel() {

        if (currentLevel == 1) {
            world.stop();
            world = new Level1(this);

            view.setWorld(world);
            view.changeBackground("level1");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

        } else if (currentLevel == 2) {
            world.stop();
            world = new Level2(this);

            view.setWorld(world);
            view.changeBackground("level2");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

        } else if (currentLevel == 3) {
            world.stop();
            world = new Level3(this);

            view.setWorld(world);
            view.changeBackground("level3");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

        } else if (currentLevel == 4) {
            world.stop();
            world = new Level4(this);

            view.setWorld(world);
            view.changeBackground("level4");
            view.setSolider(world.getPlayer());

            world.start();

            controller.setPlayer(world.getPlayer());
            bulletDropper.setPlayer(world.getPlayer());
            bulletDropper.setEnemy(world.getEnemy());

        }
    }

    /**
     * Goes to the level specified.
     * Typically called when loading a saved game.
     * @param levelToGoTo game level to go to
     */
    public void goToLevel(GameLevel levelToGoTo) {
        world.stop();
        currentLevel = levelToGoTo.getLevelNumber(); //the level no becomes the one in the save file

        // get a new world
        world =levelToGoTo;
        view.setWorld(world);
        view.setSolider(world.getPlayer());

        controller.setPlayer(world.getPlayer());
        bulletDropper.setPlayer(world.getPlayer());
        bulletDropper.setEnemy(world.getEnemy());
    }

    /**
     * Changes the background music.
     * Typically used when progressing from one level to another or
     * in response to game events.
     * @param filePath the filepath where the new sound is located.
     */
    public void setBackgroundMusic(String filePath) {
        background_music.stop();
        try {
            background_music = new SoundClip(filePath);
            background_music.loop();

            //If the mute box is ticked, mute the sound
            if (controlPanel.isMuteBoxSelected()) {
                background_music.setVolume(0.001);
            }


        } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
           System.out.println(e);
        }
    }

    /**
     * Sets the volume of the current background music.
     * Called when the volume slider is used or the mute check box.
     * @param newVolume the new volume of the sound.
     */
    public void changeVolume(double newVolume) {
        background_music.setVolume(newVolume);
    }

    /**
     * Pauses the game, background music and stopwatch. Typically called when the "pause" button
     * is pressed in the control panel. The game state is set to "paused" and a
     * "PAUSED" string is displayed in the center of the window.
     */
    public void pause() {

        if (watch.isStarted()) { //The stopwatch doesn't pause if it isn't started.
            watch.pause();
        }
        world.stop();
        background_music.pause();
        gameState = "paused";
    }

    /**
     * Resumes the game, the background music and stopwatch. Usually called
     * when pressing the "resume" button in the control panel.
     * Game state is set to "play".
     */
    public void resume() {
        if (currentPlayerName.length() > 0) {
            if (watch.isStarted()) {
                watch.resume();
            } else {
                watch.start(); //this stops the stopwatch from starting as soon as the game is ran
            }
            world.start();
            background_music.resume();
            gameState = "play";
        }
    }

    /**
     * Changes the name of the user. Called when a new user enters their name
     * or a save file is loaded and the name from the text file is loaded to
     * currentPlayerName.
     * The left panel that shows instructions and takes the name of the user
     * is also removed. This prevents the user from entering a second name or changing it
     * when a save file is loaded.
     * @param currentPlayerName the new name of the user playing.
     */
    public void setCurrentPlayerName(String currentPlayerName) {
        this.currentPlayerName = currentPlayerName;

        //Removing the left-hand panel & resizing the frame to the game view only
        //this forces the user to give only one input value at the start of the game
        //or when loading from a save file.
        frame.remove(leftPanel.getMainPanel());
        frame.validate();
        frame.setSize(1400, 700);

    }

    /**
     * Returns the name given at the start of the game or loaded from a save file.
     * @return the name of the user.
     */
    public String getCurrentPlayerName() {
        return currentPlayerName;
    }

    /**
     * Returns the current level the world is at.
     * Used when saving the level the user is playing to a text file.
     * @return the GameLevel the world is displaying.
     */
    public GameLevel getWorld() {
        return world;
    }

    /**
     * Returns the integer value of the current level.
     * Usually used to display instructions to the user at certain levels.
     * @return integer of the current level number.
     */
    public int getCurrentLevel() {
        return currentLevel;
    }

    /**
     * Changes the state of the game. This is called when the game is
     * either paused or resumed. Useful when displaying "paused" when the
     * game is paused and also removing that message from the window.
     * @param gameState the current state of the game.
     */
    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    /**
     * Returns the current state of the game.
     * @return the state of the game
     */
    public String getGameState() {
        return gameState;
    }

    /** Run the game. */
    public static void main(String[] args) {
        new Game();


    }

}
