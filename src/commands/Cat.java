package commands;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 14:08
 */
public class Cat implements Exercise {

    @Override
    public String getTitle() {
        return "cat";
    }

    @Override
    public String getDescription() {
        return "Print file contents to stdout";
    }

    @Override
    public void run() {
        String input;
        List<File> files = new LinkedList<File>();
        File input_file;

        do {
            System.out.print("Enter file name: ");
            try {
                input = IOGeneric.getString();
                if (input.isEmpty())
                    break;

                input_file = new File(input);
                if (!input_file.exists()) {
                    System.out.print("No such file, try again: ");
                }

                files.add(input_file);
            } catch (BadInput badInput) {
                System.out.print("Didn't understand that, try again: ");
            }
        } while(true);

        BufferedReader in;

        for (File file : files) {
            try {
                in = new BufferedReader(new FileReader(file));
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Couldn't read file, but had just checked that it existed...");
            }

            String line;
            IOGeneric.printTitle("Start of file: " + file.getName());

            try {
                do {
                    line = in.readLine();
                    if (line == null)
                        break;
                    System.out.println(line);
                } while(true);
            } catch (IOException e) {
                throw new RuntimeException("IOException found for file: " + file.getName());
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("Can't close buffered reader!");
                }
            }

            IOGeneric.printTitle("End of file: " + file.getName());
            System.out.println();
        }

    }
}
