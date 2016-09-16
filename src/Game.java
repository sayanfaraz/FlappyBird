import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */

//TODO: create obstacles coming from the bottom
// TODO: document all classes and functions

public class Game extends JPanel implements MouseListener {

    private int gamePlay; // 0 for home screen, 1 for play, 2 for pause
    private boolean enteringGamePlay; // new game?
    private Engine engine;
    private Date currDate, prevDate;

    // Window
    private int windowWidth;
    private int windowHeight;

    // CONSTRUCTORS-------------------------------------------------------------
    public Game(int width, int height) {
        super();

        // Set window dims
        windowHeight = height;
        windowWidth = width;

        // Set gameplay booleans
        gamePlay = 0; // init at home screen
        enteringGamePlay = true; // on play press, will make new game

        // Init engine
        engine = new Engine(windowWidth, windowHeight);
        currDate = new Date();
        prevDate = new Date();
    }

    public Game() {
        this(1500, 1000); // Default window: 1500 * 1000
    }

    // INITIALIZE UI
    private void initUI(JFrame jframe, JPanel jpanel) {

        // Set JFrame Appearance
        jframe.setTitle("Flappy Bird"); // title
        jframe.setSize(windowWidth, windowHeight); // size of window
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
        BufferedImage home_screen_img;
        BufferedImage play_button_img;

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
        switch (gamePlay) {
            // Home Screen
            case 0:
                enteringGamePlay = true;
                homeScreen(graphics);
                break;
            case 1:
                // New game? Need a fresh canvas to draw on
                if (enteringGamePlay) {
                    // Draw Canvas
                    graphics.setColor(new Color(194, 217, 239));
                    graphics.fillRect(0, 0, 1500, 1000);

                    // Game started, so set entering_game_play to false so that
                    //      canvas isn't redrawn
                    enteringGamePlay = false;
                }
                gameEngine(graphics);
                break;
            // Pause
            case 2:
                gamePause();
                break;
        }

//        System.out.println("x: " + engine.getFlappyBird().getPos()[0]
//                + " y: " + engine.getFlappyBird().getPos()[1]
//                + "    prevx: " + engine.getFlappyBird().getPrev_pos()[0]
//                + " prevy: " + engine.getFlappyBird().getPrev_pos()[1]);
    }

    // GAME ENGINE--------------------------------------------------------------

    private void gameEngine(Graphics graphics) {

        if(engine.getFlappyBird().isAlive()) {
            currDate = new Date();

            resetBird(graphics);

            // Move obstacles to the left every 0.5 secs
            if (currDate.getTime() - prevDate.getTime() > 500) {
                resetObstacles(graphics);
                engine.deleteOffScreenObstacles();
                engine.moveObstacleStackToLeft(50);


                prevDate = new Date();
            }

            drawObstacles(graphics);

            drawBird(graphics);
            engine.getFlappyBird().fall();

            engine.detectFlappyBirdCrashes();

            if(!engine.getFlappyBird().isAlive())
                gameReset();

            repaint();
        }
        else {
            gameReset();
        }
    }

    private void gamePause() {
        gamePlay = 0;
    }

    private void gameReset() {
        gamePlay = 0;
        enteringGamePlay = true;
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
        if (gamePlay == 0) {

            // Did mouse press Play button?
            if (x > 512 && x < 989 && y > 719 && y < 829) {
                // Game's starting :O
                gamePlay = 1;
                currDate = new Date();
                prevDate = new Date();
                repaint();
            }
        }
        // Is it play screen?
        else if (gamePlay == 1) {
            clickBird();
        }
    }

    // GRAPHICS HELPER FUNCTIONS------------------------------------------------

    // OBSTACLES
    private void drawObstacles(Graphics graphics) {
        // For each obstacle in obstacle-stack
        for (Obstacle obstacle : engine.getObstacleStack()) {
            // DRAW OBSTACLE
            // Set color
            graphics.setColor(obstacle.getColor());

            // Check orientation
            if (obstacle.isOrientatedUp()) {  // if oriented upwards
                // Draw obstacle from top
                graphics.fillRect(obstacle.getXpos(), 0, obstacle.getWidth(),
                        obstacle.getHeight());
            } else {
                // Draw obstacle from bottom
                int ypos = windowHeight - obstacle.getHeight();
                graphics.fillRect(obstacle.getXpos(), ypos,
                        obstacle.getWidth(), obstacle.getHeight());
            }
        }
    }

    private void resetObstacles(Graphics graphics) {
        // For each obstacle in obstacle stack
        for(Obstacle obstacle: engine.getObstacleStack()) {
            // REPAINT THAT OBSTACLE IN BLUE
            graphics.setColor(new Color(194, 217, 239));

            // Check orientation
            if (obstacle.isOrientatedUp()) {  // if oriented upwards
                // Draw obstacle from top
                graphics.fillRect(obstacle.getXpos(), 0, obstacle.getWidth(),
                        obstacle.getHeight());
            } else {
                // Draw obstacle from bottom
                int ypos = windowHeight - obstacle.getHeight();
                graphics.fillRect(obstacle.getXpos(), ypos,
                        obstacle.getWidth(), obstacle.getHeight());
            }
        }
    }

    // BIRD
    private void clickBird() {
        engine.getFlappyBird().clickBird();
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
        int[] scaled_parameters = engine.getFlappyBird().getImgDimensions();

        graphics.drawImage(bird_img, engine.getFlappyBird().getPos()[0],
                engine.getFlappyBird().getPos()[1],
                scaled_parameters[0],
                scaled_parameters[1], null);
    }

    private void resetBird(Graphics graphics) {
        // Reset current bird
        graphics.setColor(new Color(194, 217, 239));
        graphics.fillRect(engine.getFlappyBird().getPrevPos()[0],
                engine.getFlappyBird().getPrevPos()[1],
                engine.getFlappyBird().getImgDimensions()[0],
                engine.getFlappyBird().getImgDimensions()[1]);
    }


    // MAIN---------------------------------------------------------------------
    public static void main(String[] args) {
        JFrame Game_frame = new JFrame();
        Game game = new Game();
        game.initUI(Game_frame, game);

        game.setBackground(Color.black);
    }
}
