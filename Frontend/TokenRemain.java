package Frontend;

import javax.swing.*;
import java.awt.*;

public class TokenRemain extends JLabel {
    /**
     * This class is used to show the remaining token
     */
    private ImageIcon tokenIcon;
    private int amountToken; // default amount of token
    public enum TokenColour{
        BLACK,
        WHITE
    }
    TokenColour tokenColour;

    // Constructor
    public TokenRemain(TokenColour tokenColour){
        changeTokenColour(tokenColour);
        this.amountToken = 9;
        this.setText(String.valueOf(amountToken));
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

    public int getAmountToken() {
        return amountToken;
    }

    /**
     * This method is used to decrease the amount of token remainder
     */
    public int decreaseAmountToken() {
        if (this.amountToken > 0){
            this.amountToken--;
            this.setText(Integer.valueOf(amountToken).toString());
        }
        return this.amountToken;
    }

    public void changeTokenColour(TokenColour tokenColour){
        switch (tokenColour){
            case BLACK:
                this.tokenIcon = new ImageIcon(getClass().getResource(("/Icons/black-token.png"))); // import the image of black token
                this.setIcon(tokenIcon);
                break;
            case WHITE:
                this.tokenIcon = new ImageIcon(getClass().getResource("/Icons/white-token.png"));
                this.setIcon(tokenIcon);
                break;
        }
    }
}
