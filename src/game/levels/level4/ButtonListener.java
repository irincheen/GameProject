package game.levels.level4;

import city.cs.engine.*;
import game.levels.Solider;
import org.jbox2d.common.Vec2;

/**
 *Collision listener for the button. Initiated when the button is created in level 4.
 * When the solider collides with the button, the listener drops a spike on
 * the enemy.
 */
public class ButtonListener implements CollisionListener {
    Level4 world;
    Enemy2 enemy2;
    DynamicBody fallingSpike;

    /**
     * The constructor. It is added to the Button when initiating level 4.
     * @param world the world, in this case, level 4
     * @param enemy2 the enemy to drop the spike on and destroy.
     */
    public ButtonListener(Level4 world, Enemy2 enemy2) {
        this.enemy2 = enemy2;
        this.world = world;
    }

    /**
     * Collision handler for the button. Once the main character, the solider, touches
     * the button, it disappears and a spike is spawned on top of the 2nd enemy to destroy it.
     * The spike is given its own collision listener, created using a lambda expression.
     * @param collisionEvent details of the collision event.
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Solider) {
            collisionEvent.getReportingBody().destroy(); //destroy the button  itself

            Shape spikeShape = new BoxShape(1, 1);
            fallingSpike = new DynamicBody(world, spikeShape);
            fallingSpike.setPosition(new Vec2(27.5f, 15));
            fallingSpike.addImage(new BodyImage("data/level4data/falling_spike.png", 2));

            //giving the falling spike its own collision listener, but only when the button is
            //touched. This improves the efficiency of the code as the listener is added
            //only when needed.
            fallingSpike.addCollisionListener(collisionEvent1 -> {
                if (collisionEvent1.getOtherBody() == enemy2) {
                    enemy2.setDead(true);
                    enemy2.destroy();

                }
            });

        }

    }
}
