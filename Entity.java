import java.awt.image.BufferedImage;

/*
 * Creates the basic values for the entity's.
 */

public class Entity {
    public double x, y, angle, scale, newX, newY;
    public int speed, stopCloseMovement, centerX, centerY, shootAgain;
    public boolean alive;

    public BufferedImage characterImage;
    public BufferedImage dummyImage;

}
