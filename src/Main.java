import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.ExerciseChooser;
import commands.Cat;
import commands.Ls;
import commands.Mkdir;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Sam Wright
 * Date: 28/11/2012
 * Time: 13:48
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BadInput {
        ArrayList<Exercise> exercises = new ArrayList<Exercise>();

        exercises.add(new Ls());
        exercises.add(new Mkdir());
        exercises.add(new Cat());


        ExerciseChooser ech = new ExerciseChooser(exercises);
        ech.run();
    }
}
