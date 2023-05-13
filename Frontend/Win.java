package Frontend;

import javax.swing.*;
import java.awt.*;

public class Win extends JLabel {
    private ImageIcon tokenIcon;

    // Constructor
    public Win(){
        this.setText("WIN");
        this.setOpaque(true); // make the background visible
        this.setBackground(new Color(0xF4E3D3));
        this.setFont(new Font("Inter", Font.PLAIN, 52));
        this.setForeground(new Color(0x000000));
        this.setIcon(tokenIcon);
        this.setHorizontalTextPosition(JLabel.RIGHT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setIconTextGap(45); // set the distance between text and icon
        this.setPreferredSize(new Dimension(300, 100));
        this.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
    }
}
