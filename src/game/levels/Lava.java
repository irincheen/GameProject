package game.levels;
import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

/**
 * A basic body that damages anything that touches it.
 */
public class Lava extends StaticBody {
    /**
     * Constructor for a basic body that represents Lava
     * @param w the world to place the body in
     * @param s the shape of the body
     */
    public Lava(World w, Shape s) {
        super(w, s);
        setPosition(new Vec2(0, -14));
        addImage(new BodyImage("data/lava14.png", 2));

    }
}
