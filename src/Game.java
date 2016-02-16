import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Game extends JPanel implements MouseListener {

    private int game_play; // 0 for home screen, 1 for play, 2 for pause
    private boolean entering_game_play; // new game?
    private FlappyBird flappyBird;

    public Game() {
        super();

        game_play = 0; // init at home screen
        entering_game_play = true; // on play press, will make new game
        flappyBird = new FlappyBird(); // init Flappy Bird
    }

    // INITIALIZE UI
    private void initUI(JFrame jframe, JPanel jpanel) {

        // Set JFrame Appearance
        jframe.setTitle("Flappy Bird"); // title
        jframe.setSize(1500, 1000); // size of window
        jframe.setLocationRelativeTo(null);
        jframe.setBackground(new Color(194, 217, 239)); // background colour;

        // Set JFrame Behaviour
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit on close

        // Add JPanel
        jframe.setContentPane(jpanel);
        jframe.setVisible(true);

        // Add Mouse Listener
        addMouseListener(this);
    }

    private void homeScreen(Graphics graphics) {
        // Define BufferedImages
        BufferedImage home_screen_img = null;
        BufferedImage play_button_img = null;

        // Try to load the images; return exception if error
        try {
            home_screen_img = ImageIO.read(new File("src" + File.separator +
                    "resources" + File.separator
                    + "Home Page Background.png"));
            play_button_img = ImageIO.read(new File("src" + File.separator +
                    "resources" + File.separator
                    + "Home Page Play Button.png"));

            // Draw the background, play button if loading successful
            graphics.drawImage(home_screen_img, 0, 0, null);

            graphics.drawImage(play_button_img, 512, 719, null);

        } catch (IOException e) {
            System.out.print("Home Page exception handled");
        }
    }

    // GRAPHICS ENGINE
    public void paintComponent(Graphics graphics) {

        // Home Screen, Play, Pause
        switch (game_play) {
            // Home Screen
            case 0:
                entering_game_play = true;
                homeScreen(graphics);
                break;
            case 1:
                // New game? Need a fresh canvas to draw on
                if (entering_game_play) {
                    graphics.setColor(new Color(194, 217, 239));
                    graphics.fillRect(0, 0, 1500, 1000);

                    // Game started, so set entering_game_play to false so that
                    // canvas isn't redrawn
                    entering_game_play = false;
                }
                gameEngine(graphics);
                break;
            // Pause
            case 2:
                gamePause();
        }

        System.out.println("x: " + flappyBird.getPos()[0]
                + " y: " + flappyBird.getPos()[1]
                + "    prevx: " + flappyBird.getPrev_pos()[0]
                + " prevy: " + flappyBird.getPrev_pos()[1]);
    }

    // GAME ENGINE--------------------------------------------------------------

    private void gameEngine(Graphics graphics) {
        // Draw bird
        resetBird(graphics);
        drawBird(graphics);

        // Make flappy bird fall
        flappyBird.fall();
        repaint();
    }

    private void gamePause() {
        game_play = 0;
    }

    // MOUSE LISTENERS----------------------------------------------------------
    public void mousePressed(MouseEvent e) {
        return;
    }

    public void mouseReleased(MouseEvent e) {
        return;
    }

    public void mouseEntered(MouseEvent e) {
        return;
    }

    public void mouseExited(MouseEvent e) {
        return;
    }

    public void mouseClicked(MouseEvent e) {
        // Get coords of mouse click
        int x = e.getX();
        int y = e.getY();

        // PLAY BUTTON
        // Is home screen?
        if (game_play == 0) {

            // Did mouse press Play button?
            if (x > 512 && x < 989 && y > 719 && y < 829) {
                // Game's starting :O
                game_play = 1;
                repaint();
            }
        }
        // Is it play screen?
        else if (game_play == 1) {
            clickBird();
        }
    }

    // HELPER FUNCTIONS---------------------------------------------------------

    private void clickBird() {
        flappyBird.clickBird();
        repaint();
    }

    private void drawBird(Graphics graphics) {
        BufferedImage bird_img = null;

        // Load bird img
        try {
            bird_img = ImageIO.read(new File("src" + File.separator +
                    "resources" + File.separator + "bird.png"));
        } catch (IOException e) {
            System.out.print("Bird exception handled");
        }

        // Draw bird img
        int[] scaled_parameters = flappyBird.getImg_dimensions();

        graphics.drawImage(bird_img, flappyBird.getPos()[0], flappyBird
                        .getPos()[1],
                scaled_parameters[0],
                scaled_parameters[1], null);
    }

    private void resetBird(Graphics graphics) {
        // Reset current bird
        graphics.setColor(new Color(194, 217, 239));
        graphics.fillRect(flappyBird.getPrev_pos()[0],
                flappyBird.getPrev_pos()[1],
                flappyBird.getImg_dimensions()[0],
                flappyBird.getImg_dimensions()[1]);
    }


    // MAIN---------------------------------------------------------------------
    public static void main(String[] args) {
        FlappyBird flappyBird = new FlappyBird();
        JFrame Game_frame = new JFrame();
        Game game = new Game();
        game.initUI(Game_frame, game);

        game.setBackground(Color.black);
    }
}
