import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/*
 * Creates the players hitbox.
 */

public class PlayerHitbox extends Entity {

    Player player;

    public PlayerHitbox(Player player) {
        this.player = player;
        setDefaultValues();
    }

    public void setDefaultValues() {
        if (player.characterImage != null) {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            x = screenSize.width / 2 - (player.characterImage.getWidth() * player.scale) / 2;
            y = screenSize.height / 2 - (player.characterImage.getHeight() * player.scale) / 2;
            angle = 0;
            scale = player.scale;
        }
    }

    public Shape getTransformedCharacterBounds() {
        if (player.characterImage != null) {
            double width = player.characterImage.getWidth() * player.scale;
            double height = player.characterImage.getHeight() * player.scale;

            Rectangle2D bounds = new Rectangle2D.Double(0, 0, width, height);

            AffineTransform transform = new AffineTransform();
            transform.translate(player.x, player.y);
            transform.rotate(player.angle, width / 2.0, height / 2.0);
            return transform.createTransformedShape(bounds);
        }
        return null;
    }
}
