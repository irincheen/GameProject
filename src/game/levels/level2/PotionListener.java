package game.levels.level2;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.levels.*;

/**
Collision listener for the potion which makes the player fly for 3 seconds.
 */
public class PotionListener implements CollisionListener {
    Solider solider;

    /**
     * Constructor for the potion collision listner.
     * This listener is added when the potion is created in level 2
     * @param solider the soldier to apply the "flying" effect to.
     */
    public PotionListener(Solider solider) {
        this.solider = solider;
    }

    /**
     * Collision handler. This is called whenever a body collides with the potion.
     * There is no effect unless that body is the main character, the soldier.
     * @param collisionEvent description of collision event
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Solider) {
            collisionEvent.getReportingBody().destroy();
            solider.flyPowerUp();

        }
    }
}
