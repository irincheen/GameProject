package game.levels.level3;

import city.cs.engine.Body;
import game.*;
import game.levels.*;
import org.jbox2d.common.Vec2;

import java.io.IOException;

/**
 * Level3 builder. In this level, a new background song is played.
 */
public class Level3 extends GameLevel{

    /**
     * Constructor of level 3. Usually called when going to the next level or when
     * restarting the level.
     * @param game the current game
     */
    public Level3(Game game) {
        super(game);
        populate();
        addStepListener(new Level3StepListener(getPlayer(), this));

        HealthWriterAndReader reader = new HealthWriterAndReader();
        //Getting the solider's health from the previous level from the text file.
        try {
            int previousLevelHealth = reader.getPreviousHealth();
            getPlayer().setHealth(previousLevelHealth);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void populate() {
        //--------------------making & placing the platforms-------------------------
        Platform p1  = new Platform(this, new Vec2(-25,-8));
        Platform p2  = new Platform(this, new Vec2(-1,3));
        //Platform p3 = new Platform(this, new Vec2(-10,-9));
        Platform p4 = new Platform(this, new Vec2(-4,11.5f));
        Platform p5 = new Platform(this, new Vec2(-8,11.5f));
        Platform p6 = new Platform(this, new Vec2(-2,-2));
        Platform p7 = new Platform(this, new Vec2(2,-2));
        Platform p8 = new Platform(this, new Vec2(17, 5));
        Platform p9 = new Platform(this, new Vec2(8,2)) ;
        Platform p10 = new Platform(this, new Vec2(12,2));
        Platform p11 = new Platform(this, new Vec2(-5,3));
        Platform p12 = new Platform(this, new Vec2(-15,5));
        Platform p13 = new Platform(this, new Vec2(-19,5));
        Platform p14 = new Platform(this, new Vec2(-25,10));

        Platform enemyPlatform = new Platform(this, new Vec2(21, 5));

        //--------------------making & placing the bullets-------------------------
        Body b1 = new Bullet(this);
        b1.setPosition(new Vec2(-25, 5));
        b1.addCollisionListener(new PickupBullet(getPlayer()));

        Body b2 = new Bullet(this);
        b2.setPosition(new Vec2(-10, 5));
        b2.addCollisionListener(new PickupBullet(getPlayer()));

        Body b3 = new Bullet(this);
        b3.setPosition(new Vec2(-18, 7));
        b3.addCollisionListener(new PickupBullet(getPlayer()));

        Body b4 = new Bullet(this);
        b4.setPosition(new Vec2(-2, 5));
        b4.addCollisionListener(new PickupBullet(getPlayer()));

        Body b5 = new Bullet(this);
        b5.setPosition(new Vec2(-1, 5));
        b5.addCollisionListener(new PickupBullet(getPlayer()));


        //--------------------making the enemy character-------------------------
        getEnemy().setPosition(new Vec2(18, 6));
        getEnemy().setHitsPossible(3);

        //--------------------placing the portal---------------------------------
        getPortal().setPosition(new Vec2(-3, 13.5f));

        //--------------------placing the solider--------------------------------
        Vec2 soliderStartPos = new Vec2(-25, 11);
        getPlayer().setPosition(soliderStartPos);
        getPlayer().setStartPosition(soliderStartPos);


    }

    /**
     * Drop a spike when the solider reaches a certain area in the game.
     * It halves the health of the solider.
     */
    public void dropSpike() {
        SpikedBall spikedBall = new SpikedBall(this);
        spikedBall.setPosition(new Vec2(-11.5f,15));
        spikedBall.addCollisionListener(new SpikedBallListener(getPlayer()));

    }

    /**
     * Returns the integer 3. This method is used when
     * saving the level number to a save file.
     * @return integer value 3.
     */
    @Override
    public int getLevelNumber() {
        return 3;
    }

    /**
    Level3 is complete once the enemy is dead.
     */
    @Override
    public boolean isComplete() {
        return getEnemy().isDead();
    }

}
