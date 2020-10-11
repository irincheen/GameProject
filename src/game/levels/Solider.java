package game.levels;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import game.levels.level2.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Main female character
 */
public class Solider extends Walker {

    private static final Shape shape = new PolygonShape(-0.85f,1.07f, 0.71f,0.96f, 1.07f,-1.94f, -1.11f,-1.95f);
    private int bulletCount;
    private int health;

    private Vec2 startPosition;

    /**
     * Constructor for the solider.
     * @param world the world to place the soldier in.
     */
    public Solider(World world) {
        super(world, shape);
        addImage(getFacingImage("right"));
        health = 100;
        startPosition = new Vec2(-25, -7.5f);

    }

    /**
     * used to change the image on the solider.
     * @param orientation is the direction in which the character is going
     * @return BodyImage type
     */
    public static BodyImage getFacingImage(String orientation){
        ArrayList<BodyImage> list = createArray();

        if (orientation.equals("left")){
            return list.get(0);

        } else if(orientation.equals(("jump"))) {
            return list.get(2);

        } else if (orientation.equals("hurt")) {
            return list.get(3);

        } else {
            return list.get(1);

        }
    }

    /**
     * @return an arrayList of the body images in BodyImage form.
     */
    private static ArrayList<BodyImage> createArray() {

        BodyImage facingRightImage = new BodyImage("data/stand_right.png", 4);
        BodyImage facingLeftImage = new BodyImage("data/stand_left.png", 4);
        BodyImage jumpImage = new BodyImage("data/jump.png", 4);
        BodyImage hurtImage = new BodyImage("data/hurt.png", 4);

        ArrayList<BodyImage> orientationImages = new ArrayList<BodyImage>();
        orientationImages.add(0,facingLeftImage);
        orientationImages.add(1, facingRightImage);
        orientationImages.add(2, jumpImage);
        orientationImages.add(3, hurtImage);

        return orientationImages;
        //Source of character images: https://kenney.nl/assets/platformer-characters
    }

    /**
    Triggers a timer task and schedules it for 3 seconds with a 400ms delay.
    Function is called when the Player collides with the Potion
    */
    public void flyPowerUp() {
        Timer timer = new Timer();
        TimerTask task = new TimerForPotion(timer, this);
        timer.schedule(task, 400, 50);


    }

    /**
     * Changes the number of bullets the soldier can shoot.
     * This is typically used when loading a save file where the no. of bullets
     * is saved in a text file.
     * @param bulletCount the new no. of bullets
     */
    public void setBulletCount(int bulletCount) {
        if (bulletCount >= 0) {
            this.bulletCount = bulletCount;

        } else {
            System.out.println("Invalid input. Input must be bigger than 0 duh..");
        }
    }

    /**
     * Returns the number of bullets the solider has.
     * This is usually called to check whether the solider can still shoot bullets
     * when the mouse is clicked.
     * @return integer value of number of bullets
     */
    public int getBulletCount() {
        return bulletCount;
    }

    /**
     * Returns the integer value of the solider's health out of 100.
     * This is typically called when displaying the solider's health in a progress bar
     * as the game progresses.
     * @return health of the solider. Maximum value 100, minimum 0.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Changes the solider's health. This is typically called when
     * loading up a save of the game or when advancing the game to the
     * level of the game.
     * @param health the new health of the solider.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Increment the bullet count by 1. Called when the solider
     * collects bullets.
     */
    public void incrementBulletCount() {
        bulletCount++;
    }

    /**
     * Decrement the bullet count by 1. Usually called when
     * the mouse is clicked and a bullet is shot from the
     * solider.
     */
    public void decrementBulletCount() {
        if (bulletCount != 0) {
            bulletCount--;
        } else {
            bulletCount = 0;
        }

    }

    /**
     * Decrement solider health by the parameter given. Method is used when
     * the solider touches the lava or an enemy.
     * @param damageTaken the value to subtract from the soldier health.
     */
    public void decrementHealth(int damageTaken) {
        health = health - damageTaken;
        //System.out.println("Ouch! Her health is now: " + health);
        if (health <= 0) {
            this.destroy();
            System.out.println("Game over lol");
        }
        //System.out.println("~~~");
    }

    /**
     * Returns the initial position of the solider when the current level is ran.
     * @return the position of the solider as a Vec2
     */
    public Vec2 getStartPosition() {
        return startPosition;
    }

    /**
     * Changes the start position of the solider.
     * @param startPosition the new initial position of the solider
     */
    public void setStartPosition(Vec2 startPosition) {
        this.startPosition = startPosition;
    }
}
