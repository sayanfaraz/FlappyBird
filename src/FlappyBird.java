
/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class FlappyBird {

    // VARIABLES----------------------------------------------------------------
    private int[] prevPos = new int[2]; // prev pos of FlappyBird
    private int[] pos = new int[2];  // (x,y) position of FlappyBird
    private boolean alive;  // is FlappyBird alive or dead?
    private int[] imgDimensions = new int[2];
    private double fallTime; // time that FlappyBird has been falling

    public FlappyBird() {
        pos[0] = 50;
        pos[1] = 0;  // set pos to (0,0)
        imgDimensions = FlappyBird.scaleBirdInts(10);

        alive = true;  // FlappyBird is alive
        fallTime = 0;
    }


    // GETTERS, SETTERS---------------------------------------------------------

    /**
     * Get position (pixels) of flappy bird.
     *
     * @return {int[]} [x position, y position]
     */
    public int[] getPos() {
        int x = this.pos[0];
        int y = this.pos[1];

        return new int[]{x, y};
    }

    /**
     * Set position (pixels) of flappy bird.
     * @param pos {int[]} [x position, y position]
     */
    public void setPos(int[] pos) {
        setPrevPos(this.getPos());

        int new_pos_x = pos[0];
        int new_pos_y = pos[1];

        this.pos = new int[]{new_pos_x, new_pos_y};
    }

    /**
     * Get the last position of flappy bird.
     * @return {int[]} [x position, y position]
     */
    public int[] getPrevPos() {
        int x = this.prevPos[0];
        int y = this.prevPos[1];

        return new int[]{x, y};
    }

    /**
     * Save previous position of flappy bird.
     * @param prev_pos_in {int[]} [x position, y position]
     */
    public void setPrevPos(int[] prev_pos_in) {
        int prev_pos_x = prev_pos_in[0];
        int prev_pos_y = prev_pos_in[1];
        this.prevPos = new int[]{prev_pos_x, prev_pos_y};
    }

    /**
     * Is the flappy bird alive?
     * @return {boolean} true if alive; false if dead
     */
    public boolean isAlive() {
        return this.alive;
    }

    /**
     * Sets if flappy bird is alive or not.
     * @param alive {boolean} true if alive; false if dead
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * Get dimensions of flappy bird image.
     * @return {int[]} [width of image, height of image]
     */
    public int[] getImgDimensions() {

        int x = this.imgDimensions[0];
        int y = this.imgDimensions[1];

        return new int[]{x, y};
    }

    /**
     * Sets dimensions of flappy bird image.
     * @param imgDimensions {int[]} [width of image, height of image]
     */
    public void setImgDimensions(int[] imgDimensions) {

        int x = imgDimensions[0];
        int y = imgDimensions[1];

        this.imgDimensions = new int[]{x, y};
    }

    /**
     * Returns how long (arbitrary units of time) flappy bird has been falling
     * for.
     *
     * @return {double}
     */
    public double getFallTime() {

        return this.fallTime;
    }

    /**
     * Set how long (arbitrary units of time) flappy bird has been falling for.
     *
     * @param fallTime {double}
     */
    public void setFallTime(double fallTime) {

        this.fallTime = fallTime;
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

    /**
     * If called (upon a click), flappy bird moves up 50 pixels; falling time
     * reset to 0.
     */
    // CLICK
    public void clickBird() {
        moveUp(50);
        setFallTime(0);
    }

    // FALL

    /**
     * Makes flappy bird fall due to effect of gravity. (Moves flappy bird
     * down by pixels depending on falling time).
     */
    public void fall() {
        int fall_distance = (int) (fallTime * fallTime);
        moveDown(fall_distance);
        setFallTime((getFallTime()+0.1));
    }

    // KILL

    /**
     * Kills flapy bird.
     */
    public void kill() {
        this.setAlive(false);
    }

    /**
     * Makes flappy bird alive (again).
     */
    public void resurrect() {
        this.setAlive(true);
    }


    // RELATIVE POS

    /**
     * Returns if Flappy Bird is on obstacle's x positions.
     *
     * @param obstacle {Obstacle}
     * @return {boolean}
     */
    public boolean onXObstacle(Obstacle obstacle) {
        return (
                // CASE 1: Front of bird hits the obstacle
                this.pos[0] + this.getImgDimensions()[0] >= obstacle.getXpos()
                        &&
                        this.pos[0] <= obstacle.getXpos() + obstacle.getWidth()
                        ||
                        // CASE 2: Back of bird hits the obstacle
                        this.pos[0] + this.getImgDimensions()[0] >= obstacle.getXpos()
                                && this.pos[0] <= obstacle.getXpos() + obstacle.getWidth()
                // CASE 3: Obstacle is in between bird
        );
    }

    /**
     * Returns if Flappy Bird is on obstacle's x positions.
     * @param obstacle {Obstacle}
     * @param y_gap {int} Vertical spacing btn 2 obstacles in a pair
     * @return {boolean}
     */
    public boolean onYObstacle(Obstacle obstacle, int y_gap) {
        // If obstacle is oriented up
        if (obstacle.isOrientatedUp()) {
            return (
                    // CASE 1: Top of bird hits obstacle
                    this.pos[1] <= obstacle.getHeight()
                            ||
                            // CASE 2: Bottom of bird hits obstacle
                            this.pos[1] + this.getImgDimensions()[1] <= obstacle
                                    .getHeight()
            );
        }
        // If obstacle is oriented down
        else {
            return (
                    // CASE 1: Top of bird hits obstacle
                    this.pos[1] >= obstacle.getHeight() + y_gap
                            ||
                            // CASE 2: Bottom of bird hits obstacle
                            this.pos[1] + this.getImgDimensions()[1] >= obstacle.getHeight() + y_gap
                    // CASE 3: Obstacle is in between bird
            );

        }
    }

    /**
     * Returns if flappy bird is inside an obstacle.
     * @param obstacle {Obstacle}
     * @param y_gap {int} Vertical spacing btn 2 obstacles in a pair
     * @return {boolean}
     */
    public boolean insideObstacle(Obstacle obstacle, int y_gap) {
        if (this.onXObstacle(obstacle) && this.onYObstacle(obstacle, y_gap)) {
            System.out.println("x-hit: " + this.onXObstacle(obstacle));
            System.out.println("y-hit: " + this.onYObstacle(obstacle, y_gap));
        }
        return this.onXObstacle(obstacle) && this.onYObstacle(obstacle, y_gap);
    }

    // BASIC GAME FUNCTIONALITY

    // MOVE

    /**
     * Move flappy bird up by 5 pixels.
     */
    public void moveUp() {
        moveUp(5);
    }

    /**
     * Move flappy bird up by specified number of pixels.
     * @param amount {int} number of pixels
     */
    public void moveUp(int amount) {

        int[] new_pos = this.getPos();

        new_pos[1] -= amount;

        this.setPos(new_pos);
    }

    /**
     * Move flappy bird down by 5 pixels.
     */
    public void moveDown() {
        moveDown(5);
    }

    /**
     * Move flappy bird down by specified number of pixels.
     * @param amount {int} number of pixels
     */
    public void moveDown(int amount) {

        int[] new_pos = this.getPos();

        new_pos[1] += amount;

        this.setPos(new_pos);
    }

    /**
     * Kills flappy bird if it collides with an obstacle.
     * @param obstacle {Obstacle}
     * @param y_gap {int} Vertical spacing btn 2 obstacles in a pair
     */
    public void hasCollided(Obstacle obstacle, int y_gap) {
        if (this.insideObstacle(obstacle, y_gap)) {
            this.setAlive(false);
        }
    }
}


