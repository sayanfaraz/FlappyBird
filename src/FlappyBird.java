import com.sun.org.apache.bcel.internal.generic.RET;

import javax.sound.sampled.ReverbType;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class FlappyBird {

    // VARIABLES----------------------------------------------------------------
    private int[] prev_pos = new int[2]; // prev pos of FlappyBird
    private int[] pos = new int[2];  // (x,y) position of FlappyBird
    private boolean alive;  // is FlappyBird alive or dead?
    private int[] img_dimensions = new int[2];
    private float fall_time; // time that FlappyBird has been falling

    public FlappyBird() {
        pos[0] = 0;
        pos[1] = 0;  // set pos to (0,0)
        img_dimensions = FlappyBird.scaleBirdInts(10);

        alive = true;  // FlappyBird is alive
        fall_time = 0;
    }


    // GETTERS, SETTERS---------------------------------------------------------

    public int[] getPos() {
        int x = this.pos[0];
        int y = this.pos[1];

        int[] ret_pos = new int[]{x, y};

        return ret_pos;
    }

    public void setPos(int[] pos) {
        setPrev_pos(this.getPos());

        int new_pos_x = pos[0];
        int new_pos_y = pos[1];

        int[] new_pos = new int[]{new_pos_x, new_pos_y};
        this.pos = new_pos;
    }


    public int[] getPrev_pos() {
        int x = this.prev_pos[0];
        int y = this.prev_pos[1];

        int[] ret_pos = new int[]{x, y};

        return ret_pos;
    }

    public void setPrev_pos(int[] prev_pos_in) {
        int prev_pos_x = prev_pos_in[0];
        int prev_pos_y = prev_pos_in[1];
        int[] new_prev_pos = new int[]{prev_pos_x, prev_pos_y};
        this.prev_pos = new_prev_pos;
    }

    public boolean isAlive() {
        boolean ret_bool = this.alive;
        return ret_bool;
    }

    public void setAlive(boolean alive) {
        boolean new_bool = alive;
        this.alive = new_bool;
    }


    public int[] getImg_dimensions() {

        int x = this.img_dimensions[0];
        int y = this.img_dimensions[1];

        int[] ret_list = new int[]{x, y};

        return ret_list;
    }

    public void setImg_dimensions(int[] img_dimensions) {

        int x = img_dimensions[0];
        int y = img_dimensions[1];

        int[] new_img_dimensions = new int[]{x, y};

        this.img_dimensions = new_img_dimensions;
    }


    public float getFall_time() {

        float ret_float = this.fall_time;

        return ret_float;
    }

    public void setFall_time(float fall_time) {

        float new_fall_time = fall_time;

        this.fall_time = new_fall_time;
    }


    // FLAPPY BIRD IMAGE--------------------------------------------------------

    /**
     * Returns scaled dimensions of bird image, given a scale factor.
     *
     * @param scale_factor {int}
     * @return {int[]} {new_width, new_height}
     */
    private static int[] scaleBirdInts(int scale_factor) {
        // Init ret list
        int[] ret_list;

        int new_width = 973 / scale_factor;
        int new_height = 782 / scale_factor;

        // Return scaled parameters
        ret_list = new int[]{new_width, new_height};

        return ret_list;
    }

    // GAME FUNCTIONALITY-------------------------------------------------------

    // CLICK
    public void clickBird() {
        moveUp(50);
        fall_time = 0;
    }

    // FALL
    public void fall() {
        int fall_distance = (int) (fall_time * fall_time);
        moveDown(fall_distance);
        fall_time += 0.25;
    }

    // KILL

    public void kill() {
        this.setAlive(false);
    }

    public void resurrect() {
        this.setAlive(true);
    }


    // RELATIVE POS

    public boolean onXObstacle(Obstacle obstacle) {
        if (this.pos[0] >= obstacle.getXpos()
                && this.pos[0] <= obstacle.getXpos() + obstacle.getWidth()) {
            return true;
        } else return false;
    }

    public boolean onYObstacle(Obstacle obstacle) {
        // If obstacle is oriented up
        if (obstacle.isOrientatedUp()) {
            if (this.pos[1] >= obstacle.getDist_from_origin()) {
                return true;
            } else return false;
        }
        // If obstacle is oriented down
        else {
            if (this.pos[1] <= -1 * obstacle.getDist_from_origin()) {
                return true;
            } else return false;
        }
    }

    public boolean insideObstacle(Obstacle obstacle) {
        if (this.onXObstacle(obstacle) && this.onYObstacle(obstacle)) {
            return true;
        } else return false;
    }

    // BASIC GAME FUNCTIONALITY

    // MOVE

    public void moveUp() {
        moveUp(5);
    }

    public void moveUp(int amount) {
        int[] curr_pos = this.getPos();

        int[] new_pos = curr_pos;

        new_pos[1] -= amount;

        this.setPos(new_pos);
    }

    public void moveDown() {
        moveDown(5);
    }

    public void moveDown(int amount) {
        int[] curr_pos = this.getPos();

        int[] new_pos = curr_pos;

        new_pos[1] += amount;

        this.setPos(new_pos);
    }

    public void hasCollided(Obstacle obstacle) {
        if (this.insideObstacle(obstacle)) {
            this.setAlive(false);
        }
    }
}


