package game.levels.level3;
import city.cs.engine.*;
import game.levels.*;

/**
Collision listener for the dropping spiked ball in level3
 */
public class SpikedBallListener implements CollisionListener {
    Solider solider;

    /**
     * Builds the listener for the SpikedBall.
     * @param solider the character it damages.
     */
    public SpikedBallListener(Solider solider) {
        this.solider = solider;
    }

    @Override
    public void collide(CollisionEvent collisionEvent) {
        //When the solider collides with the spiked ball, its health halves and spikedBall is destroyed.
        if (collisionEvent.getOtherBody() instanceof Solider) {
            solider.decrementHealth((int) (solider.getHealth()*0.5f));
            collisionEvent.getReportingBody().destroy();
        }

    }
}
