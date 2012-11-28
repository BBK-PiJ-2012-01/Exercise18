package commands;

import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.IOGeneric;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 13:42
 */
public class Ls implements Exercise {

    @Override
    public String getTitle() {
        return "ls";
    }

    @Override
    public String getDescription() {
        return "Lists all files (not folders) in the directory";
    }

    @Override
    public void run() {

        List<String> filenames = new LinkedList<String>();
        List<String> foldernames = new LinkedList<String>();


        for (File file : new File(".").listFiles()) {
            if (file.isFile())
                filenames.add(file.getName());
            else if (file.isDirectory())
                foldernames.add(file.getName());
        }

        IOGeneric.printTitle("Directories");
        printList(foldernames);

        System.out.println();

        IOGeneric.printTitle("Files");
        printList(filenames);

    }

    private <T> void printList(List<T> lst) {
        for (T element : lst) {
            System.out.println(element.toString());
        }
    }
}
