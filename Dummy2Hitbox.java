import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * Creates the dummy's hitbox.
 */

public class Dummy2Hitbox extends Entity {

    Dummy2 dummy2;

    public Dummy2Hitbox(Dummy2 dummy2) {
        this.dummy2 = dummy2;
        setDefaultValues();
    }

    public void setDefaultValues() {
        if (dummy2.dummyImage != null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.width / 2 - (dummy2.dummyImage.getWidth() * dummy2.scale) / 2;
            y = screenSize.height / 2 - (dummy2.dummyImage.getHeight() * dummy2.scale) / 2;
            angle = 0;
            scale = dummy2.scale;
        }
    }

    public Shape getTransformedCharacterBounds() {
        if (dummy2.dummyImage != null) {
            double width = dummy2.dummyImage.getWidth() * dummy2.scale;
            double height = dummy2.dummyImage.getHeight() * dummy2.scale;

            Rectangle2D bounds = new Rectangle2D.Double(0, 0, width, height);

            AffineTransform transform = new AffineTransform();
            transform.translate(dummy2.x, dummy2.y);
            transform.rotate(dummy2.angle, width / 2.0, height / 2.0);
            return transform.createTransformedShape(bounds);
        }
        return null;
    }
}
