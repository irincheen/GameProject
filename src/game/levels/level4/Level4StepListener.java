package game.levels.level4;

import game.*;
import game.levels.*;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

/**
 * Step listener for level 4. Used to display 2 platforms and a new background sound
 * once both enemies are destroyed.
 */
public class Level4StepListener implements StepListener {

    private Enemy enemy1;
    private Enemy2 enemy2;

    /**
     * Stops the platform from appearing at each frame.
     */
    private boolean shownPlatform = false;
    private Game game;

    /**
     * Constructor for the step listener.
     * @param enemy1 1st enemy
     * @param enemy2 2nd enemy
     * @param game the game
     */
    public Level4StepListener(Enemy enemy1, Enemy2 enemy2, Game game) {
        this.enemy1 = enemy1;
        this.enemy2 = enemy2;
        this.game = game;
    }

    @Override
    public void preStep(StepEvent stepEvent) {

        //secret platform only appears when both enemies are dead.
        if (!shownPlatform) {
            if (enemy1.isDead() && enemy2.isDead()) {
                game.setBackgroundMusic("data/sfx/finish_line.wav");

                System.out.println("Commence secret platform");
                Platform platform = new Platform(enemy2.getWorld(), new Vec2(-4, 6));
                Platform platform2 = new Platform(enemy2.getWorld(), new Vec2(-11, 7));

                shownPlatform = true;
            }
        }
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }
}
