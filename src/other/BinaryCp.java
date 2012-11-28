package other;

import BBK.PiJ01.common.Exercise;
import commands.Cp;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 16:41
 */
public class BinaryCp extends Cp {
    @Override
    public String getTitle() {
        return "cp (for binary files)";
    }

    @Override
    public String getDescription() {
        return "Copies one binary file to a destination file";
    }

    public static void copy(File source_file, File destination_file) {
        FileInputStream in;

        try {
            in = new FileInputStream(source_file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't read from source file...", e);
        }

        PrintWriter out;
        try {
            out = new PrintWriter(destination_file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't open destination file for writing...", e);
        }

        int character;

        try {
            while ((character = in.read()) != -1) {
                out.write(character);
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException while reading file", e);
        } finally {
            out.close();
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException while closing source file", e);
            }
        }

    }
}
