import BBK.PiJ01.common.BadInput;
import BBK.PiJ01.common.Exercise;
import BBK.PiJ01.common.ExerciseChooser;
import commands.*;

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
        exercises.add(new Cp());
        exercises.add(new CpManyToDir());
        exercises.add(new Tr());
        exercises.add(new Sort());

        ExerciseChooser ech = new ExerciseChooser(exercises);
        ech.run();
    }
}
