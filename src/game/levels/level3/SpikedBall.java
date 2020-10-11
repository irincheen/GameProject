package game.levels.level3;
import city.cs.engine.*;

/**
 * Builds the deadly spiked ball.
 */
public class SpikedBall extends DynamicBody {
    private static final Shape shape = new PolygonShape(-0.018f,0.741f, 0.717f,0.042f, -0.024f,-0.738f, -0.666f,-0.021f);

    /**
     * Constructor of the ball. Called when the main character reaches a cerain area
     * in the world.
     * @param world the world to place the ball in.
     */
    public SpikedBall(World world) {
        super(world, shape);

        //source: https://opengameart.org/content/spiked-ball-0
        BodyImage image = new BodyImage("data/spikedball.png", 2);
        addImage(image);
    }
}
