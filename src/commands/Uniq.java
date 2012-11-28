package commands;

import BBK.PiJ01.common.Exercise;

import java.io.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 16:16
 */
public class Uniq implements Exercise {
    @Override
    public String getTitle() {
        return "uniq";
    }

    @Override
    public String getDescription() {
        return "Like cat, but removes duplicate lines.";
    }

    @Override
    public void run() {
        File file = Cp.getSourceFile();
        if (file == null) {
            System.out.println("No filename entered: cancelling request.");
            return;
        }

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found, but just confirmed it was found...", e);
        }

        String input_line, previous_input_line = "";
        List<String> lines = new LinkedList<String>();

        try {
            while ((input_line = in.readLine()) != null) {
                if (!previous_input_line.equals(input_line)) {
                    System.out.println(input_line);
                    previous_input_line = input_line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
