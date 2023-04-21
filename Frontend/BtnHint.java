package Frontend;

import javax.swing.*;
import java.awt.*;

// Customized button for hint
public class BtnHint extends JButton {
    public BtnHint(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconHint = new ImageIcon("Icons/hint.png");
        this.setIcon(iconHint);
        this.setBackground(new Color(0xE6B380));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }
}
