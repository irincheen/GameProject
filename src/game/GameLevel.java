package game;
import city.cs.engine.Shape;
import city.cs.engine.*;
import game.levels.*;
import org.jbox2d.common.Vec2;
import java.awt.*;

/**
A basic structure of every level created
 */
public abstract class GameLevel extends World {

    private Solider solider;
    private Portal portal;
    private Enemy enemy;
    private Lava ground;

    /**
     * Constructor for a level. This is called each time a new
     * level is initiated.
     * @param game the current game
     */
    public GameLevel(Game game) {
        solider = new Solider(this);

        portal = new Portal(this);
        portal.addCollisionListener(new PortalListener(game));

        enemy = new Enemy(this);
        enemy.addCollisionListener(new EnemyListener(solider));

        //every level should have the same border
        Shape s1 = new BoxShape(30, 1.5f);
        Body bottomBorder = new StaticBody(this, s1);
        bottomBorder.setPosition(new Vec2(0, -16.5f));
        bottomBorder.setFillColor(Color.DARK_GRAY);

        Shape s2 = new BoxShape(0.5f, 17.5f);
        Body leftBorder = new StaticBody(this, s2);
        leftBorder.setPosition(new Vec2(-29.5f, 0));
        leftBorder.setFillColor(Color.DARK_GRAY);
        Body rightBorder = new StaticBody(this, s2);
        rightBorder.setPosition(new Vec2(29.5f, 0));
        rightBorder.setFillColor(Color.DARK_GRAY);

        //the lava doesn't change positions
        Shape groundShape = new BoxShape(29, 1);
        ground = new Lava(this, groundShape);
        ground.addCollisionListener(new LavaListener(solider, enemy));


    }

    /**
     * Returns the player in the level.
     * This is usually called when positioning the player in each level.
     * @return the main player
     */
    public Solider getPlayer() {
        return solider;
    }

    /**
     * Returns the enemy in the level.
     * This is usually called when positioning the enemy in each level.
     * @return the enemy to be returned
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Returns the portal in the level. Its purpose is to
     * allow the player to advance to the next level.
     * This is usually called when positioning the portal in each level.
     * @return Portal object in the level.
     */
    public Portal getPortal() {
        return portal;
    }

    /**
     * Abstract method to check if a level is complete.
     * @return true if level is comeplete, false otherwise
     */
    public abstract boolean isComplete();

    /**

     * @return the number of the level as int
     */
    public abstract int getLevelNumber();

    /**
     * Build the world(platform and other bodies)
     */
    public abstract void populate();


}
