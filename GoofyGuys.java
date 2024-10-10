import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

/**
 * Onthoud Frame.repaint
 * 
 * @author Tim van Kollenburg
 * @id 2110105
 * @author Lars Weeber
 * @id 2109506
 * 
 */

class Menu {
    void Instantiate() {
        JFrame frame = new JFrame();
        frame.setTitle("Goofy Guys");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.white);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GoofyGuys().GoofyGuys());
    }

    public static void main(String[] args) {
        new Menu().Instantiate();
    }
}

class GoofyGuys {
    public JLabel Tank() {
        JLabel character = new JLabel(new javax.swing.ImageIcon(getClass().getResource("character.png")));
        return character;
    }

    public JPanel GoofyGuys() {
        JPanel goofyGuys = new JPanel();
        // goofyGuys.setLayout();
        goofyGuys.setBackground(Color.white);
        goofyGuys.setSize(200, 200);
        goofyGuys.setLocation(200, 200);
        Border blackline = BorderFactory.createLineBorder(Color.black, 25);
        goofyGuys.setBorder(blackline);
        goofyGuys.setVisible(true);
        goofyGuys.add(new GoofyGuys().Tank());
        return goofyGuys;
    }
}