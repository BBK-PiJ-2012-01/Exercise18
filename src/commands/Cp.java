package commands;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.*;

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

    public static File getSourceFile() {
        String input;
        File source_file = null;

        System.out.print("Enter source filename: ");
        do {
            try {
                input = IOGeneric.getString();
                if (input.isEmpty()) {
                    return null;
                }

                source_file = new File(input);

                if (!source_file.exists()) {
                    System.out.print("Source file does not exist.  Try again: ");
                    source_file = null;
                }
            } catch (BadInput badInput) {
                badInput.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        } while (source_file == null);

        return source_file;
    }

    @Override
    public void run() {
        String input;
        File source_file = getSourceFile(), destination_file;

        if (source_file == null) {
            System.out.println("Received empty string: cancelling request.");
            return;
        }


        do {
            System.out.print("Enter destination filename: ");
            try {
                input = IOGeneric.getString();
                if (input.isEmpty())
                    System.out.println("Received empty string: cancelling request.");

                destination_file = new File(input);

                if (destination_file.exists()) {
                    if (destination_file.equals(source_file)) {
                        System.out.print("Destination file can't be the same as source file! try again: ");
                        break;
                    }

                    System.out.print("Destination file already exists! Overwrite? [y/n]: ");
                    if (!IOGeneric.getString().toLowerCase().equals("y"))
                        destination_file = null;
                    else {
                        // delete destination file
                        destination_file.delete();
                    }
                }
            } catch (BadInput badInput) {
                throw new RuntimeException("Bad input...");
            }

        } while (destination_file == null);

        copy(source_file, destination_file);
    }

    private void copy(File source_file, File destination_file) {
        copyFromTo(source_file, destination_file);
    }

    public static void copyFromTo(File source_file, File destination_file) {
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(source_file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't read source file, even though I just checked it exists...", e);
        }

        PrintWriter out;
        try {
            out = new PrintWriter(destination_file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't open destination file for writing...", e);
        }

        String source_line;

        try {
            while (true) {
                source_line = in.readLine();

                if (source_line == null)
                    break;

                out.write(source_line);
                out.write('\n');
            }
        } catch (IOException e) {
            out.close();
            destination_file.delete();
            throw new RuntimeException("IOException whilst writing to destination file! Cleaning up...", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("Couldn't close input stream", e);
            }
            out.close();
        }
    }
}
