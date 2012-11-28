package other;

import BBK.PiJ01.common.Exercise;
import commands.Cp;

import java.io.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 16:26
 */
public class TempAverages implements Exercise {
    @Override
    public String getTitle() {
        return "Temperature Averages";
    }

    @Override
    public String getDescription() {
        return "Takes a CSV file of temperatures and prints to screen the\n" +
                "average temperatures for each line, and for the whole file.";
    }

    @Override
    public void run() {
        File file = Cp.getSourceFile();
        if (file == null) {
            System.out.println("Received empty string: cancelling request.");
            return;
        }

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't open file, but I could earlier...", e);
        }

        String input_line;
        String[] line_temps;
        int line_total, line_temp_count;
        int total=0, temp_count=0;

        try {
            while ((input_line = in.readLine()) != null) {
                line_total = 0;
                line_temp_count = 0;
                line_temps = input_line.split(",");

                for (String temp : line_temps) {
                    line_total += Integer.valueOf(temp.trim());
                    ++line_temp_count;
                }
                System.out.println("Line average: " + (line_total / line_temp_count));
                total += line_total;
                temp_count += line_temp_count;
            }

            System.out.println("\nFile average: " + (total / temp_count));
        } catch (IOException e) {
            throw new RuntimeException("Got IOException when reading input", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("Got IOException when closing input", e);
            }
        }

    }
}
