import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.lang.*;
import java.io.IOException;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Game extends JPanel{

    public Game () {
        super();
    }

    // Initialize UI
    private static void initUI(JFrame jframe, JPanel jpanel) {

        // Set JFrame Appearance
        jframe.setTitle("Flappy Bird"); // title
        jframe.setSize(1500, 1000); // size of window
        jframe.setLocationRelativeTo(null);
        jframe.setBackground(new Color(194,217,239)); // background colour;

        // Set JFrame Behaviour
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close

        // Add JPanel
        jframe.setContentPane(jpanel);
        jframe.setVisible(true);
    }

    public void paintComponent(Graphics graphics) {

        BufferedImage bird_img = null;
        try {
            bird_img = ImageIO.read(new File("src" + File.separator + "resources" + File.separator + "bird.png"));
        } catch (IOException e) {
            System.out.print("Bird exception handled");
        }

        graphics.drawImage(bird_img, 30, 40, null);
    }

    public static void main(String[] args) {
        FlappyBird flappyBird = new FlappyBird();
        JFrame Game_frame = new JFrame();
        Game game = new Game();
        initUI(Game_frame, game);

        game.setBackground(Color.black);

        System.out.print(flappyBird.getPos()[0]);
    }
}
