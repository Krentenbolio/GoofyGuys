import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Dummy1Hitbox extends Entity {

    Dummy1 dummy1;

    public Dummy1Hitbox(Dummy1 dummy1) {
        this.dummy1 = dummy1;
        setDefaultValues();
    }

    public void setDefaultValues() {
        if (dummy1.dummyImage != null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.width / 2 - (dummy1.dummyImage.getWidth() * dummy1.scale) / 2;
            y = screenSize.height / 2 - (dummy1.dummyImage.getHeight() * dummy1.scale) / 2;
            angle = 0;
            scale = dummy1.scale;
        }
    }

    public Shape getTransformedCharacterBounds() {
        if (dummy1.dummyImage != null) {
            double width = dummy1.dummyImage.getWidth() * dummy1.scale;
            double height = dummy1.dummyImage.getHeight() * dummy1.scale;

            Rectangle2D bounds = new Rectangle2D.Double(0, 0, width, height);

            AffineTransform transform = new AffineTransform();
            transform.translate(dummy1.x, dummy1.y);
            transform.rotate(dummy1.angle, width / 2.0, height / 2.0);
            return transform.createTransformedShape(bounds);
        }
        return null;
    }
}
