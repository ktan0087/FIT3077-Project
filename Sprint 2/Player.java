
public class Player {

    private String name;
    private Token token;
    private int tokensOnBoard;
    private int tokensInHand;
    private boolean canFly;

    public Player(String name, Token token) {
        this.name = name;
        this.token = token;
        this.tokensInHand = Game.MAX_TOKENS_PLAYER;
        this.tokensOnBoard = Game.MAX_TOKENS_PLAYER-getTokensInHand();
        this.canFly = false;
    }
    public String getName() {
        return name;
    }

    public int getTokensOnBoard() {
        return tokensOnBoard;
    }

    public Token getToken() {
        return token;
    }

    public int getTokensInHand() {
        return tokensInHand;
    }

    public boolean canPlayerFly() {
        return canFly;
    }

    // This function is used when a player's token is placed on the board.
    public int placeTokenOnBoard() {
        if (tokensInHand > 0) {
            tokensInHand--;
            tokensOnBoard++;
        }
        return tokensInHand;
    }


    // This function is used when a player's token is removed by another player
    public int losePieceOnBoard() {
        if (tokensOnBoard > 0) {
            tokensOnBoard--;
            if (tokensOnBoard == 3 && getTokensInHand()==0) {
                canFly = true;
            }
        }
        return tokensOnBoard;
    }
}
