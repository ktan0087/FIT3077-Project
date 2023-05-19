package Frontend.Game;

import Backend.Token.TokenColour;

import java.awt.*;

/**
 * A class to represent a black token
 * @see Token
 * @see TokenColour
 */

public class BlackToken extends Token {
    /**
     * Constructor.
     * Creates a black token.
     *
     * @param coordinateX the x coordinate of the token
     * @param coordinateY the y coordinate of the token
     * @param iniBoard the initial board
     */
    public BlackToken(int coordinateX, int coordinateY, InitialBoard iniBoard) {
        super(coordinateX, coordinateY, iniBoard);
        this.tokenColor = new Color(0x4D4D4D); // Black
        this.ownerTokenColour = TokenColour.PLAYER_2_BLACK;
    }
}
