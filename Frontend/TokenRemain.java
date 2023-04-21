package Frontend;

import javax.swing.*;
import java.awt.*;

public abstract class TokenRemain extends JLabel {
    ImageIcon tokenIcon;

    public TokenRemain(){
        this.setText("9");
        this.setBackground(new Color(0xE6B380));
        this.setFont(new Font("Inter", Font.PLAIN, 42));
        this.setForeground(new Color(0x000000));
        this.setIcon(tokenIcon);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setIconTextGap(45); // set the distance between text and icon
        this.setPreferredSize(new Dimension(125, 314));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }
}
