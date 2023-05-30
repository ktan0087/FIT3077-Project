package Frontend.Button;

import Frontend.IconProcessor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BtnNext extends JButton{
    public BtnNext() {
        ImageIcon nextImage = new ImageIcon(getClass().getResource("/Icons/next.png"));
        IconProcessor nextIcon = new IconProcessor(nextImage, 100, 100);
        ImageIcon nextResizedIcon = nextIcon.resizeIcon();
        this.setIcon(nextResizedIcon);
        this.setFocusable(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);

        // Make the visual effect when the next button is pressed
        ImageIcon pressedNextImage = new ImageIcon(getClass().getResource("/Icons/next-pressed.png"));
        IconProcessor pressedNextIcon = new IconProcessor(pressedNextImage, 100, 100);
        ImageIcon pressedNextResizedIcon = pressedNextIcon.resizeIcon();
        this.setPressedIcon(new ImageIcon(pressedNextResizedIcon.getImage()));
    }
}
