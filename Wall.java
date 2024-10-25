import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Wall extends Entity {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Rectangle[] walls = new Rectangle[] {
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

    public Rectangle[] getWalls() {
        return walls;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        for (Rectangle wall : walls) {
            g2d.fill(wall);
        }
    }

}
