package Frontend;

import javax.swing.*;
import java.awt.*;

// Customized label for Player Turn
public class PlayerTurn extends JLabel {
    ImageIcon whiteToken = new ImageIcon("Frontend/Icons/white-token.png");
    ImageIcon blackToken = new ImageIcon("Frontend/Icons/black-token.png");
    Boolean isWhite = true;

    public PlayerTurn(){
        this.setText("TURN");
        this.setFont(new Font("Inter", Font.PLAIN, 42));
        this.setForeground(new Color(0x000000));
        this.setIcon(whiteToken);
        this.setHorizontalTextPosition(JLabel.LEFT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setIconTextGap(45); // set the distance between text and icon
        this.setPreferredSize(new Dimension(300, 80));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }

    public void changeIcon(){
        if (isWhite){
            this.setIcon(blackToken);
            isWhite = false;
        }
        else{
            this.setIcon(whiteToken);
            isWhite = true;
        }
    }

}
