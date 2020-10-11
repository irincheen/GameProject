package game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
Writes and reads the nano seconds from the text file.
Writes how many nanoseconds the player took to complete the game.
 */
public class TimeWriterAndReader {
    public TimeWriterAndReader () {

    }

    /**
     * Saves the current state of the stopwatch
     * @param timeToSave the sum of nanoseconds the game is paused for.
     * @param name of the user
     * @throws IOException an input or output error.
     */
    public void saveTime(long timeToSave, String name) throws IOException {
        FileWriter writer = null;
        long milliseconds = timeToSave/100000;
        String seconds = Double.toString(Double.parseDouble(String.valueOf(milliseconds))/10000);

        try {
            writer = new FileWriter("data/time_leaderboard.txt", true);
            writer.write(name+ " ->> " + seconds + "secs"+ "\n");

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * Adds lines of text file to a list.
     * @return List containing all lines of the text file.
     * @throws IOException an input or output error.
     */
    public List<String> getScores() throws IOException {
        List<String> myList = new ArrayList<>();
        FileReader fr = null;
        BufferedReader reader = null;

        try {
           //the file to be opened for reading
            FileInputStream fis=new FileInputStream("data/time_leaderboard.txt");
            Scanner sc=new Scanner(fis);

            while(sc.hasNextLine()) { //each line of the text file will be added to the list as a String
                myList.add(sc.nextLine());

            }
            sc.close();

        }
        catch(IOException e) {
            e.printStackTrace();

        }
        return myList;

    }

}
