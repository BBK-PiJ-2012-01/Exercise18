package commands;

import BBK.PiJ01.common.Exercise;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 16:10
 */
public class Sort implements Exercise {
    @Override
    public String getTitle() {
        return "sort";
    }

    @Override
    public String getDescription() {
        return "Like cat, but sorts the output lines alphabetically by the first letter\n" +
                "on each line.";
    }

    @Override
    public void run() {
        File file = Cp.getSourceFile();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found, but just confirmed it was found...", e);
        }

        String input_line;
        List<String> lines = new LinkedList<String>();

        try {
            while((input_line = in.readLine()) != null) {
                lines.add(input_line);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        Collections.sort(lines);

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
