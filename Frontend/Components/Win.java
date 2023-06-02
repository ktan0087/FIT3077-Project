package Frontend.Components;

import Frontend.Frame.Size;

import javax.swing.*;
import java.awt.*;

/**
 * A class to represent the win label that will show at the middle section of result page
 */
public class Win extends JLabel {
    /**
     * An enum to represent who wins the game
     */
    private WhoWin winner;

    /**
     * An enum to represent the winner of the game
     */
    public enum WhoWin {
        WHITEWIN,
        BLACKWIN,
        DRAW
    }

    /**
     * A constructor to create a win label.
     * The label will show the winner of the game.
     *
     * @param winner an enum to represent the winner of the game
     */
    public Win(WhoWin winner){
        this.winner = winner; // set the winner of the game
        this.setText("WIN"); // set the text of label to WIN
        this.setOpaque(true); // make the background visible
        this.setBackground(new Color(0xF4E3D3));
        this.setFont(new Font("Inter", Font.PLAIN, new Size(52, 52).getHeight()));
        this.setForeground(new Color(0x000000));
        this.setWinner(this.winner);
        this.setHorizontalTextPosition(JLabel.RIGHT);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setIconTextGap(new Size(45, 45).getHeight()); // set the distance between text and icon
        Size size = new Size(300, 100);
        this.setPreferredSize(new Dimension(size.getWidth(), size.getHeight()));
        this.setBorder(BorderFactory.createLineBorder(new Color(0xE27408), 5));
    }

    /**
     * A method to set the icon of the label.
     *
     * @param winner an enum to represent the winner of the game
     */
    public void setWinner(WhoWin winner){
        if (this.winner == WhoWin.WHITEWIN){ // if the winner is white
            setIcon(new ImageIcon(getClass().getResource("/Icons/white-token.png")));
        }
        else if (this.winner == WhoWin.BLACKWIN){ // if the winner is black
            setIcon(new ImageIcon(getClass().getResource("/Icons/black-token.png")));
        }
        else if (this.winner == WhoWin.DRAW){ // if the game is draw
            this.setText("DRAW");
        }
    }
}
