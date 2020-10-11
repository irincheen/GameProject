package game.levels;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
Collision listener for the lava.
 It is added to the Lava body when the Lava is created in GameLevel
 */
public class LavaListener implements CollisionListener {
    Solider solider;
    Enemy enemy;

    /**
     * Constructor for the listener.
     * @param s current main character
     * @param enemy main enemy in the game
     */
    public LavaListener(Solider s, Enemy enemy) {
        solider = s;
        this.enemy = enemy;

    }

    /**
     * Event handler when any body collides with the lava.
     * When the main character collides with the Lava, it loses health and changes
     * its appearance to indicate it took damage.
     * Any other body that collides with the Lava is destroyed.
     * @param collisionEvent description of the collision event.
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Solider) {
            solider.decrementHealth(10);
            solider.removeAllImages();
            solider.addImage(Solider.getFacingImage("hurt"));
            solider.jump(10);

        } else if (collisionEvent.getOtherBody() instanceof Bullet ) {
            collisionEvent.getOtherBody().destroy();

        } else if (collisionEvent.getOtherBody() instanceof Enemy) {
            collisionEvent.getOtherBody().destroy();
            enemy.setIsDead(true);

        } else {
            collisionEvent.getOtherBody().destroy();
        }

    }
}
