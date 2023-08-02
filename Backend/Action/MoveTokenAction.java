package Backend.Action;

import Backend.*;
import Backend.Board.Intersection;
import Backend.Board.Mill;
import Backend.Game.Game;
import Backend.Interfaces.CanRemoveMill;

import java.util.ArrayList;

/**
 * This class represents the action of moving a token from one intersection to another
 * This action execute when the player has no tokens in hand
 *
 * @see Backend.Board.Intersection
 * @see Backend.Board.Mill
 * @see Backend.Interfaces.CanRemoveMill
 * @see Backend.Action.Action
 */
public class MoveTokenAction extends Action implements CanRemoveMill {

    /**
     * Private attributes of MoveTokenAction
     */
    private Intersection currentIntersection;
    private Intersection newIntersection;
    private ArrayList<Mill> removeMillList = new ArrayList<>();

    /**
     * Constructor
     * @param player = player who is moving the token
     * @param currentIntersection = current intersection of the token
     * @param newIntersection = new intersection of the token
     * @param game = current game
     */
    public MoveTokenAction(Player player, Intersection currentIntersection, Intersection newIntersection, Game game){
        super(player,game);
        this.currentIntersection = currentIntersection;
        this.newIntersection = newIntersection;
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
        if (player.isActionAllowed(AllActions.MOVE_TOKEN) && !(player.isActionAllowed(AllActions.REMOVE_TOKEN))){
            //call moveToken() function from board
            //remove token from current intersection and add to new intersection
            if (game.getBoard().moveToken(player, currentIntersection, newIntersection)){
                flag = true;
                //check if moving the token to the new intersection forms a mill
                game.getBoard().isMill(player, newIntersection);
                //add previous mill to removeMillList as moving to a new intersection
                //will break the mill if current intersection has a mill
                this.addRemoveMill();
            }
        }
        return flag;
    }

    /**
     * Function to get the description of the action
     * @param player = player who is moving the token
     * @return the description of the action
     */
    @Override
    public String menuDescription(Player player) {
        return player + "moved a token to " + "(" + newIntersection.getLayer() + "," + newIntersection.getPosition() + ")";
    }

    /**
     * Function to add the mill to the removeMillList, so that when the move action is executed,
     * then the token is part of a mill, we need to remove the mill from the board.
     */
    @Override
    public void addRemoveMill() {
        //loop through all mills and check if the current intersection is in the mill
        for (Mill mills: game.getBoard().getMills()){
            //if the mill contains the current intersection, add the mill to the removeMillList
            // so that the mill can be removed from the board after the move action
            if(mills.getIntersection().contains(currentIntersection)){
                //add the mill to the removeMillList
                removeMillList.add(mills);
            }
        }
        //loop through the removeMillList and remove the mill from the board
        for (Mill mills: removeMillList){
            game.getBoard().getMills().remove(mills);
        }
    }

    /**
     * Function to get the removeMillList
     * @return
     */
    public ArrayList<Mill> getRemoveMillList() {
        return removeMillList;
    }
}
