package commands;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 15:56
 */
public class Tr implements Exercise {
    @Override
    public String getTitle() {
        return "tr";
    }

    @Override
    public String getDescription() {
        return "like cat, but replaces any occurrences of string1 with string2";
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

        String string1;
        do {
            try {
                System.out.print("Enter string1: ");
                string1 = IOGeneric.getString();
            } catch (BadInput badInput) {
                throw new RuntimeException("Bad input");
            }
        } while (string1 == null);

        String string2;
        do {
            try {
                System.out.print("Enter string2: ");
                string2 = IOGeneric.getString();
            } catch (BadInput badInput) {
                throw new RuntimeException("Bad input");
            }
        } while (string2 == null);

        String line;
        System.out.format("replacing '%s' for '%s'\n", string1, string2);

        try {
            do {
                line = in.readLine();
                if (line == null)
                    break;

                System.out.println(line.replaceAll(string1, string2));
            } while (true);
        } catch (IOException e) {
            throw new RuntimeException("IOException found for file: " + file.getName());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException("Can't close buffered reader!");
            }
        }
    }
}
