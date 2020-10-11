package game;

import city.cs.engine.UserView;
import city.cs.engine.World;
import game.levels.Solider;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
Main view of the game, displaying health bar, bullet count and responsive alerts on the screen.
 */
public class MyView extends UserView {
    //Source of all backgrounds: https://pressstart.vip/assets
    
    private Image background;
    private Map<String, Image> backgroundImages = new HashMap<>(); //map to store the corresponding background image of each level
    private Solider solider;

    private JProgressBar progressBar = new JProgressBar(0, 100); //displays the health of the main character
    private Game game;

    /**
     * Constructor
     * @param world displays and runs the current level
     * @param width of the frame
     * @param height of the frame
     * @param solider main controllable body of the game
     * @param game the game.
     */
    public MyView(World world, int width, int height, Solider solider, Game game) {
        super(world, width, height);
        createHashMap();
        background = backgroundImages.get("level1");
        this.solider = solider;
        this.game = game;
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString("Bullets: " + solider.getBulletCount(), 170, 17);


        if (game.getCurrentLevel() == 1) {
            drawInstructions(g); //draw instructions for the player.

        } else if (game.getCurrentLevel() == 4) {
            g.drawString("The blue enemy is immune to bullets!", 100, 180);
            g.drawString("Find another way to eliminate him!", 100, 210);

            //Displaying "paused" when the game is paused.
        } if (game.getGameState().equals("paused")) {
            g.setColor(Color.RED);
            g.drawString("PAUSED", 600, 300);

            //Displaying a "Saved" message on the screen
        } else if(game.getGameState().equals("saved")) {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
            g.drawString("Saved progress", 500, 300);
            savedMessageTimer();

        }
        addHealthBar();
    }

    /**
    Timer to display the "Saved game" message in the center of the screen
    for approx. 1 second.
     */
    private void savedMessageTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int sec = 0;

            @Override
            public void run() {
                sec++;
                if (sec == 30) {
                    game.setGameState("play");
                }
            }
        };
        timer.schedule(task, 0,50);
    }

    /**
     * Changes the background depending on the level given
     * @param levelNumber the level number to set the background image for.
     */
    public void changeBackground(String levelNumber) {
        background = backgroundImages.get(levelNumber);
    }

    private void createHashMap(){
        backgroundImages.put("level1", new ImageIcon("data/backgrounds/foggy.png").getImage());
        backgroundImages.put("level2", new ImageIcon("data/backgrounds/graveyard.png").getImage());
        backgroundImages.put("level3", new ImageIcon("data/backgrounds/snowymountains.png").getImage());
        backgroundImages.put("level4", new ImageIcon("data/backgrounds/sunnyday.png").getImage());

    }

    /**
     * This is the set of instructions to be displayed on the 1st level.
     * This is to give the user an understanding of the controls and game goal.
     * @param g is the current view.
     */
    public void drawInstructions(Graphics2D g) {
        g.drawString("To pass each level, shoot all the enemies & go to the green portal. ", 100, 150);
        g.drawString("Move with the arrows, use DOWN arrow to slam to the ground. ", 100, 180);
        g.drawString("Jump with SPACE and shoot with LEFT click. ", 100, 210);
        g.drawString("Press PLAY to start!! ", 100, 240);
        g.drawString("REMEMBER! the goal is to finish the game as fast", 100, 270);
        g.drawString("as possible.", 100, 300);
    }

    /*
    Creating & adding the health bar to the view.
    Responsive to the solider's health.
     */
    private void addHealthBar() {
        progressBar.setString("Health: " + solider.getHealth() + "%");
        progressBar.setStringPainted(true);
        progressBar.setValue(solider.getHealth());
        progressBar.setVisible(true);
        add(progressBar);
        progressBar.setBounds(20,0, 100, 20);
        progressBar.setBackground(Color.DARK_GRAY);
        progressBar.setForeground(Color.PINK);

    }

    /**
     * Changes the solider value.
     * This is commonly used when advancing to a level to display the correct health of it.
     * @param solider the new Soldier
     */
    public void setSolider(Solider solider) {
        this.solider = solider;
    }


}




