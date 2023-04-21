package Frontend;

import javax.swing.*;
import java.awt.*;

// Customized button for restart
public class BtnRestart extends JButton {
    public BtnRestart(){
        this.setFocusable(false); // get rid of the annoying box in button
        ImageIcon iconRestart = new ImageIcon("Frontend/Icons/restart.png");
        this.setIcon(iconRestart);
        this.setBackground(new Color(0xE6B380));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));

    }
}
