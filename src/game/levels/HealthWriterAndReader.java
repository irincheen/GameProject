package game.levels;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
Writer and reader that stores and retrives the health of the solider once a
level is completed.
 */
public class HealthWriterAndReader {
    /**
     * The constructor for the writer and reader.
     */
    public HealthWriterAndReader() {
    }

    /**
    Health of the soldier is written to the file once game progresses to the
    next level
     @param solider character to be saved from
     @throws IOException an input or output error
     */
    public void saveHealth(Solider solider) throws IOException {
        FileWriter writer = null;
        String soldierHealth = Integer.toString(solider.getHealth());

        try {
            writer = new FileWriter("data/solider_health.txt"); //data in the file will always be overwritten, not appended.
            writer.write(soldierHealth);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Returns the integer value in the text file that represents the solider's
     * health from the previous level.
     * @return the integer value in the text file
     * @throws IOException an input or output error
     */
    public int getPreviousHealth() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        int soliderHealth = 100;

        try {
            fr = new FileReader("data/solider_health.txt");

            reader = new BufferedReader(fr); //the buffer reads a line at a time
            String line = reader.readLine(); //if there are no more lines to read it will return "null"

            soliderHealth = Integer.parseInt(line);
            return soliderHealth;

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
