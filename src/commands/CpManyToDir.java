package commands;

import BBK.PiJ01.common.Exercise;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 15:31
 */
public class CpManyToDir implements Exercise {

    @Override
    public String getTitle() {
        return "cp (many to directory)";
    }

    @Override
    public String getDescription() {
        return "Copies many files to a directory (the last string inputted)";
    }

    @Override
    public void run() {

        File input_file;
        List<File> files = new LinkedList<File>();

        while ((input_file = Cp.getSourceFile()) != null) {
            files.add(input_file);
        }

        int number_of_files = files.size();

        if (number_of_files < 2) {
            System.out.println("You must input at least two filenames (one source, one destination).");
            return;
        }

        File folder = files.get(number_of_files - 1);

        if (!folder.isDirectory()) {
            System.out.println("The last filename must be a directory (to copy the previous files to)");
            return;
        }

        files.remove(folder);

        List<File> files_not_checked_for_duplicates = new LinkedList<File>(files);

        for (File file : files) {
            if (file != folder) {
                if (!file.isFile()) {
                    System.out.format("Filename '%s' is not a file\n", file.getName());
                    return;
                }
                files_not_checked_for_duplicates.remove(file);
                for (File other_file : files_not_checked_for_duplicates) {
                    if (other_file.equals(file)) {
                        System.out.format("Filename '%s' appears more than once in the list.\n", file.getName());
                        return;
                    }
                }
            }
        }

        File destination;
        for (File file : files) {
            destination = new File(folder.getPath() + File.separator + file.getName());
            Cp.copy(file, destination);
        }

    }
}
