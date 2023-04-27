package Frontend;

import javax.swing.*;
import java.awt.*;

public class BtnClose extends JButton {
    /**
     * Customized button for exit the game
     */

    // Constructor
    public BtnClose(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconClose = new ImageIcon("Frontend/Icons/close.png"); // import the image of close button
        this.setIcon(iconClose); // set the image of close button
        this.setBackground(new Color(0xE6B380)); // set the background color of close button
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)); // set the border of close button
    }
}
