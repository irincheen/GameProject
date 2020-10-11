package game.levels;
import city.cs.engine.*;

/**
 * Builds the bullet.
 */
public class Bullet extends DynamicBody {
    private static final Shape shape = new CircleShape(0.3f);
    private static final BodyImage image = new BodyImage("data/bullet.png", 0.6f); //source of image: https://images-eu.ssl-images-amazon.com/images/I/31azfWpHlTL.png

    /**
     * Constructor for the bullet.
     * @param world the GameLevel to place the bullet in.
     */
    public Bullet(World world){
        super(world, shape);
        addImage(image);

    }




}
