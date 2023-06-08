package Backend.Action;

import Backend.Game.Game;
import Backend.Board.Intersection;
import Backend.Player;

/**
 * This class represents the action of removing a token from an intersection
 * if the token is not part of a mill and if the token is not the current player's token
 * This action is execute when player has formed a mill
 *
 * @see Backend.Action.Action
 * @see Backend.Action.AllActions
 * @see Backend.Board.Board
 * @see Backend.Board.Intersection
 * @see Game
 * @see Backend.Player
 */
public class RemoveTokenAction extends Action {

    /**
     * Private attributes of RemoveTokenAction
     */
    private Intersection removeIntersection;

    /**
     * Constructor for the action class
     *
     * @param player the player performing the action
     * @param game   the game the action is being performed in
     */
    public RemoveTokenAction(Player player, Intersection removeIntersection, Game game) {
        super(player, game);
        this.removeIntersection = removeIntersection;
    }

    /**
     * An abstract function to perform the action.
     *
     * @return a boolean value whether the action was successful or not
     */
    @Override
    public boolean execute() {
        boolean flag = false;
        //check if the player is allowed to remove token
        if (player.isActionAllowed(AllActions.REMOVE_TOKEN)){
            //call removeToken() function from board, check if token is from mill
            // and check if removed token is not current player's token
            if (game.getBoard().removeToken(player, removeIntersection)){
                flag = true;
                //other player loses a token on board if other player's token
                // is more than 0
                game.getOtherPlayer().loseTokenOnBoard();
                //remove REMOVE_TOKEN ability from player
                player.removeAllowableAction(AllActions.REMOVE_TOKEN);
            }
        }
        return flag;
    }

    /**
     * An abstract function to describe what the action does.
     *
     * @param player The player performing the action.
     * @return a description of the action.
     */
    @Override
    public String menuDescription(Player player) {
        return player + "Remove a token";
    }
}
