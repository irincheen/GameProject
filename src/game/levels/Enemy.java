package game.levels;
import city.cs.engine.*;

/**
 * Enemy body that can be placed anywhere on the grid
 */
public class Enemy extends DynamicBody{
    private static Shape shape = new PolygonShape(-1.287f,0.934f, 1.116f,0.967f, 1.342f,0.31f, 0.652f,-0.961f, -0.696f,-0.95f, -1.326f,0.249f);
    BodyImage image = new BodyImage("data/enemy_stand.png", 2);

    private boolean isDead;
    private int hitsPossible = 2;

    /**
     * Constructor for the enemy.
     * @param world the World to place the enemy in.
     */
    public Enemy(World world) {
        super(world, shape);
        addImage(image);
        isDead = false;

    }

    /**
     * Changes the boolean value of the state of the enemy.
     * @param dead the new state of the enemy
     */
    public void setIsDead(boolean dead) {
        this.isDead = dead;
    }

    /**
     * Returns true if the enemy was destroyed, false otherwise.
     * @return is the enemy eliminated?
     */
    public boolean isDead() {
        return isDead;
    }

    /**
     * Returns the integer value of the no. of hits the enemy can take.
     * This is typically called when the enemy takes a hit and deciding
     * when to destroy the enemy.
     * @return how many hits the enemy can take
     */
    public int getHitsPossible() {
        return hitsPossible;
    }

    /**
     * Changes the numbers of hits the enemy can take.
     * This is called when a certain level requires multiple hits to
     * eliminate the enemy.
     * @param hitsPossible the new no. of hits
     */
    public void setHitsPossible(int hitsPossible) {
        if (hitsPossible <= 0) {
            System.out.println("The number of hits the enemy can take must be at least 1.");
            System.exit(0);
        } else {
            this.hitsPossible = hitsPossible;

        }

    }

    /**
     * Decrement possible hits the enemy can take by 1.
     * Usually called when the enemy collides with the bullet.
     */
    public void decrementHitsPossible() {
        hitsPossible--;
    }




}
