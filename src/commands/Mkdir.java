package commands;

import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 13:50
 */
public class Mkdir implements Exercise {
    private static final String[] reserved = new String[]{".","/",":"};

    @Override
    public String getTitle() {
        return "mkdir";
    }

    @Override
    public String getDescription() {
        return "Makes a new directory in the pwd";
    }

    @Override
    public void run() {
        String input = null;

        do {
            try {
                input = IOGeneric.getString();
                for (String reserved_string : reserved) {
                    if (input.contains(reserved_string)) {
                        System.out.format("Folder names can't contain '%s', try again:\n", reserved_string);
                        input = null;
                        break;
                    }
                }
            } catch (BadInput badInput) {
                System.out.println("That was a bad input.  Try again:");
            }
        } while(input == null);

        new File(String.format("./%s", input)).mkdir();
    }
}
