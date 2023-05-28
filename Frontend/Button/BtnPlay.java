package Frontend.Button;

import Frontend.Size;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;

/**
 * A class to create the play button in the game, which is used to start the game of Nine Men's Morris.
 */

public class BtnPlay extends JButton {
    /**
     * Constructor.
     * Creates a play button in the game.
     */
    public BtnPlay(){
        this.setText("PLAY"); // set the text of button
        this.setFocusable(false); // get rid of the annoying box in button
        this.setFont(new Font("Inter",Font.BOLD, new Size(80, 80).getHeight()));
        this.setForeground(new Color(0x000000));
        this.setBackground(new Color(0xE59C54));
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
    }
}
