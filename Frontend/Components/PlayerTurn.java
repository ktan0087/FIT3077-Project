package Frontend.Components;

import Frontend.IconProcessor;
import Frontend.Frame.Size;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the player turn label that will show at the bottom-middle section of the game board
 */

public class PlayerTurn extends JLabel {
    /**
     * The white token icon of the label
     */
    private ImageIcon whiteToken;

    /**
     * The black token icon of the label
     */
    private ImageIcon blackToken;

    /**
     * A boolean to represent whether the label is showing the white token icon or the black token icon
     */
    private Boolean isWhite;

    /**
     * A constructor to create a player turn label.
     * The label will show the white token icon by default.
     */
    public PlayerTurn(){
        this.whiteToken = new ImageIcon(getClass().getResource("/Icons/white-token.png"));
        this.blackToken = new ImageIcon(getClass().getResource("/Icons/black-token.png"));
        this.isWhite = false;

        this.setText("TURN"); // set the text of label
        this.setFont(new Font("Inter", Font.PLAIN, new Size(42, 42).getHeight())); // set the font of label
        this.setForeground(new Color(0x000000)); // set the color of label
        this.changeIcon();
        this.setHorizontalTextPosition(JLabel.LEFT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setIconTextGap(45); // set the distance between text and icon
        Size size = new Size(300, 80);
        this.setPreferredSize(new Dimension(size.getWidth(), size.getHeight()));
        this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
    }

    /**
     * A method to change the icon of the label.
     */
    public void changeIcon(){
        IconProcessor icon;
        ImageIcon resizedIcon;

        if (isWhite){
            // Resize the icon
            icon = new IconProcessor(this.blackToken, 68, 68);
            resizedIcon = icon.resizeIcon();

            this.setIcon(resizedIcon);
            isWhite = false;
        }
        else{
            // Resize the icon
            icon = new IconProcessor(this.whiteToken, 68, 68);
            resizedIcon = icon.resizeIcon();

            this.setIcon(resizedIcon);
            isWhite = true;
        }
    }

}
