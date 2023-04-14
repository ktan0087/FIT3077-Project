public class Token {

    private TokenColour tokenColour;

public Token(TokenColour tokenColour) {
        setTokenColour(tokenColour);
    }

    public TokenColour getTokenColour() {
        return tokenColour;
    }

    public void setTokenColour(TokenColour tokenColour) {
        this.tokenColour = tokenColour;
    }
}
