package Frontend;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Font;
import java.awt.Color;

// Customized button for TUTORIAL
public class BtnTutorial extends JButton {
    public BtnTutorial(){
        this.setText("TUTORIAL");
        this.setFocusable(false); // get rid of the annoying box in button
        this.setFont(new Font("Inter", Font.BOLD, 80));
        this.setForeground(new Color(0x000000));
        this.setBackground(new Color(0xE59C54));
        this.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLACK));
    }
}
