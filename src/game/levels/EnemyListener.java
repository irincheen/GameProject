package game.levels;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
 * Collision listener for the enemy.
 */
public class EnemyListener implements CollisionListener {
    Solider solider;

    /**
     * Initiates the listener for the enemy.
     * It is created when the enemy is also placed in the world.
     * @param s the main character in the world.
     */
    public EnemyListener(Solider s){
        solider = s;

    }

    /**
     * Event handler when the Enemy and Soldier collide.
     * When this happens, the solider loses a significant amount
     * of health.
     * @param collisionEvent the event description
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Solider) { //when the enemy collides with the solider
            solider.decrementHealth(10);
            solider.removeAllImages();
            solider.addImage(Solider.getFacingImage("hurt"));
            solider.jump(10);

        }
    }


}
