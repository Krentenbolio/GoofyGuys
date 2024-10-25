import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class DummyHitbox extends Entity {

    Dummy dummy;

    public DummyHitbox(Dummy dummy) {
        this.dummy = dummy;
        setDefaultValues();
    }

    public void setDefaultValues() {
        if (dummy.dummyImage != null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.width / 2 - (dummy.dummyImage.getWidth() * dummy.scale) / 2;
            y = screenSize.height / 2 - (dummy.dummyImage.getHeight() * dummy.scale) / 2;
            angle = 0;
            scale = dummy.scale;
        }
    }

    public Shape getTransformedCharacterBounds() {
        if (dummy.dummyImage != null) {
            double width = dummy.dummyImage.getWidth() * dummy.scale;
            double height = dummy.dummyImage.getHeight() * dummy.scale;

            Rectangle2D bounds = new Rectangle2D.Double(0, 0, width, height);

            AffineTransform transform = new AffineTransform();
            transform.translate(dummy.x, dummy.y);
            transform.rotate(dummy.angle, width / 2.0, height / 2.0);
            return transform.createTransformedShape(bounds);
        }
        return null;
    }
}
