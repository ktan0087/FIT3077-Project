package Frontend;

import javax.swing.*;
import java.awt.*;

public class PlayerTurn extends JLabel {
    /**
     * Customized label for Player Turn
     */
    ImageIcon whiteToken = new ImageIcon("Frontend/Icons/white-token.png");
    ImageIcon blackToken = new ImageIcon("Frontend/Icons/black-token.png");
    Boolean isWhite = true;

    // Constructor
    public PlayerTurn(){
        this.setText("TURN"); // set the text of label
        this.setFont(new Font("Inter", Font.PLAIN, 42)); // set the font of label
        this.setForeground(new Color(0x000000)); // set the color of label
        this.setIcon(whiteToken); // set the icon of label
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
