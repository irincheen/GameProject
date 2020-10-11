package game.levels.level2;

import city.cs.engine.*;

/**
A simple potion body.
 */
public class Potion extends StaticBody {
    private static final Shape shape = new PolygonShape(-0.009f,0.69f, 0.465f,-0.237f, 0.027f,-0.699f, -0.432f,-0.231f);
    private final BodyImage image = new BodyImage("data/blue_potion.png", 2); //source: https://opengameart.org/content/potion-bottles

    /**
     * Constructor that creates a new potion.
     * @param w the world to place the potion in.
     */
    public Potion(World w) {
        super(w, shape);
        addImage(image);
    }
}
