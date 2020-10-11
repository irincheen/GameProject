package game;

import city.cs.engine.*;
import game.levels.*;

/**
 * Collision listener that allows the solider to collect the bullets.
 */
public class PickupBullet implements CollisionListener {
    private Solider solider;

    public PickupBullet(Solider solider) {
        this.solider = solider;
    }

    /**
     * Handles the collision between the main character and bullets.
     * Whenever a bullet collides with the solider, its buller count is
     * incremented by 1.
     * @param e the event of the 2 bodies colliding
     */
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() == solider) {
            solider.incrementBulletCount();
            e.getReportingBody().destroy();
        }
    }
    
}
