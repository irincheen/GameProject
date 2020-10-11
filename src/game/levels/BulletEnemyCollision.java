package game.levels;
import game.levels.level4.*;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

/**
Collision listener for the bullet shot.
 */
public class BulletEnemyCollision implements CollisionListener {
    Enemy enemy;
    Enemy2 enemy2;

    /**
     * The constructor that builds the listener.
     * It is initiated when a bullet is shot from the main character.
     * @param enemy the bullet to collide with
     */
    public BulletEnemyCollision(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Collision handler triggered when the Enemy and Bullet collide.
     * @param collisionEvent the collision event
     */
    @Override
    public void collide(CollisionEvent collisionEvent) {
        if (collisionEvent.getOtherBody() instanceof Enemy) {
            collisionEvent.getReportingBody().destroy();

            //Destroy enemy when hitsPossible reaches 0
            if (enemy.getHitsPossible() == 1) {
                collisionEvent.getOtherBody().destroy();
                enemy.setIsDead(true);
            }
            enemy.decrementHitsPossible();


        }
    }
}
