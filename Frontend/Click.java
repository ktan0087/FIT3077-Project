package Frontend;

import Frontend.Game.Tutorial;
import Frontend.Utils.IconProcessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to create the click icon.
 */
public class Click extends JLabel {

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

        /**
         * Create a timer to make the click icon blink.
         */
        Timer timer = new Timer(500, new ActionListener() {
            Boolean clickVisible = false;
            @Override
            public void actionPerformed(ActionEvent e) {
                clickVisible = !clickVisible; // change the visibility of the click icon
                repaint();
            }
        });
        timer.start(); // start the timer
    }
}
