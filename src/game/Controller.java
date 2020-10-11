package game;

import game.levels.Solider;
import org.jbox2d.common.Vec2;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key handler to control a Walker.
 */
public class Controller extends KeyAdapter {

    private static final float JUMPING_SPEED = 12;
    private static final float WALKING_SPEED = 8; //8

    private int timesPressedDown = 0;
    private Solider player;

    /**
     * Constructor for the key handler.
     * @param player the character to control with the keyboard
     */
    public Controller(Solider player) {
        this.player = player;

    }

    /**
     * Handle key release events (start walking).
     * @param e description of the key event
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); //stores the value of the key pressed

        if (code == KeyEvent.VK_Q) { // Q = quit
            System.exit(0);

        } else if (code == KeyEvent.VK_SPACE) { // SPACE = jump
            Vec2 v = player.getLinearVelocity();
            player.jump(JUMPING_SPEED);

        } else if (code == KeyEvent.VK_LEFT) {
            player.removeAllImages();
            player.addImage(Solider.getFacingImage("left"));
            player.startWalking(-WALKING_SPEED); // LEFT arrow = walk left

        } else if (code == KeyEvent.VK_RIGHT) {
            player.removeAllImages();
            player.addImage(Solider.getFacingImage("right"));
            player.startWalking(WALKING_SPEED); // RIGHT arrow = walk right

        } else if (code == KeyEvent.VK_DOWN) {
            timesPressedDown++;
            player.applyForce(new Vec2(0, -1000));
            player.applyImpulse(new Vec2(0, -1000));

            if (timesPressedDown > 100) {
                timesPressedDown = 0;
                player.setPosition(player.getStartPosition()); //resetting the main player position if down arrow is constantly pressed
                System.out.println("Stop trying to break my game >:(");
            }
        }
    }

    /**
     * Handle key release events (stop walking).
     * @param e description of the key event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_LEFT) {
            player.stopWalking();

        } else if (code == KeyEvent.VK_RIGHT) {
            player.stopWalking();

        }
    }

    /**
     * Change the character the key handler is controlling.
     * Typically used when switching levels.
     * @param player the new player to control.
     */
    public void setPlayer(Solider player) {
        this.player = player;
        timesPressedDown = 0; //Player value is changed with each level, so resetting timesPressedDown here.


    }
}
