package Backend;

import java.util.List;

/**
 * A class to represent a player in the game. There is 2 player objects
 * in a game on Nine Men's Morris.
 *
 * @see Game
 * @see ActionList
 */
public class Player {
    /**
     * The name of the player
     */
    private String name;
    /**
     * The colour of the token held by the player
     */
    private TokenColour tokenColour;
    /**
     * The number of tokens for this player that is on the board
     */
    private int tokensOnBoard;
    /**
     * The number of tokens in hand of this player
     */
    private int tokensInHand;
    /**
     * The status of the player whether it can fly or not
     */
    private boolean canFly;

    /**
     * The list of allowable actions for this player
     */
    private ActionList allowableActions = new ActionList();

    /**
     * Constructor.
     *
     * @param name the name of the player
     * @param tokenColour the colour of the token held by the player
     */
    public Player(String name, TokenColour tokenColour) {
        this.name = name;
        this.tokenColour = tokenColour;
        this.tokensInHand = Game.MAX_TOKENS_PLAYER;
        this.tokensOnBoard = Game.MAX_TOKENS_PLAYER-getTokensInHand();
        this.addAllowableAction(AllActions.PLACE_TOKEN);
        this.canFly = false;
    }

    /**
     * A function to check if the player is allowed extra moves or certain moves are
     * no longer valid.
     * A player can no longer place tokens on the board if he has no tokens in hand left.
     * A player can now move his tokens once he placed all his token.
     * A player can perform Fly on his tokens if he has 3 tokens left.
     *
     */
    public void playTurn(){
        // Handle player allowable actions in every turn
        // If player has no tokens in hand, he can no longer place tokens on the board
        if (getTokensInHand() <= 0){
            this.removeAllowableAction(AllActions.PLACE_TOKEN); // remove the ability for place token action
            this.addAllowableAction(AllActions.MOVE_TOKEN);     // add the ability for move token action
        }
        if (canPlayerFly()){        // If player has 3 tokens left, he can fly
            this.addAllowableAction(AllActions.CAN_FLY);    // add the ability for fly action
        }
    }

    /**
     * Getter to retrieve the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Getter to retrieve the number of tokens the player has on the board.
     *
     * @return the number of tokens in hand of the player
     */
    public int getTokensOnBoard() {
        return tokensOnBoard;
    }

    /**
     * Getter to retrieve the colour of the token held by the player.
     *
     * @return the colour of the token held by the player
     */
    public TokenColour getTokenColour() {
        return tokenColour;
    }

    /**
     * Getter to retrieve the number of tokens the player has in hand.
     *
     * @return the number of tokens in hand of the player
     */
    public int getTokensInHand() {
        return tokensInHand;
    }

    /**
     * Getter to retrieve the status of the player whether it can fly or not.
     *
     * @return a boolean value whether the player can fly or not
     */
    public boolean canPlayerFly() {
        return canFly;
    }

    /**
     * This function is used when a player's token is placed on the board.
     * This function will only run if the player has at least one token in hand.
     * The number of tokens in hand is reduced by 1 and the number of tokens on board is increased by 1.
     */
    public void placeTokenOnBoard() {
        // Check if the player has at least one token in hand
        if (tokensInHand > 0) {
            tokensInHand--;     // Reduce the number of tokens in hand for this player by 1
            tokensOnBoard++;    // Increase the number of tokens on board for this player by 1
        }
    }

    /**
     * This function is used when a player's token is removed by another player and also checks if
     * player has reached the criteria to fly his token by only having 3 token left after his
     * token is removed by the opponent.
     * This function will only run if the player has at least one token on the board.
     * The number of tokens on board is reduced by 1.
     * If the number of tokens on board is 3 and the number of tokens in hand is 0, the player can fly.
     *
     */
    public void loseTokenOnBoard() {
        if (tokensOnBoard > 0) {
            tokensOnBoard--;        // Reduce the number of tokens on board for this player by 1

            // If the number of tokens on board is 3 and the number of tokens in hand is 0, the player can fly
            if (tokensOnBoard == 3 && getTokensInHand()==0) {
                canFly = true;
            }
        }
    }

    /**
     * This function is used to check if a player can perform a certain action.
     *
     * @param actions the action to be checked
     * @return a boolean value whether the action is allowed or not
     */
    public boolean isActionAllowed(AllActions actions){
        return allowableActions.allowedActions(actions);    // Return a boolean value whether the action is allowed or not
    }

    /**
     * This function is used to add an allowable action to the player.
     *
     * @param action the action to be added
     */
    public void addAllowableAction(AllActions action) {
        allowableActions.addAction(action);         // Add action to the list of allowable actions
    }

    /**
     * This function is used to remove an allowable action from the player.
     *
     * @param action the action to be removed
     */
    public void removeAllowableAction(AllActions action) {
        allowableActions.removeAction(action);         // Remove action from the list of allowable actions
    }

    /**
     * This function is used to retrieve the list of allowable actions for the player.
     *
     * @return the list of allowable actions for the player
     */
    public List<AllActions> getAllowableActions() {
        return allowableActions.actionList();       // Return the list of allowable actions
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", TOB='" + tokensOnBoard + '\'' +
                '}';
    }
}
