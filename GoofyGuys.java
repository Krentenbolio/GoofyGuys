import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

class Menu {
    void Instantiate() {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        Color green = new Color(68, 158, 72);
        panel.setBackground(green);
        panel.setSize(200, 200);
        panel.setLocation(200, 200);
        panel.setLayout(null);
        Border blackline = BorderFactory.createLineBorder(Color.black, 25);
        panel.setBorder(blackline);
        frame.setTitle("GoofyGuys");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Target target = new Target();
        // panel.add(target);
        Character character = new Character();
        character.setBounds(0, 0, screenSize.width, screenSize.height);
        panel.add(character);
        Target target = new Target();
        target.setBounds(0, 0, screenSize.width, screenSize.height);
        panel.add(target);
        frame.add(panel);

        frame.setVisible(true);
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        new Menu().Instantiate();
    }
}

class Target extends JLabel {
    private BufferedImage targetImage;
    private double x = 250;
    private double y = 250;
    private double angle = 0;
    private double scale = 0.2;

    /* */ public Target() {
        try {
            URL targetImageUrl = getClass().getResource("dummy.png");
            if (targetImageUrl == null) {
                System.out.println("Godverdomme");
                System.exit(0);
            } else {
                targetImage = ImageIO.read(targetImageUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setBackground(Color.white);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform transform = new AffineTransform();

        transform.translate(x, y);
        transform.rotate(angle, targetImage.getWidth() * scale
                / 2, targetImage.getHeight() * scale / 2);

        // Apply scaling
        transform.scale(scale, scale);
        g2d.drawImage(targetImage, transform, this);
    }
}

class Character extends JLabel implements ActionListener {
    private BufferedImage characterImage;
    private double x = 250;
    private double y = 250;
    private double newX;
    private double newY;
    private double angle = 0;
    private double speed = 10;
    private int stopCloseMovement = 10;
    private double scale = 0.2;

    public Character() {
        try {
            URL imageUrl = getClass().getResource("/character.png");
            characterImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Timer refresh = new Timer(16, this);
        refresh.start();

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                newX = e.getX();
                newY = e.getY();
            }
        });

        setBackground(Color.white);
        setFocusable(true);
    }

    private void moveTowardsTarget() {
        int centerX = (int) (characterImage.getWidth() * scale / 2);
        int centerY = (int) (characterImage.getHeight() * scale / 2);
        double dx = newX - (x + centerX);
        double dy = newY - (y + centerY);
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > stopCloseMovement) {
            angle = Math.atan2(dy, dx);
            x += speed * Math.cos(angle);
            y += speed * Math.sin(angle);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        AffineTransform transform = new AffineTransform();

        transform.translate(x, y);
        transform.rotate(angle, characterImage.getWidth() * scale
                / 2, characterImage.getHeight() * scale / 2);

        // Apply scaling
        transform.scale(scale, scale);
        g2d.drawImage(characterImage, transform, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        moveTowardsTarget();
        repaint();
    }
}
