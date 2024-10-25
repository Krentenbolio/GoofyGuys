import java.awt.*;
import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Robot robot;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            robot = new Robot();
            robot.mouseMove(screenSize.width / 2, screenSize.height / 2);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        frame.setTitle("GoofyGuys");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width, screenSize.height);

        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        gamePanel.startGameThread();
    }
}