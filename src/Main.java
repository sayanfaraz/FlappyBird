import javax.swing.*;
import java.awt.*;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Main extends JFrame {

    public Main() {

        initUI();
    }

    // Initialize UI
    private void initUI() {

        setTitle("Flappy Bird"); // title
        setSize(1500, 1000); // size of window
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(194,217,239)); // background colour

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        FlappyBird flappyBird = new FlappyBird();

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Main mainWindow = new Main();
                mainWindow.setVisible(true);
            }
        });

        System.out.print(flappyBird.getPos()[0]);
    }
}
