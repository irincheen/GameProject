package game.levels.level4;
import city.cs.engine.*;

/**
 * Blueprint for the 2nd enemy in level 4.
 */
public class Enemy2 extends DynamicBody{
    private static final Shape shape = new BoxShape (1, 1);
    private boolean isDead;

    /**
     * Constructor for the 2nd enemy in level 4.  It is immune to bullets and can only be
     * eliminated by activating a button in the game.
     * @param world the world, level 4 in this case.
     */
    public Enemy2(World world) {
        super(world, shape);

        BodyImage image = new BodyImage("data/level4data/enemy2_stand.png", 2);
        addImage(image);
        isDead = false;

    }


    /**
     * Changes the boolean value of the <i>isDead </i>. True if the enemy was destroyed,
     * false otherwise. This method is called when the spike drops on top of the enemy to
     * set it to <i>true</i>
     * @param dead true if it is dead, false oterwise
     */
    public void setDead(boolean dead) {
        isDead = dead;
    }

    /**
     * Returns the <i>isDead</i> status of the enemy. This
     * is called when checking if level 4 is complete and the secret platforms
     * should apprear.
     * @return true if enemy2 was destroyed, false otherwise
     */
    public boolean isDead() {
        return isDead;
    }

}
