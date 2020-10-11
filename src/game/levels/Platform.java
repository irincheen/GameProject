package game.levels;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * Creates a platform.
 */
public class Platform extends StaticBody{
    private static final Shape shape = new BoxShape(2, 0.5f);
    private static final BodyImage image = new BodyImage("data/ground_cake.png", 1);

    /**
     * Initialise a new platform
     * @param world the current world
     * @param position vector position of the platform.
     */
    public Platform(World world, Vec2 position) {
        super(world,  shape);
        setPosition(position);
        addImage(image);

    }
}

