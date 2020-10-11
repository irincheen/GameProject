package game;
import city.cs.engine.WorldView;
import game.levels.Bullet;
import game.levels.BulletEnemyCollision;
import game.levels.Enemy;
import game.levels.Solider;
import org.jbox2d.common.Vec2;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse handler to shoot bullets from a body.
 */
public class DropBullet extends MouseAdapter {
    WorldView view;
    Solider solider;
    Bullet bullet;
    Enemy enemy;


    public DropBullet(WorldView view, Solider solider, Enemy enemy) {
            this.view = view;
            this.solider = solider;
            this.enemy = enemy;
        }

    /**
     * Handle mouse pressed events.
     * In this case, a bullet is shot from the main character when the mouse is pressed.
     * @param e description of mouse event
     */
    @Override
        public void mousePressed(MouseEvent e) {

            //Solider can only shoot the bullets collected
            if (solider.getBulletCount() > 0) {
                bullet = new Bullet(view.getWorld());
                bullet.addCollisionListener(new BulletEnemyCollision(enemy));
                bullet.setGravityScale(1.0f);
                Vec2 soliderPos = solider.getPosition();
                Vec2 mousePos = view.viewToWorld(e.getPoint());

            //shooting the bullet in the direction of the mouse
            if (mousePos.x < soliderPos.x) {
                bullet.setPosition(new Vec2(solider.getPosition().x - 2, solider.getPosition().y));
                bullet.setLinearVelocity(new Vec2(-10, 5));

            } else {
                bullet.setPosition(new Vec2(solider.getPosition().x, solider.getPosition().y));
                bullet.setLinearVelocity(new Vec2(10, 5));

            }
            solider.decrementBulletCount();

        }
    }

    /**
     * Changes the solider. This is typically used when going to the next level or loading up a save file.
     * @param solider the new solider
     */
    public void setPlayer(Solider solider) {
        this.solider = solider;

    }

    /**
     * Setting a new value to the enemy.
     * This is usually needed when switching from one level to another.
     * @param enemy the new enemy
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;

    }

    }
