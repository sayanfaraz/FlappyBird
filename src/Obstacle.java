import java.awt.*;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Obstacle {
    private int xpos; // leftmost pos of obstacle
    private boolean orientation;  // is obstacle coming from the top or the bottom?
    private int height;  // height
    private int width;  // width of obstacle
    private Color color; // color of obstacle

    public Obstacle(int xpos, boolean orientation, int height,
                    int width, int[] rgb) {
        if (rgb.length == 3) {
            this.xpos = xpos;
            this.orientation = orientation;
            this.height = height;
            this.width = width;
            this.color = new Color(rgb[0], rgb[1], rgb[2]);
        } else throw new IllegalArgumentException();
    }

    // GETTERS, SETTERS

    /**
     * Get x-position (pixel grid) of obstacle.
     *
     * @return {int}
     */
    public int getXpos() {
        return xpos;
    }

    /**
     * Set x-position (pixel grid) of obstacle.
     * @param xpos {int}
     */
    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    /**
     * Is obstacle oriented up (coming from the top of the window)? If false,
     * oriented down (coming from the bottom of the window).
     * @return {boolean}
     */
    public boolean isOrientatedUp() {
        return orientation;
    }

    /**
     * Set orientation of obstacle.
     * True: obstacle oriented up (coming from the top of the window).
     * False: obstacle oriented down (coming from the bottom of the window).
     * @param orientation {boolean}
     */
    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    /**
     * Returns height of obstacle in pixels.
     * @return {int}
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set height of obstacle in pixels.
     *
     * @param height {int}
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Get the width of obstacle (in pixels).
     * @return {int}
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set width of obtacle in pixels.
     * @param width {int}
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Get color of obstacle.
     * @return {Color} Color object
     */
    public Color getColor() {
        // Returning a NEW color object with same characteristics as
        // obstacle's color
        return new Color(color.getRed(), color.getGreen(),
                color.getBlue());
    }

    /**
     * Set color of obstacle.
     * @param input_color {Color} Color object
     */
    public void setColor(Color input_color) {
        this.color = new Color(input_color.getRed(), input_color.getGreen()
                , input_color.getBlue());
    }
}
