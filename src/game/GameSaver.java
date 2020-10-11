package game;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Saves the current state of the game to a text file.
 */
public class GameSaver  {
    private String fileName;
    Game game;

    /**
     *
     * @param fileName name of text file the game state to be saved to
     * @param game current running game.
     */
    public GameSaver(String fileName, Game game) {
        this.game = game;
        this.fileName = fileName;
    }
    /**
    Saving the current level status (level number, player health and position, how long the game has been running for)
     The nanoseconds the stopwatch was started at is saved and the sum of the nanoseconds the stopwatch was paused for is
     saved in the file.
     The game state is set to "saved" to display a "saved" message on the screen to the user.
     The data in the file is always overwritten with each save.
     @param stopWatch save the state of running stopwatch.
     @throws IOException an input or output error.
     */
    public void saveGame(StopWatch stopWatch) throws IOException {
        FileWriter writer = null;
        GameLevel currentLevel = game.getWorld();

        try {
            writer = new FileWriter(fileName);
            writer.write(game.getCurrentLevel() + ","+currentLevel.getPlayer().getPosition().x + ","
                    + currentLevel.getPlayer().getPosition().y + "," + currentLevel.getPlayer().getHealth()+","
                    + currentLevel.getPlayer().getBulletCount() +","+ stopWatch.getStartNanoSeconds() + ","
                    + stopWatch.getSumOfNanoSecondsPaused() + "," + game.getCurrentPlayerName()+"\n");
            //save the stop watch status (start nanosecond & total time it has been paused for)

            game.setGameState("saved"); //so MyView can display the "Saved progress" message

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
