import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by Sayan Faraz on 2016-01-22.
 */
public class Engine {
    ArrayList obstacle_stack = new ArrayList(); //

    public Engine () {
        // Add 3 columns of obstacles for the beginning
        make_Multiple_Obstacles(3, obstacle_stack, beginning_constraints);
    }

    public static Obstacle make_Obstacle(int small_x, int large_x, int small_height, int large_height) {
        // Choose random stats
        int rand_x = (int)(Math.random()*(large_x - small_x)) + small_x;
        int rand_height = (int)(Math.random()*(large_height - small_height)) + small_height;

        int temp_bool = (int)(Math.round(Math.random()));
        boolean rand_orientation =  (temp_bool==1) ? true:false;

        int rand_width = (int)(Math.random()*100 + 50);

        // Init Obstacle based on random stats
        return new Obstacle(rand_x, rand_orientation, rand_height, rand_width);
    }

    public static void make_Multiple_Obstacles(int num_of_obstacles, ArrayList obstacle_list, Array constraints) {

    }
}
