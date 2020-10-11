package game;

import game.levels.Level1;
import game.levels.level2.Level2;
import game.levels.level3.Level3;
import game.levels.level4.Level4;
import org.jbox2d.common.Vec2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loads previously saved state of a game.
 * This class reads a text file, unpacks all the data,
 * then set the current game to the level number in the save file.
 */
public class GameLoader {
    private String fileName;
    private Game game;
    private MyView view;

    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     * @param game current running game
     * @param view which holds the world
     */
    public GameLoader(String fileName, Game game, MyView view) {
        this.fileName = fileName;
        this.game = game;
        this.view = view;

    }

    /**
     * Read the data from the text file, and set the current game to the
     * level number in the save file. Information about the main character and stopwatch is
     * also unpacked and it overwrites the current game.
     * The correct background sound is set to the loaded world.
     * @return GameLevel read from the save file
     * @param stopWatch to change the values of the stopwatch to the ones
     *                  from the save file.
     * @throws IOException an input or output error.
     */
    public GameLevel loadGame(StopWatch stopWatch) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr); //the buffer reads a line at a time
            String line = reader.readLine(); //if there are no more lines to read it will return "null"

            String[] tokens = line.split(",");
            int levelNumber = Integer.parseInt(tokens[0]);


            float xPlayer = Float.parseFloat(tokens[1]);
            float yPlayer = Float.parseFloat(tokens[2]);
            Vec2 posPlayer = new Vec2(xPlayer, yPlayer);

            //Unpacking the solider attributes saved of the solider
            int healthSolider = Integer.parseInt(tokens[3]);
            int bulletCount = Integer.parseInt(tokens[4]);

            long startOfStopwatch = Long.parseLong(tokens[5]);
            long pausedSeconds = Long.parseLong(tokens[6]);

            //unpack the start nano second and seconds paused for.
            stopWatch.setStartNanoSeconds(startOfStopwatch);
            stopWatch.emptyNanoSecondsPausedList();
            stopWatch.getNanoSecondsPausedForList().add(pausedSeconds);

            //unpack the user name & set it.
            String userName = tokens[7];
            game.setCurrentPlayerName(userName);

            GameLevel level = null;

            //LEVEL 1
            if (levelNumber == 1) {
                level = new Level1(game);

                level.getPlayer().setPosition(posPlayer);
                level.getPlayer().setHealth(healthSolider);
                level.getPlayer().setBulletCount(bulletCount);

                game.setBackgroundMusic("data/sfx/background_music.wav");
                view.changeBackground("level1");
                game.pause();

            //LEVEL 2
            } else if (levelNumber == 2) {
                level = new Level2(game);

                level.getPlayer().setHealth(healthSolider);
                level.getPlayer().setPosition(posPlayer);
                level.getPlayer().setBulletCount(bulletCount);

                game.setBackgroundMusic("data/sfx/background_music.wav");
                view.changeBackground("level2");
                game.pause();

            //LEVEL 3
            }  else if (levelNumber == 3) {
                level = new Level3(game);

                level.getPlayer().setHealth(healthSolider);
                level.getPlayer().setPosition(posPlayer);
                level.getPlayer().setBulletCount(bulletCount);

                game.setBackgroundMusic("data/sfx/level3.wav");
                view.changeBackground("level3");
                game.pause();

            //LEVEL 4
            } else if (levelNumber == 4) {
                level = new Level4(game);

                level.getPlayer().setHealth(healthSolider);
                level.getPlayer().setPosition(posPlayer);
                level.getPlayer().setBulletCount(bulletCount);


                game.setBackgroundMusic("data/sfx/level3.wav");
                view.changeBackground("level4");
                game.pause();

            } else {
                System.out.println("Invalid level number, something went wrong..");
            }
            return level;

        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }
}
