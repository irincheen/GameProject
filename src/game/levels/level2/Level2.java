package game.levels.level2;
import city.cs.engine.Body;
import game.*;
import game.levels.*;
import org.jbox2d.common.Vec2;
import java.io.IOException;

/**
 * Level2 class. Build the characters and bodies in the level.
 */
public class Level2 extends GameLevel {
    Potion potion;

    /**
     * Constructor of level 2.
     * Usually called when going to the next level or when restarting the level.
     * @param game the current game.
     */
    public Level2(Game game) {
        super(game);
        populate();

        /**
         * Reader that gets the solider's health from the previous level from the text file.
         */
        HealthWriterAndReader reader = new HealthWriterAndReader();


        try {
            int previousLevelHealth = reader.getPreviousHealth();
            getPlayer().setHealth(previousLevelHealth); //changing the solider's health to the one from the previous level

        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public void populate() {

        //--------------------making & placing the platforms-------------------------
        Platform p1  = new Platform(this, new Vec2(-25,-8));
        Platform p2  = new Platform(this, new Vec2(-3,3));
        Platform p3 = new Platform(this, new Vec2(-10,-9));
        Platform p4 = new Platform(this, new Vec2(-4,12.5f));
        Platform p5 = new Platform(this, new Vec2(0,12.5f));
        Platform p6 = new Platform(this, new Vec2(0,-2));
        Platform p7 = new Platform(this, new Vec2(4,-2));
        Platform p8 = new Platform(this, new Vec2(17, 5));
        Platform p9 = new Platform(this, new Vec2(8,2)) ;
        Platform p10 = new Platform(this, new Vec2(12,2));
        Platform p11 = new Platform(this, new Vec2(-7,3));
        Platform p12 = new Platform(this, new Vec2(-15,5));
        Platform p13 = new Platform(this, new Vec2(-19,5));

        Platform enemyPlatform = new Platform(this, new Vec2(21, 5));
        Platform powerupPlatform = new Platform(this, new Vec2(-25,10));

        //--------------------making & placing the bullets-------------------------
        for (int i = 0; i < 5; i++) {
            Body bullet = new Bullet(this);
            bullet.setPosition(new Vec2(i*4-10, 10));
            bullet.addCollisionListener(new PickupBullet(getPlayer()));
        }

        //--------------------making the enemy character-------------------------
        getEnemy().setPosition(new Vec2(18, 6));
        getEnemy().setHitsPossible(3);

        //--------------------placing the portal---------------------------------
        getPortal().setPosition(new Vec2(-2, 14.5f));

        //--------------------placing the solider--------------------------------
        Vec2 soliderStartPos = new Vec2(-25, -7);
        getPlayer().setPosition(soliderStartPos);
        getPlayer().setStartPosition(soliderStartPos);

        //--------------------placing the potion--------------------------------
        potion = new Potion(this);
        potion.setPosition(new Vec2(-15,6.5f));
        potion.addCollisionListener(new PotionListener(getPlayer()));

    }

    /**
     * Returns 2, the integer value of the level. This method is used
     * when saving the level number to a save file.
     * @return 2
     */
    @Override
    public int getLevelNumber() {
        return 2;
    }

    /**
     Level2 is complete once the enemy is dead
     */
    @Override
    public boolean isComplete() {
        return getEnemy().isDead();
    }

}
