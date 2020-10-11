package game.levels.level4;
import city.cs.engine.*;
import game.*;
import game.levels.*;
import org.jbox2d.common.Vec2;

import java.io.IOException;

/**
 * Building Level4
 */
public class Level4 extends GameLevel{
    Enemy2 enemy2;

    /**
     * Constructor for the level 4. Called when going to next level or when restarting level4.
     * @param game the current game.
     */
    public Level4(Game game) {
        super(game);
        populate();

        //When both enemies are dead, the secret platform appears to move to finish the game.
        addStepListener(new Level4StepListener(getEnemy(), enemy2, game));

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
        Platform p11 = new Platform(this, new Vec2(-6,-5));

        //Platform portalPlatform = new Platform(this, new Vec2(-25, 10));
        Platform enemyPlatform = new Platform(this, new Vec2(20, 8));
        Platform enemy2Platform = new Platform(this, new Vec2(27, 9));

        //--------------------making & placing the vertical barriers---------------

        Shape wallShape = new BoxShape(0.5f, 1.5f);
        StaticBody leftWall = new StaticBody(this, wallShape);
        leftWall.setPosition(new Vec2(25.5f, 11));
        leftWall.addImage(new BodyImage("data/level4data/level4wall.png", 3));

        //--------------------making & placing the bullets-------------------------
        //bullet at -25,0 ;
        Body bullet = new Bullet(this);
        bullet.addCollisionListener(new PickupBullet(getPlayer()));
        bullet.setPosition(new Vec2(-25, 2));

        for (int i = 0; i < 7; i++) {
            Body bulletN = new Bullet(this);
            bulletN.setPosition(new Vec2(i*2-15, 10+i));
            bulletN.addCollisionListener(new PickupBullet(getPlayer()));
        }

        //--------------------placing the 1st enemy character-------------------------
        getEnemy().setPosition(new Vec2(20, 9));
        getEnemy().setHitsPossible(3); //how many hits the enemy can take

        //--------------------placing the 2nd enemy character----------------------
        enemy2 = new Enemy2(this);
        enemy2.setPosition(new Vec2(27, 10));

        enemy2.addCollisionListener(collisionEvent -> {

            //Give the enemy the appearance of a shield to indicate that it is immune to a bullet
            if (collisionEvent.getOtherBody() instanceof Bullet) {
                collisionEvent.getOtherBody().destroy();
                enemy2.removeAllImages();
                enemy2.addImage(new BodyImage("data/level4data/enemy2_shield.png", 2));
            }
        });

        //--------------------placing the portal---------------------------------
        getPortal().setPosition(new Vec2(-23, 13));

        //--------------------placing the solider---------------------------------
        Vec2 soliderStartPos = new Vec2(-25, -7.5f);
        getPlayer().setPosition(soliderStartPos);
        getPlayer().setStartPosition(soliderStartPos);

        createButton();

    }

    /**
     * Creating and placing the button that kills the 2nd enemy. After it's used,
     * the button will be destroyed.
     */
    public void createButton() {
        Shape buttonShape = new BoxShape(0.5f, 0.5f);
        Body buttonBody = new StaticBody(this, buttonShape);
        buttonBody.addImage(new BodyImage("data/level4data/button_unpressed.png", 1)); //Source of button images: https://kenney.nl/assets/simplified-platformer-pack
        buttonBody.setPosition(new Vec2(17, -4));
        buttonBody.addCollisionListener(new ButtonListener(this, enemy2));

    }

    /**
     * Returns 4, the integer value of the level. This method is used
     * when saving the level number to a save file.
     * @return 4
     */
    @Override
    public int getLevelNumber() {
        return 4;
    }

    /**
    Level4 is complete once both enemies are dead.
     */
    @Override
    public boolean isComplete() {
        return getEnemy().isDead() && enemy2.isDead();
    }

    /**
     * Returns the body of the second enemy. This method is called
     * when checking if the level is complete.
     * @return the second enemy in the level.
     */
    public DynamicBody getEnemy2() {
        return enemy2;
    }
}
