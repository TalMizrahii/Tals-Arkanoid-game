import GameRelated.Counter;
import Levels.DirectHit;
import Levels.FinalFour;
import Levels.Green3;
import Levels.WideEasy;
import RunRelated.AnimationRunner;
import RunRelated.GameFlow;
import RunRelated.LevelInformation;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * the RunClass of the game.
 *
 * <p>suppose to hold the main function and run the game from it.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class RunClass {
    private static final int FRAME_PER_SECONDS = 1000 / 60; //the frame rate of the class.

    /**
     * conversion function.
     *
     * <p>converts an arrays strings to integers.
     *
     * @param levelsArr the levels array.
     * @param numbers   array of integers.
     * @return an array of integers
     */
    public static List<Integer> stringsToInts(String[] numbers, LevelInformation[] levelsArr) {
        List<Integer> arr = new ArrayList<>();
        int flag = 0;
        for (String i : numbers) {
            //checking if it's a number.
            for (int k = 0; k < i.length(); k++) {
                int x = i.charAt(k);
                // if the number is not valid, go to the next one.
                if (x < 49 || x > 57) {
                    flag = 1;
                    break;
                }
            }
            //if the number is in the levels range, add it.
            if (flag == 0 && Integer.parseInt(i) <= levelsArr.length && Integer.parseInt(i) > 0) {
                arr.add(Integer.parseInt(i));
            }
            flag = 0;
        }
        return arr;
    }

    /**
     * the main function.
     * starting the game and running it.
     *
     * @param args currently no value used in this array.
     */
    public static void main(String[] args) {
        LevelInformation[] levelArr = {new DirectHit(), new WideEasy(), new Green3(), new FinalFour()};
        List<LevelInformation> levels = new ArrayList<>();
        List<Integer> arr = stringsToInts(args, levelArr);

        if (!arr.isEmpty()) {
            //setting the levels.
            for (Integer integer : arr) {
                levels.add(levelArr[integer - 1]);
            }
        } else {
            levels.addAll(Arrays.asList(levelArr));
        }


        //Setting a default "GUI" member in the designated size.
        GUI gui = new GUI("TAL'S ARKNOID", 800, 600);

        //creating the game and running it.
        GameFlow gameFlow = new GameFlow(new AnimationRunner(gui, FRAME_PER_SECONDS),
                gui.getKeyboardSensor(), new Counter(0), gui);
        gameFlow.runLevels(levels);
    }
}
