package game.levels;

import city.cs.engine.Body;
import game.*;
import org.jbox2d.common.Vec2;

/**
 * Level1 class.
 */
public class Level1 extends GameLevel {
    /**
     * Usually called when going to the next level or when restarting the level.
     * @param game the game.
     */
    public Level1(Game game) {
        super(game);
        populate();

    }

    @Override
    public void populate() {
         //--------------------making & placing the platforms-------------------------
        Platform p1  = new Platform(this, new Vec2(-25,-10));
        Platform p2 = new Platform(this, new Vec2(-25,0));
        Platform p3 = new Platform(this, new Vec2(-10,-5));
        Platform p4 = new Platform(this, new Vec2(9,6));
        Platform p5 = new Platform(this, new Vec2(13,6));
        Platform p6 = new Platform(this, new Vec2(0,0));
        Platform p7 = new Platform(this, new Vec2(4,0));
        Platform p8 = new Platform(this, new Vec2(17, -5));
        Platform p9 = new Platform(this, new Vec2(8,0)) ;
        Platform p10 = new Platform(this, new Vec2(12,0));
        Platform enemyPlatform = new Platform(this, new Vec2(20, 8));

        //--------------------making & placing the bullets-------------------------
        for (int i = 0; i < 11; i++) {
            Body bullet = new Bullet(this);
            bullet.setPosition(new Vec2(i*2-10, 10+i));
            bullet.addCollisionListener(new PickupBullet(getPlayer()));
        }

        //--------------------making the enemy character-------------------------
        getEnemy().setPosition(new Vec2(20, 9));
        //how many hits the enemy can take
        getEnemy().setHitsPossible(2);

        //--------------------placing the portal---------------------------------
        getPortal().setPosition(new Vec2(7, -8));

        //--------------------placing the solider---------------------------------
        Vec2 soliderStartPos = new Vec2(-25, -7.5f);
        getPlayer().setPosition(soliderStartPos);
        getPlayer().setStartPosition(soliderStartPos);

    }

    /**
     * Returns 1, the integer value of the level. This method is used when
     * saving the level number to a save file.
     * @return 1
     */
    @Override
    public int getLevelNumber() {
        return 1;
    }


    /**
     * Level1 is complete once the enemy is destroyed.
     * @return boolean, true if the enemy is destroyed. False otherwise
     */
    @Override
    public boolean isComplete() {
        return getEnemy().isDead();

    }
}
