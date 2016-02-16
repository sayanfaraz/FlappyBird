/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class FlappyBird {

    // VARIABLES----------------------------------------------------------------
    private int[] pos = new int[2];  // (x,y) position of FlappyBird
    private boolean alive;  // is FlappyBird alive or dead?
    private int[] img_dimensions = new int[2];

    public FlappyBird() {
        pos[0] = 0;
        pos[1] = 0;  // set pos to (0,0)
        alive = true;  // FlappyBird is alive
        img_dimensions = FlappyBird.scaleBirdInts(10);
    }


    // GETTERS, SETTERS---------------------------------------------------------

    public int[] getPos() {
        return pos;
    }

    public void setPos(int[] pos) {
        this.pos = pos;
    }


    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int[] getImg_dimensions() {
        return img_dimensions;
    }

    public void setImg_dimensions(int[] img_dimensions) {
        this.img_dimensions = img_dimensions;
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

    // MOVE

    public void moveUp() {
        int[] curr_pos = this.getPos();

        int[] new_pos = curr_pos;

        new_pos[1] -= 5;

        this.setPos(new_pos);
    }

    public void moveDown() {
        int[] curr_pos = this.getPos();

        int[] new_pos = curr_pos;

        new_pos[1] += 5;
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

    public void hasCollided(Obstacle obstacle) {
        if (this.insideObstacle(obstacle)) {
            this.setAlive(false);
        }
    }
}


