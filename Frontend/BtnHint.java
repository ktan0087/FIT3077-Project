package Frontend;

import javax.swing.*;
import java.awt.*;

public class BtnHint extends JButton {
    /**
     * Customized button for hint
     */

    // Constructor
    public BtnHint(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconHint = new ImageIcon(getClass().getResource("/Icons/hint.png"));
        this.setIcon(iconHint);
        this.setBackground(new Color(0xE6B380));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }
}
