package Frontend.Button;

import Frontend.Utils.IconProcessor;

import javax.swing.*;

/**
 * A class to create the next button in the tutorial, which is used to navigate to the next page.
 */
public class BtnNext extends JButton{
    /**
     * Constructor.
     * Creates a next button in the tutorial.
     */
    public BtnNext() {
        ImageIcon nextImage = new ImageIcon(getClass().getResource("/Icons/next.png")); // import the image of next button
        IconProcessor nextIcon = new IconProcessor(nextImage, 100, 100);
        ImageIcon nextResizedIcon = nextIcon.resizeIcon();
        this.setIcon(nextResizedIcon);
        this.setFocusable(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

        // Make the visual effect when the next button is pressed
        ImageIcon pressedNextImage = new ImageIcon(getClass().getResource("/Icons/next-pressed.png")); // import the image of pressed next button
        IconProcessor pressedNextIcon = new IconProcessor(pressedNextImage, 100, 100);
        ImageIcon pressedNextResizedIcon = pressedNextIcon.resizeIcon();
        this.setPressedIcon(new ImageIcon(pressedNextResizedIcon.getImage()));
    }
}
