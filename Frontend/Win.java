package Frontend;

import javax.swing.*;
import java.awt.*;

public class Win extends JLabel {
    private WhoWin winner;
    public enum WhoWin {
        WHITEWIN,
        BLACKWIN
    }

    // Constructor
    public Win(WhoWin winner){
        this.winner = winner;
        this.setText("WIN");
        this.setOpaque(true); // make the background visible
        this.setBackground(new Color(0xF4E3D3));
        this.setFont(new Font("Inter", Font.PLAIN, 52));
        this.setForeground(new Color(0x000000));
        this.setWinner(this.winner);
        this.setHorizontalTextPosition(JLabel.RIGHT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setIconTextGap(45); // set the distance between text and icon
        this.setPreferredSize(new Dimension(300, 100));
        this.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
    }

    public void setWinner(WhoWin winner){
        if (this.winner == WhoWin.WHITEWIN){
            setIcon(new ImageIcon("Icons/white-token.png"));
        }
        else if (this.winner == WhoWin.BLACKWIN){
            setIcon(new ImageIcon("Icons/black-token.png"));
        }
    }
}
