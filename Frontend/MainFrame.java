package Frontend;

import javax.swing.*;
import java.awt.*;

// Customized main frame
public class MainFrame extends JFrame {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // get the screen size
    public MainFrame(){
        this.setTitle("NINE MEN'S MORRIS"); // set title of frame
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // set frame to full screen in default
        this.setSize(screenSize); // set the x-dimension and y-dimension of frame
        this.setVisible(true); // make the frame visible
        this.setResizable(false); // prevent frame from being resized
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // exit out of application

        ImageIcon pvp = new ImageIcon("Icons/pvp.png"); // create an image icon
        this.setIconImage(pvp.getImage()); //change icon of frame

    }

}
