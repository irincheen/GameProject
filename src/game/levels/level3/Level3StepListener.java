package game.levels.level3;

import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.levels.Solider;

/**
 * Step listener for level3.
 * Used to drop a deadly spike when the main character reaches a certain
 * area in the game.
 */
public class Level3StepListener implements StepListener {

    /**
     * Stops the spiked ball from dropping at each frame.
     * So it is dropped only once
     */
    private int noOfTimesBallDropped = 0;
    private Solider solider;
    private Level3 level;

    public Level3StepListener(Solider solider, Level3 level) {
        this.solider = solider;
        this.level = level;
    }

    @Override
    public void postStep(StepEvent stepEvent) {

        //The spiked ball should drop only once.
        float soliderX = solider.getPosition().x;
        float soliderY = solider.getPosition().y;

        if (noOfTimesBallDropped != 1) {
            if (soliderX <= -15 && soliderX >= -20 && soliderY >= 5 && soliderY <= 9) {
                level.dropSpike();
                noOfTimesBallDropped++;

            }
        }

    }

    @Override
    public void preStep(StepEvent stepEvent) {

    }
}
