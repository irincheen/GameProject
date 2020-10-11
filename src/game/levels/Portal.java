package game.levels;
import city.cs.engine.*;

/**
 * Portals in a game. When the soldier collides with it and the
 * current level is complete, the player will commence to the
 * next level.
 */
public class Portal extends StaticBody {
    
    /**
     * Initialise a new portal.
     * @param world The world.
     */
    public Portal(World world) {
        super(world, new CircleShape(1.5f));
        addImage(new BodyImage("data/portal.gif", 3));
    }
}
