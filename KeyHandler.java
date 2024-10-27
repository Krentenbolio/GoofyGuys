import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Handles the shooting function by checking if e is pressed.
 */

public class KeyHandler implements KeyListener {
    boolean ePressed;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_E) {
            ePressed = true;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_E) {
            ePressed = false;
        }
    }
}
