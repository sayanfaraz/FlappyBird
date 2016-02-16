import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

/**
 * Created by Sayan Faraz on 2016-01-22.
 */
public class Engine {
    ArrayList<Obstacle> obstacle_stack; // Arraylist of obstacles
    int[] beginning_constraints;

    public Engine () {
        // Init beginning constraints
        beginning_constraints = new int[] {15, 30, 50, 100};

        // Add 3 columns of obstacles for the beginning
        obstacle_stack = new ArrayList<>();
        this.add_Multiple_Obstacles(3, beginning_constraints);
    }

    /**
     * Given parameters, make a random obstacle.
     * @param small_x {int} Minimum x value
     * @param large_x {int} Maximum x value
     * @param small_height {int} Minimum height
     * @param large_height {int} Maximum height
     * @return {Obstacle} Random obstacle
     */
    public static Obstacle make_Obstacle(int small_x, int large_x,
                                         int small_height, int large_height) {
        // Choose random stats
        int rand_x = (int)(Math.random()*(large_x - small_x)) + small_x;
        int rand_height = (int)(Math.random()*(large_height - small_height))
                + small_height;

        int temp_bool = (int)(Math.round(Math.random()));
        boolean rand_orientation = (temp_bool==1);

        int rand_width = (int)(Math.random()*100 + 50);

        // Init Obstacle based on random stats
        return new Obstacle(rand_x, rand_orientation, rand_height, rand_width);
    }

    /**
     * Given number of obstacles, list of parameters, return a list of
     * random Obstacles.
     * @param num_of_obstacles {int} Number of obstacles
     * @param constraints {int[]} Array of constraints, 4 parameters
     * @return {ArrayList} List of obstacles
     */
    public static ArrayList<Obstacle> make_Multiple_Obstacles(int num_of_obstacles,
                                               int[] constraints) {

        // Check if constraints is formatted properly
        if (is_format_constraints(constraints)) {

            // Create obstacles array list
            ArrayList<Obstacle> list_of_Obstacles = new ArrayList<>();

            // Create obstacles
            for (int i = 0; i < num_of_obstacles; i++) {
                list_of_Obstacles.add(0,make_Obstacle(constraints[0],
                        constraints[1], constraints[2], constraints[3]));
            }

            // Return Constraints list
            return list_of_Obstacles;
        }
        else throw new IllegalArgumentException("You need to enter 4 integer " +
                "parameters to make an Obstacle.");
    }

    /**
     * Given number of obstacles, list of parameters, add obstacles to
     * obstacle_stack
     * @param num_of_obstacles {int} Number of obstacles
     * @param constraints {int[]} Array of constraints, 4 parameters
     */
    public void add_Multiple_Obstacles(int num_of_obstacles, int[]
            constraints) {
        // Create list of obstacles
        try {
            ArrayList<Obstacle> new_obstacles = make_Multiple_Obstacles
                    (num_of_obstacles, constraints);

            // Run through each obstacle
            for (int i = 0; i < num_of_obstacles; i++)
                this.obstacle_stack.add(0, new_obstacles.get(i));
        }
        catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("You need to enter 4 integer " +
                    "parameters to make an Obstacle.");
        }


    }
    // HELPER FUNCTIONS---------------------------------------------------------
    /**
     * Checks if constraints array is formatted properly.
     * @param constraints {int[]} Array of constraints
     * @return {boolean}
     */
    private static boolean is_format_constraints(int[] constraints) {
        // Is length=4? Is small_x < large_x? Is small_height < large_height?
        if (constraints.length==4 && constraints[0] < constraints[1] &&
                constraints[2] < constraints[3]) return true;
        else return false;
    }
}
