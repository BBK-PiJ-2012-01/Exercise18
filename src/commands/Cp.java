package commands;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 14:31
 */
public class Cp implements Exercise {
    @Override
    public String getTitle() {
        return "cp";
    }

    @Override
    public String getDescription() {
        return "Copies one file to another filename";
    }

    @Override
    public void run() {
        String input;
        File source_file = null, destination_file = null;

        System.out.print("Enter source filename: ");
        do {
            try {
                input = IOGeneric.getString();
                if (input.isEmpty())
                    System.out.println("Received empty string: cancelling request.");

                source_file = new File(input);

                if (!source_file.exists()) {
                    System.out.print("Source file does not exist.  Try again: ");
                    source_file = null;
                }
            } catch (BadInput badInput) {
                badInput.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } while (source_file == null);


        do {
            System.out.print("Enter destination filename: ");
            try {
                input = IOGeneric.getString();
                if (input.isEmpty())
                    System.out.println("Received empty string: cancelling request.");

                destination_file = new File(input);

                if (destination_file.exists()) {
                    System.out.print("Destination file already exists! Overwrite? [y/n]: ");
                    if (!IOGeneric.getString().toLowerCase().equals("y"))
                        destination_file = null;
                    else {
                        // delete destination file
                        destination_file.delete();
                        destination_file.createNewFile();
                    }
                }
            } catch (BadInput badInput) {
            } catch (IOException e) {
                throw new RuntimeException("Couldn't overwrite destination file.");
            }
        } while (destination_file == null);

        try {
            destination_file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Couldn't create new file: " + destination_file.getName());
        }

        BufferedReader in;

        try {
            in = new BufferedReader(new FileReader(source_file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't read source file, even though I just checked it exists...");
        }

        PrintWriter out;
        try {
            out = new PrintWriter(destination_file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't open destination file for writing, even though I just checked it exists...");
        }

        String source_line;

        while (true) {
            try {
                source_line = in.readLine();
                out.write(source_line);
            } catch (IOException e) {
                out.close();
                destination_file.delete();
                throw new RuntimeException("IOException whilst writing to destination file! Cleaning up...");
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    throw new RuntimeException("Couldn't close input stream");
                }
                out.close();
            }
        }

    }
}
