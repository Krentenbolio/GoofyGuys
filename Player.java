import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.util.ArrayList;

/*
 * Creates the Player.
 */

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    private double mouseX, mouseY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        // Load images before setting default values
        getPlayerImages();
        setDefaultValues();
    }

    public void getPlayerImages() {
        try {
            URL imageUrl = getClass().getResource("/character.png");
            characterImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Ensure characterImage is not null before using it
        if (characterImage != null) {
            x = screenSize.width / 2 - (characterImage.getWidth() * scale) / 2;
            y = screenSize.height / 2 - (characterImage.getHeight() * scale) / 2;
        }

        speed = screenSize.height / 150;
        angle = 0;
        scale = 0.2 * (screenSize.width / 1920.000);
        stopCloseMovement = 10;
    }

    // Set the mouse position
    public void setMousePosition(double mouseX, double mouseY) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    // Move the player towards the mouse
    public void update() {
        if (characterImage != null) {
            centerX = (int) (characterImage.getWidth() * scale / 2);
            centerY = (int) (characterImage.getHeight() * scale / 2);
            double dx = mouseX - (x + centerX);
            double dy = mouseY - (y + centerY);
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance > stopCloseMovement) {
                angle = Math.atan2(dy, dx);
                x += speed * Math.cos(angle);
                y += speed * Math.sin(angle);
            }
        }
    }

    // Draw the player at its current position
    public void draw(Graphics2D g2d) {
        if (characterImage != null) {
            AffineTransform transform = new AffineTransform();

            // Apply transformations for movement and rotation
            transform.translate(x, y);
            transform.rotate(angle, characterImage.getWidth() * scale / 2, characterImage.getHeight() * scale / 2);
            transform.scale(scale, scale);

            // Draw the character image with transformations
            g2d.drawImage(characterImage, transform, null);
        }
    }
}
