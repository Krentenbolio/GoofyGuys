import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    int FPS = 60;

    public Point2D mousePosition;
    public boolean dummyAlive = true;
    public boolean dummy1Alive = true;
    public boolean dummy2Alive = true;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    Refresh refresh;
    Player player = new Player(this, keyH);
    Dummy dummy = new Dummy(this);
    Dummy1 dummy1 = new Dummy1(this);
    Dummy2 dummy2 = new Dummy2(this);
    PlayerHitbox playerHitbox = new PlayerHitbox(player);
    DummyHitbox dummyHitbox = new DummyHitbox(dummy);
    Dummy1Hitbox dummy1Hitbox = new Dummy1Hitbox(dummy1);
    Dummy2Hitbox dummy2Hitbox = new Dummy2Hitbox(dummy2);
    Wall walls = new Wall();

    public GamePanel() {
        refresh = new Refresh(this);
        mousePosition = new Point2D.Double(0, 0);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        Color green = new Color(68, 158, 72);
        this.setBackground(green);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                player.setMousePosition(e.getX(), e.getY());
                mousePosition = e.getPoint();
            }
        });
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        refresh.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // System.out.println("The game loop is running");
        }

    }

    public void update() {
        player.update();

        Shape hitbox = playerHitbox.getTransformedCharacterBounds();
        if (hitbox != null) {
            for (Rectangle wall : walls.getWalls()) {
                if (hitbox.intersects(wall)) {
                    System.out.println("Collision detected! Stopping the game.");
                    JOptionPane.showMessageDialog(this, "You Lost!",
                         "Game Over", JOptionPane.PLAIN_MESSAGE); // adds a game over frame.
                    stopGame();
                    break;
                }
            }
        }
        Shape hitboxd = dummyHitbox.getTransformedCharacterBounds();
        if (hitboxd != null) {
            if (hitboxd.contains(mousePosition) && keyH.ePressed) {
                dummyAlive = false;
            }
        }
        Shape hitboxd1 = dummy1Hitbox.getTransformedCharacterBounds();
        if (hitboxd1 != null) {
            if (hitboxd1.contains(mousePosition) && keyH.ePressed) {
                dummy1Alive = false;
            }
        }
        Shape hitboxd2 = dummy2Hitbox.getTransformedCharacterBounds();
        if (hitboxd2 != null) {
            if (hitboxd2.contains(mousePosition) && keyH.ePressed) {
                dummy2Alive = false;
            }
        }
        if (dummyAlive == false && dummy1Alive == false && dummy2Alive == false) {
            JOptionPane.showMessageDialog(this, "You Win!!!",
                 "Winner", JOptionPane.PLAIN_MESSAGE);  // adds a win frame.
            stopGame();
        }
    }

    public void stopGame() {
        gameThread = null;
        refresh.stop();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        walls.draw(g2d);

        if (dummyAlive) {
            dummy.draw(g2d);
        }
        if (dummy1Alive) {
            dummy1.draw(g2d);
        }
        if (dummy2Alive) {
            dummy2.draw(g2d);
        }
        player.draw(g2d);


        /*
         * Shape hitbox = dummyHitbox.getTransformedCharacterBounds();
         * if (hitbox != null) {
         * g2d.setColor(Color.RED);
         * g2d.draw(hitbox);
         * }
         */
    }

}
