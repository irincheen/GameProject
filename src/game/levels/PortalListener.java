package game.levels;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.Game;

/**
 * Collision listener for the portal. If the level is complete
 * the soldier will go to the next level.
 */
public class PortalListener implements CollisionListener {
    Game game;

    /**
     * Constructor for the portal, it is added when the portal is created in GameLevel.
     * @param game the current game
     */
    public PortalListener(Game game) {
        this.game = game;

    }


    /**
     * Collide event handler.
     * Called when a body collides with the portal.
     * Its main purpose is to transfer the main character from the current
     * level to the next one. Any other body that collides with the portal is destroyed.
     * @param collisionEvent description of the collision event.
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Solider) {
            game.goToNextLevel();

        } else {
            collisionEvent.getOtherBody().destroy();
        }
    }
}
