package Frontend.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the main frame of the game
 */

public class MainFrame extends JFrame {
    /**
     * Constructor.
     * Creates the main frame of the application.
     */
    public MainFrame(){
        this.setTitle("NINE MEN'S MORRIS"); // set title of frame
        this.setSize(new Dimension(1280, 720)); // set the x-dimension and y-dimension of frame
        this.setVisible(true); // make the frame visible
        this.setResizable(false); // prevent frame from being resized
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit out of application

        ImageIcon pvp = new ImageIcon(getClass().getResource("/Icons/pvp.png")); // create an image icon
        this.setIconImage(pvp.getImage()); //change icon of frame
    }

}
