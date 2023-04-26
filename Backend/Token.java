package Backend;

public class Token {

    private TokenColour tokenColour;

    //constructor
    public Token(TokenColour tokenColour) {
        setTokenColour(tokenColour);
    }

    //getter to retrieve token color
    public TokenColour getTokenColour() {
        return tokenColour;
    }

    //setter to set token color
    public void setTokenColour(TokenColour tokenColour) {
        this.tokenColour = tokenColour;
    }
}
