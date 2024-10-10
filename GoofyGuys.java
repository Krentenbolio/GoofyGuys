import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Onthoud Frame.repaint
 * 
 * @author Tim van Kollenburg
 * @id
 * @author Lars Weeber
 * @id 2109506
 * 
 */

public class GoofyGuys {
    void Instantiate() {
        JFrame frame = new JFrame();
        frame.setTitle("Goofy Guys");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setResizable(false);
        // frame.setUndecorated(true); Haalt maximize en minimize weg en haalt the close
        // weg
        frame.getContentPane().setBackground(Color.white);
        // frame.pack(); past het aan zodat alles op het scherm past maar als je niks
        // hebt maakt het het scherm praktisch gezien onzichtbaar.
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        new GoofyGuys().Instantiate();
    }
}