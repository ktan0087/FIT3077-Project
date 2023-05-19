package Frontend.Button;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;

/**
 * A class to create the tutorial button in the game, which is used to navigate to the tutorial of Nine Men's Morris.
 */

public class BtnTutorial extends JButton {
    /**
     * Constructor.
     * Creates a tutorial button in the game.
     */
    public BtnTutorial(){
        this.setText("TUTORIAL");
        this.setFocusable(false); // get rid of the annoying box in button
        this.setFont(new Font("Inter", Font.BOLD, 80));
        this.setForeground(new Color(0x000000));
        this.setBackground(new Color(0xE59C54));
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
    }
}
