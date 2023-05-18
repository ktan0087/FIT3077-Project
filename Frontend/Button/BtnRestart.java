package Frontend.Button;

import javax.swing.*;
import java.awt.*;

public class BtnRestart extends JButton {
    /**
     * Customized button for restart
     */

    // Constructor
    public BtnRestart(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconRestart = new ImageIcon(getClass().getResource("/Icons/restart.png"));
        this.setIcon(iconRestart);
        this.setBackground(new Color(0xE6B380));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

    }
}
