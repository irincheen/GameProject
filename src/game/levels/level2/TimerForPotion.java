package game.levels.level2;
import game.levels.Solider;
import org.jbox2d.common.Vec2;

import java.util.Timer;
import java.util.TimerTask;

/**
The set of actions that will be running for the duration of the timer.
 It makes the solider fly for approx. 3 seconds, then come straight down.
 */
public class TimerForPotion extends TimerTask {
    int sec = 0;
    Timer timer;
    Solider solider;

    /**
     * Constructor for the task to be ran until the timer is finshed.
     * @param timer the timer
     * @param solider the character to fly
     */
    public TimerForPotion(Timer timer, Solider solider) {
        this.timer = timer;
        this.solider = solider;
    }

    /**
     * The action to be performed by this timer task whilst the timer is running.
     */
    @Override
    public void run() {
        sec++;
        if (sec == 60){
            //The solider will fly for approx 3 seconds
            solider.applyForce(new Vec2(0, -700));
            solider.applyImpulse(new Vec2(0, -700));
            timer.cancel();
            sec = 0;

        } else if (sec <= 60){
            solider.setLinearVelocity(new Vec2(0, 5));
        }
    }

}
