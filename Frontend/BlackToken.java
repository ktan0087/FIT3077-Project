package Frontend;

import Backend.Token.TokenColour;

import java.awt.*;

public class BlackToken extends Token{
    /**
     * Generate a black token
     * @param coordinateX the x coordinate of the token
     * @param coordinateY the y coordinate of the token
     * @param iniBoard the initial board
     */

    // Constructor
    public BlackToken(int coordinateX, int coordinateY, InitialBoard iniBoard) {
        super(coordinateX, coordinateY, iniBoard);
        this.tokenColor = new Color(0x4D4D4D); // Black
        this.ownerTokenColour = TokenColour.PLAYER_2_BLACK;
    }
}
