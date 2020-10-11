package game;
import java.util.ArrayList;

/**
Stopwatch that uses the nano time of the system.
 */
public class StopWatch {

    /**
     * The nanosecod of the system at which the stopwatch is started at
     */
    private long startNanoSeconds;

    /**
     * The nanosecod of the system at which the stopwatc is eneded at
     */
    private long endNanoSeconds;

    /**
     * The nanosecond at which the stopwatch is paused.
     */
    private long pausedNanoSecond;

    private boolean isStarted;
    private boolean isEnded;


    /**
     * To store the lengths the game is paused for.
     * It allows the stopwatch to be paused multiple times.
     */
    private ArrayList<Long> nanoSecondsPausedForList = new ArrayList<>();

    /**
     * Constructor for the stopwatch.
     * Called once when the game is started.
     */
    public StopWatch() {
        isStarted = false;
        isEnded = false;

    }

    /**
    Starts the stopwatch.
     */
    public void start(){
        isStarted = true;
        startNanoSeconds = System.nanoTime();
    }

    /**
    Stop the stopwatch.
     */
    public void stop() {
        isEnded = true;
        endNanoSeconds = System.nanoTime();
    }

    /**
     Pauses the stopwatch.
     */
    public void pause() {
        if (isStarted) {
            pausedNanoSecond = System.nanoTime(); //only allow the stopwatch to be paused if it's started

        } else {
            System.out.println("Timer isn't started..");
        }
    }

    /**
    Returns the final value of the stopwatch in nanoseconds.
     @return long nanoseconds stopwatch ran for
     */
    public long getNanoTime() {

        /**
         * totalPausedFor will hold the sum from nanoSecondsPausedForList.
         * The value of totalPausedFor is subtracted from the (endNanoSeconds - startNanoSeconds)
         * Even if the stopwatch was never paused, nanoSecondsPaused will be 0
         */
        long totalPausedFor = 0;

        for (long element : nanoSecondsPausedForList) {
            //adding the total time the stopwatch was paused for, to subtract it for the total time running
            totalPausedFor += element;

        }
        long finalNanoSeconds = endNanoSeconds - startNanoSeconds - totalPausedFor;
        return finalNanoSeconds;

    }


    /**
     * Resume the stopwatch.
     * This is done by registering the nanosecod resume() was called at.
     * then calculating how long ago pause() was called and
     * adding this value to nanoSecondsPausedForList.
     */
    public void resume() {
        if (isStarted) {
            long nanoSecondResumedAt = System.nanoTime();

            long nanoSecondsPausedFor = nanoSecondResumedAt - pausedNanoSecond;

            nanoSecondsPausedForList.add(nanoSecondsPausedFor); //the time the stopwatch was paused for is added to the list

        } else {
            System.out.println("Timer isn't started..");

        }
    }

    /**
     * Empty the List that holds the nano seconds the stopwatch was paused for.
     */
    public void emptyNanoSecondsPausedList () {
        nanoSecondsPausedForList = new ArrayList<>();
    }

    /**
     * Typically called when calculating the final value of the stopwatch.
     * @return long the sum of paused nanosecods.
     */
    public long getSumOfNanoSecondsPaused() {

        /*
          TotalPausedFor will hold the sum from nanoSecondsPausedForList
         */
        long totalPausedFor = 0;

        for (long element : nanoSecondsPausedForList) {
            totalPausedFor += element;
        }
        return totalPausedFor;

    }

    /**
     * Changes the value of startNanoSeconds.
     * Typically called when loading a save file.
     * @param startNanoSeconds the new start value
     */
    public void setStartNanoSeconds(long startNanoSeconds) {
        this.startNanoSeconds = startNanoSeconds;
    }

    /**
     * The nanosecond of the system at which the stopwatch is started at
     * @return the start nanosecond
     */
    public long getStartNanoSeconds() {
        return startNanoSeconds;
    }

    /**
     * Returns the boolean value if the stopwatch was ended
     * @return is the stopwatch eneded?
     */
    public boolean isEnded() {
        return isEnded;
    }

    /**
     * Returns the boolean value if the stopwatch was started
     * @return is the stopwatch started?
     */
    public boolean isStarted() {
        return isStarted;
    }

    /**
     * Returns the current nano second the nanosecod was paused at.
     * @return nanosecond stopwatch paused at
     */
    public long getPausedNanoSecond() {
        return pausedNanoSecond;
    }

    /**
     * It returns the list stores the lengths the game is paused for.
     * @return the list is returned
     */
    public ArrayList<Long> getNanoSecondsPausedForList() {
        return nanoSecondsPausedForList;
    }
}
