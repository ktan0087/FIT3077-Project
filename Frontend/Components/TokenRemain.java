package Frontend.Components;

import Backend.Player;
import Backend.Token.TokenColour;
import Frontend.IconProcessor;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the token remainder label that will show at the middle section of the game board.
 * This label will show the remaining token of the player.
 *
 * The White Token Remainder Label will show on the left side of the game board.
 * The Black Token Remainder Label will show on the right side of the game board.
 */

public class TokenRemain extends JLabel {
    /**
     * The token icon of the label
     */
    private ImageIcon tokenIcon;

    /**
     * The amount of token
     */
    private int amountToken;

    /**
     * An enum to represent the token colour.
     */
    public enum TokenColour{
        BLACK,
        WHITE
    }

    /**
     * Token colour of the label
     */
    TokenColour tokenColour;

    /**
     * A constructor to create a token remainder label.
     *
     * @param tokenColour is the colour of the token remainder label, which includes: White and Black
     */
    public TokenRemain(TokenColour tokenColour){
        changeTokenColour(tokenColour); // set the token colour of the label
        this.amountToken = 9; // set the amount of token as a default of 9
        this.setText(String.valueOf(amountToken)); // set the text of label to the amount of token
        this.setBackground(new Color(0xE6B380));
        this.setFont(new Font("Inter", Font.PLAIN, 42));
        this.setForeground(new Color(0x000000));
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.setVerticalTextPosition(JLabel.BOTTOM);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setIconTextGap(45); // set the distance between text and icon
        this.setPreferredSize(new Dimension(125, 314));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }

    /**
     * Getter for the amount of token remainder.
     *
     * @return the amount of token remainder
     */
    public int getAmountToken() {
        return amountToken;
    }

    /**
     * This method is used to decrease the amount of token remainder.
     *
     * @return the amount of token remainder after decreasing
     */
    public int decreaseAmountToken(Player player) {
        this.amountToken = player.getTokensInHand();
        this.setText(Integer.valueOf(amountToken).toString());

        return this.amountToken; // return the amount of token
    }

    /**
     * This method is used to change the token colour of the label.
     *
     * @param tokenColour is the colour of the token remainder label, which includes: White and Black
     */
    public void changeTokenColour(TokenColour tokenColour){
        IconProcessor icon;
        ImageIcon resizedIcon;

        switch (tokenColour){
            case BLACK:
                this.tokenIcon = new ImageIcon(getClass().getResource(("/Icons/black-token.png"))); // import the image of black token

                // Resize the icon
                icon = new IconProcessor(this.tokenIcon, 68, 68);
                resizedIcon = icon.resizeIcon();

                this.setIcon(resizedIcon);
                break;
            case WHITE:
                this.tokenIcon = new ImageIcon(getClass().getResource("/Icons/white-token.png"));

                // Resize the icon
                icon = new IconProcessor(this.tokenIcon, 68, 68);
                resizedIcon = icon.resizeIcon();

                this.setIcon(resizedIcon);
                break;
        }
    }
}
