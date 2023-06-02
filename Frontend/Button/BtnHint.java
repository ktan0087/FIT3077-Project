package Frontend.Button;

import Frontend.IconProcessor;

import javax.swing.*;
import java.awt.*;

/**
 * A class to create the hint button in the game, which is used to give the player a hint.
 */

public class BtnHint extends JButton {
    /**
     * Constructor.
     * Creates a hint button in the game.
     */
    public BtnHint(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconHint = new ImageIcon(getClass().getResource("/Icons/hint.png"));

        // Resize the icon
        IconProcessor icon = new IconProcessor(iconHint, 60, 60);
        ImageIcon resizedIcon = icon.resizeIcon();
        this.setIcon(resizedIcon); // set the image of hint button

        this.setBackground(new Color(0xE6B380)); // set the background color of hint button
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK)); // set the border of hint button
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        if (this.isEnabled()) {
//            // Keep the original button's appearance regardless of the enabled state
//            Color backgroundColor = getBackground();
//            Color foregroundColor = getForeground();
//
//            super.paintComponent(g);
//
//            // Restore the original appearance
//            setBackground(backgroundColor);
//            setForeground(foregroundColor);
//        }
//    }

}
