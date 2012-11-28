package commands;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.*;

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
        File file = null;
        System.out.print("Enter file name: ");

        do {
            try {
                input = IOGeneric.getString();
                if (input.isEmpty()) {
                    System.out.println("No input, cancelling request");
                    return;
                }
                file = new File(input);
                if (!file.exists()) {
                    System.out.print("No such file, try again: ");
                    file = null;
                }
            } catch (BadInput badInput) {
                System.out.print("Didn't understand that, try again: ");
            }
        } while(file == null);

        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't read file, but had just checked that it existed...");
        }

        String line;
        IOGeneric.printTitle("Start of file");

        try {
            do {
                line = in.readLine();
                if (line == null)
                    break;
                System.out.println(line);
            } while(true);
        } catch (IOException e) {
            IOGeneric.printTitle("End of file");
        }

    }
}
