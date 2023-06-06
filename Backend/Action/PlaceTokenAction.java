package Backend.Action;

import Backend.Game.Game;
import Backend.Board.Intersection;
import Backend.Player;

/**
 * This class represents the action of placing a token on an intersection.
 * This action is executed when player at the initial part of the game.
 *
 * @see Backend.Action.Action
 * @see Backend.Action.AllActions
 * @see Backend.Board.Board
 * @see Backend.Board.Intersection
 * @see Game
 * @see Backend.Player
 */
public class PlaceTokenAction extends Action {

    /**
     * Private attributes of PlaceTokenAction
     */
    private Intersection placeIntersection;

    /**
     * Constructor
     * @param player = player who is placing the token
     * @param placeIntersection = intersection where the token is placed
     * @param game = current game
     */
    public PlaceTokenAction(Player player, Intersection placeIntersection, Game game) {
        super(player, game);
        this.placeIntersection = placeIntersection;
    }

    /**
     * Function to execute the action
     * @return true if the action is executed successfully,
     *         false if the action is not executed successfully
     */
    @Override
    public boolean execute() {
        boolean flag = false;
        //check if the player is allowed to move token
        if (player.isActionAllowed(AllActions.PLACE_TOKEN) && !(player.isActionAllowed(AllActions.REMOVE_TOKEN))){
            //call the placeToken() function from board
            //reduce token in hand and add token on board at the intersection given
            if (game.getBoard().placeToken(player, placeIntersection)) {
                flag = true;
                //call isMill() function to create mill if this action creates a mill
                game.getBoard().isMill(player, placeIntersection);
            }
        }
        return flag;
    }

    /**
     * Function to get the description of the action
     * @param player = player who is placing the token
     * @return the description of the action
     */
    @Override
    public String menuDescription(Player player) {
        return player + "Place a token";
    }
}
