import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Dummy1 extends Entity {
    GamePanel gp;

    public Dummy1(GamePanel gp) {
        this.gp = gp;

        getDummyImages();
        setDefaultValues();
    }

    public void getDummyImages() {
        try {
            URL imageUrl = getClass().getResource("/dummy.png");
            dummyImage = ImageIO.read(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefaultValues() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        x = screenSize.width / 10;
        y = screenSize.height / 10 * 2;
        scale = 0.2 * (screenSize.width / 1920.000);
        angle = 3.141592;
    }

    public void draw(Graphics2D g2d) {
        if (dummyImage != null) {
            AffineTransform transform = new AffineTransform();

            // Apply transformations for movement and rotation
            transform.translate(x, y);
            transform.rotate(angle, dummyImage.getWidth() * scale / 2, dummyImage.getHeight() * scale / 2);
            transform.scale(scale, scale);

            // Draw the character image with transformations
            g2d.drawImage(dummyImage, transform, null);
        }
    }
}
