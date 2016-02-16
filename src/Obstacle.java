import java.awt.*;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Obstacle {
    private int xpos; // leftmost pos of obstacle
    private boolean orientation;  // is obstacle coming from the top or the bottom?
    private int dist_from_origin;  // distance of obstacle to origin
    private int width;  // width of obstacle
    private Color color; // color of obstacle

    public Obstacle(int xpos, boolean orientation, int dist_from_origin,
                    int width, int[] rgb) {
        if (rgb.length == 3) {
            this.xpos = xpos;
            this.orientation = orientation;
            this.dist_from_origin = dist_from_origin;
            this.width = width;
            this.color = new Color(rgb[0], rgb[1], rgb[2]);
        } else throw new IllegalArgumentException();
    }

    // GETTERS, SETTERS

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public boolean isOrientatedUp() {
        return orientation;
    }

    public void setOrientation(boolean orientation) {
        this.orientation = orientation;
    }

    public int getDist_from_origin() {
        return dist_from_origin;
    }

    public void setDist_from_origin(int dist) {
        this.dist_from_origin = dist;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        Color ret_color = new Color(color.getRed(), color.getGreen(),
                color.getBlue());
        return ret_color;
    }

    public void setColor(Color input_color) {
        Color new_color = new Color(input_color.getRed(), input_color.getGreen()
                , input_color.getBlue());
        this.color = new_color;
    }
}
