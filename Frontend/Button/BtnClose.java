package Frontend.Button;

import Frontend.IconProcessor;

import javax.swing.*;
import java.awt.*;

/**
 * A class to create the close button in the game, which is used to exit the game and navigate to the main page.
 */
public class BtnClose extends JButton {
    /**
     * Constructor.
     * Creates a close button in the game.
     */
    public BtnClose(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconClose = new ImageIcon(getClass().getResource("/Icons/close.png")); // import the image of close button

        // Resize the icon
        IconProcessor icon = new IconProcessor(iconClose, 60, 60);
        ImageIcon resizedIcon = icon.resizeIcon();
        this.setIcon(resizedIcon);

        this.setBackground(new Color(0xE6B380)); // set the background color of close button
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)); // set the border of close button
    }

}
