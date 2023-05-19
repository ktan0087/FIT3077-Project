package Backend.Token;

/**
 * A class to represent the token held by each player.
 * A player can have a maximum on 9 Token objects at a time.
 *
 * @see TokenColour
 *
 */
public class Token {

    /**
     * The colour of the token
     */
    private TokenColour tokenColour;

    /**
     * Constructor.
     *
     * @param tokenColour the colour of the token
     */
    public Token(TokenColour tokenColour) {
        setTokenColour(tokenColour);
    }

    /**
     * Getter to retrieve token colour of this object.
     *
     * @return the colour of the token
     */
    public TokenColour getTokenColour() {
        return tokenColour;
    }

    /**
     * Setter to set token colour of this object.
     *
     * @param tokenColour the colour of the token
     */
    public void setTokenColour(TokenColour tokenColour) {
        this.tokenColour = tokenColour;
    }
}
