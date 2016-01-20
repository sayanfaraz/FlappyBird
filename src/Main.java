import javax.swing.*;
import java.awt.*;

/**
 * Created by Sayan Faraz on 2016-01-11.
 */
public class Main extends JFrame {

    public Main() {

        initUI();
    }

    private void initUI() {

        setTitle("Flappy Bird");
        setSize(1500, 1000);
        setLocationRelativeTo(null);
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
