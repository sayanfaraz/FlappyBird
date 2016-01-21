import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.lang.*;
import java.io.IOException;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Game extends JPanel{

    private int game_play; // 0 for home screen, 1 for play, 2 for pause

    public Game () {
        super();
        game_play = 0; // init at home screen
    }

    // INITIALIZE UI
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

    private void homeScreen(Graphics graphics) {
        // Define BufferedImages
        BufferedImage home_screen_img = null;
        BufferedImage play_button_img = null;

        // Try to load the images; return exception if error
        try {
            home_screen_img = ImageIO.read(new File("src" + File.separator + "resources" + File.separator
                    + "Home Page Background.png"));
            play_button_img = ImageIO.read(new File("src" + File.separator + "resources" + File.separator
                    + "Home Page Play Button.png"));

            // Draw the background if loading successful
            graphics.drawImage(home_screen_img, 0, 0, null);

            // Create play button if loading successful
//            JButton play_button = new JButton(new ImageIcon(play_button_img));
//
//            play_button.setBorder(BorderFactory.createEmptyBorder());
//            play_button.setContentAreaFilled(false);

            graphics.drawImage(play_button_img, 512, 719, null);


        } catch (IOException e) {
            System.out.print("Home Page exception handled");
        }
    }

    // GRAPHICS ENGINE
    public void paintComponent(Graphics graphics) {

        switch (game_play) {
            case 0:
                homeScreen(graphics);
                break;
            case 1:
                gameEngine(graphics);
                break;
            case 2:
                gamePause();
        }
    }

    // GAME ENGINE-----------------------------------------------------------------------------------------------------

    private void gameEngine(Graphics graphics) {
        BufferedImage bird_img = null;
        try {
            bird_img = ImageIO.read(new File("src" + File.separator + "resources" + File.separator + "bird.png"));
        } catch (IOException e) {
            System.out.print("Bird exception handled");
        }

        graphics.drawImage(bird_img, 30, 40, null);
    }

    private void gamePause () {
        game_play = 0;
    }

    // MAIN------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        FlappyBird flappyBird = new FlappyBird();
        JFrame Game_frame = new JFrame();
        Game game = new Game();
        initUI(Game_frame, game);

        game.setBackground(Color.black);

        System.out.print(flappyBird.getPos()[0]);
    }
}
