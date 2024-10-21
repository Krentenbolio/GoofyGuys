import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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

import javafx.scene.shape.Circle;

import java.awt.event.KeyEvent;

class Menu {
        void Instantiate() {
                JFrame frame = new JFrame();
                JPanel panel = new JPanel();
                Color green = new Color(68, 158, 72);
                panel.setBackground(green);
                // panel.setSize(200, 200);
                // panel.setLocation(200, 200);
                panel.setLayout(null);
                // Border blackline = BorderFactory.createLineBorder(Color.black, 25);
                // panel.setBorder(blackline);
                frame.setTitle("GoofyGuys");
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                Robot robot;
                try {
                        robot = new Robot();
                        robot.mouseMove(screenSize.width / 2, screenSize.height / 2);
                } catch (AWTException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
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

        private void addKeyListener(KeyAdapter keyAdapter) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addKeyListener'");
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

        public Target() {
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private BufferedImage characterImage;

        private double x = screenSize.width / 2;
        private double y = screenSize.height / 2;
        private double newX;
        private double newY;
        private double angle = 40;
        private double speed = screenSize.height / 100; // Player speed

        private int stopCloseMovement = 10;
        private double scale = 0.2 * (screenSize.width / 1920.000);
        private Timer refresh;

        public Character() {
                try {
                        URL imageUrl = getClass().getResource("/character.png");
                        characterImage = ImageIO.read(imageUrl);
                } catch (IOException e) {
                        e.printStackTrace();
                }

                refresh = new Timer(16, this);
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

        private void addEventListener() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'addEventListener'");
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
                if (checkCollision()) {
                        refresh.stop();
                }
        }

        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                for (Rectangle wall : walls) {
                        g2d.fill(wall);
                }

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

        private Rectangle[] walls = new Rectangle[] {
                        // all walls are ordered by location on the frame.
                        // to the right of each rectangle there are
                        // coordinates,
                        // sizes,
                        // each respective to the screenwidth and screenheight.

                        // border
                        new Rectangle(0, 0, screenSize.width, screenSize.height / 42), // (0, 0, 1, 1/42)
                        new Rectangle(0, 0, screenSize.width / 60, screenSize.height), // (0, 0, 1/60, 1)
                        new Rectangle(195 * screenSize.width / 200, 0,
                                        screenSize.width / 50, screenSize.height), // (195/200, 0, 1/50, 1)
                        new Rectangle(0, 93 * screenSize.height / 100,
                                        screenSize.width, screenSize.height / 25), // (0, 93/100, 1, 1/25)

                        // spawn area
                        new Rectangle(7 * screenSize.width / 16, screenSize.height / 3,
                                        screenSize.width / 8, screenSize.height / 60), // (7/16, 1/3, 1/8, 1/60)
                        new Rectangle(7 * screenSize.width / 16, screenSize.height / 6,
                                        screenSize.width / 80, 59 * screenSize.height / 160), // (7/16, 1/6, 1/80,
                                                                                              // 59/160)
                        new Rectangle(9 * screenSize.width / 16, screenSize.height / 3,
                                        screenSize.width / 80, screenSize.height / 5), // (9/16, 1/3, 1/80, 1/5)
                        new Rectangle(9 * screenSize.width / 20, 5 * screenSize.height / 8,
                                        screenSize.width / 9, screenSize.height / 60), // (9/20, 5/8, 1/9, 1/60)

                        // upper bound
                        new Rectangle(screenSize.width / 3, screenSize.height / 6, 5 * screenSize.width / 12,
                                        screenSize.height / 60), // (1/3, 1/6, 1/3, 1/60)
                        new Rectangle(3 * screenSize.width / 8, screenSize.height / 12,
                                        screenSize.width / 3, screenSize.height / 90), // (3/8, 1/12, 1/3, 1/90)

                        // left bound
                        new Rectangle(screenSize.width / 3, screenSize.height / 4,
                                        screenSize.width / 80, 7 * screenSize.height / 12), // (1/3, 1/4, 1/80, 7/12)
                        new Rectangle(0, screenSize.height / 2, screenSize.width / 7,
                                        screenSize.height / 60), // (0, 1/2, 1/7, 1/60)
                        new Rectangle(screenSize.width / 5, screenSize.height / 2,
                                        screenSize.width / 7, screenSize.height / 60), // (1/5, 1/2, 1/7, 1/60)
                        new Rectangle(screenSize.width / 7, screenSize.height / 3,
                                        screenSize.width / 150, screenSize.height / 3), // (1/7, 1/3, 1/150, 1/3)
                        new Rectangle(screenSize.width / 5, screenSize.height / 3,
                                        screenSize.width / 150, screenSize.height / 3), // (1/5, 1/3, 1/150, 1/3)

                        // left upper bound
                        new Rectangle(screenSize.width / 12, screenSize.height / 7,
                                        screenSize.width / 5, screenSize.height / 90), // (1/12, 1/7, 1/5, 1/90)
                        new Rectangle(17 * screenSize.width / 60, screenSize.height / 7,
                                        screenSize.width / 150, screenSize.height / 4), // (17/60, 1/7, 1/150, 1/4)

                        // left lower bound
                        new Rectangle(screenSize.width / 14, 3 * screenSize.height / 5,
                                        screenSize.width / 150, screenSize.height / 4), // (1/14, 3/5, 1/150, 1/4)
                        new Rectangle(19 * screenSize.width / 70, 3 * screenSize.height / 5,
                                        screenSize.width / 150, screenSize.height / 4), // (19/70, 3/5, 1/150, 1/4)

                        // lower bound
                        new Rectangle(screenSize.width / 3, 5 * screenSize.height / 6,
                                        screenSize.width / 2, screenSize.height / 70), // (1/3, 5/6, 1/2, 1/70)
                        new Rectangle(screenSize.width / 2, 5 * screenSize.height / 8,
                                        screenSize.width / 100, screenSize.height / 8), // (1/2, 5/8, 1/100, 1/8)

                        // right middle bound
                        new Rectangle(5 * screenSize.width / 8, screenSize.height / 3,
                                        screenSize.width / 6, screenSize.height / 90), // (5/8, 1/3, 1/6, 1/90)
                        new Rectangle(5 * screenSize.width / 8, screenSize.height / 3,
                                        screenSize.width / 100, screenSize.height / 3), // (5/8, 1/3, 1/100, 1/3)
                        new Rectangle(5 * screenSize.width / 8, 2 * screenSize.height / 3,
                                        screenSize.width / 12, screenSize.height / 90), // (5/8, 2/3, 1/12, 1/90)
                        new Rectangle(17 * screenSize.width / 24, 7 * screenSize.height / 16,
                                        screenSize.width / 100, 2 * screenSize.height / 5), // (17/24, 7/16, 1/100, 2/5)

                        // right bound
                        new Rectangle(5 * screenSize.width / 6, screenSize.height / 6,
                                        screenSize.width / 100, 9 * screenSize.height / 16), // (5/6, 1/6, 1/100, 9/16)
                        new Rectangle(5 * screenSize.width / 6, screenSize.height / 4,
                                        screenSize.width / 12, screenSize.height / 80), // (5/6, 1/4, 1/12, 1/80)
                        new Rectangle(5 * screenSize.width / 6, 5 * screenSize.height / 8,
                                        screenSize.width / 12, screenSize.height / 80), // (5/6, 5/8, 1/12, 1/80)
                        new Rectangle(11 * screenSize.width / 12, 7 * screenSize.height / 16,
                                        screenSize.width / 12, screenSize.height / 80), // (11/12, 7/16, 1/12, 1/80)

        };

        private boolean checkCollision() {
                Shape characterBounds = getTransformedCharacterBounds();

                for (Rectangle wall : walls) {
                        if (characterBounds.intersects(wall.getBounds2D())) {
                                return true;
                        }
                }

                return false;
        }

        private Shape getTransformedCharacterBounds() {
                double width = characterImage.getWidth() * scale;
                double height = characterImage.getHeight() * scale;
                int widthInt = (int) width;
                int heightInt = (int) height;

                Rectangle bounds = new Rectangle(0, 0, widthInt, heightInt);
                AffineTransform transform = new AffineTransform();
                transform.translate(x, y);
                transform.rotate(angle, width / 2.0, height / 2.0);

                return transform.createTransformedShape(bounds);
        }
}

class Projectile extends JPanel implements ActionListener {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        private BufferedImage characterImage;

        private double x = screenSize.width / 2;
        private double y = screenSize.height / 2;
        private double newX;
        private double newY;
        private double angle = 40;
        private double speed = screenSize.height / 50; // Player speed

        private int stopCloseMovement = 10;
        private Timer refresh;

        public Projectile() {
                refresh = new Timer(16, this);
                refresh.start();

                addMouseMotionListener(new MouseMotionAdapter() {
                        @Override
                        public void mouseMoved(MouseEvent e) {
                                newX = e.getX();
                                newY = e.getY();
                        }
                });
        }

        private void moveTowardsTarget() {
                int centerX = (int) (characterImage.getWidth() / 2);
                int centerY = (int) (characterImage.getHeight() / 2);
                double dx = newX - (x + centerX);
                double dy = newY - (y + centerY);
                double distance = Math.sqrt(dx * dx + dy * dy);
                if (distance > stopCloseMovement) {
                        angle = Math.atan2(dy, dx);
                        x += speed * Math.cos(angle);
                        y += speed * Math.sin(angle);
                }
                // if (checkCollision()) {
                // refresh.stop();
                // }
        }

        private Circle bullet = new Circle(x, y, 10);

        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;

                AffineTransform transform = new AffineTransform();
                g2d.fill((Shape) bullet);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
                moveTowardsTarget();
                repaint();
        }
}