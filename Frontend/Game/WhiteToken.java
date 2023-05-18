package Frontend.Game;

import Backend.Token.TokenColour;

import java.awt.*;

public class WhiteToken extends Token {
    /**
     * This class is used to create the white token
     * @param coordinateX is the layer of the token (e.g. TOP_LEFT has the layer of 1)
     * @param coordinateY is the position of the token (e.g. TOP_LEFT has the position of 1)
     * @param initialBoard is the initial board
     */

    // Constructor
    public WhiteToken(int coordinateX, int coordinateY, InitialBoard initialBoard) {
        super(coordinateX, coordinateY, initialBoard);
        this.tokenColor = new Color(0xF5F5DC);
        this.ownerTokenColour = TokenColour.PLAYER_1_WHITE;
    }
}
