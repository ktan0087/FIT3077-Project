package Frontend.Components;

import Frontend.Game.Tutorial;
import Frontend.Utils.IconProcessor;

import javax.swing.*;

/**
 * This class is used to create the click icon.
 */
public class Click extends JLabel {
    private Boolean clickVisible;

    /**
     * Constructor.
     * Creates a new click icon.
     */
    public Click(){
        this.setOpaque(false); // set the background transparent

        // resize the icon
        ImageIcon click = new ImageIcon(Tutorial.class.getResource("/Icons/click.png"));
        IconProcessor clickIcon = new IconProcessor(click, 48, 48);
        ImageIcon resizedIcon = clickIcon.resizeIcon();

        this.setIcon(resizedIcon);
    }
}
