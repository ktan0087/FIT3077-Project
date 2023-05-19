package Backend.Action;

import Backend.*;
import Backend.Board.Intersection;
import Backend.Board.Mill;
import Backend.Interfaces.CanRemoveMill;

import java.util.ArrayList;

/**
 * This class represents the action of flying a token from one intersection to another.
 * This action execute when the player has 3 tokens on board.
 *
 * @see Backend.Board.Intersection
 * @see Backend.Board.Mill
 * @see Backend.Interfaces.CanRemoveMill
 * @see Backend.Action.Action
 *
 */
public class FlyTokenAction extends Action implements CanRemoveMill {

    /**
     * Private attributes of FlyTokenAction
     */
    private Intersection currentIntersection;
    private Intersection newIntersection;
    private ArrayList<Mill> removeMillList = new ArrayList<>();

    /**
     * Constructor for the action class
     *
     * @param player the player performing the action
     * @param game   the game the action is being performed in
     */
    public FlyTokenAction(Player player, Intersection currentIntersection, Intersection newIntersection, Game game) {
        super(player, game);
        this.currentIntersection = currentIntersection;
        this.newIntersection = newIntersection;
    }

    /**
     * An abstract function to perform the action.
     *
     * @return a boolean value whether the action was successful or not
     */
    @Override
    public boolean execute() {
        boolean flag = false;
        //check if the player is allowed to fly token
        if (player.isActionAllowed(AllActions.CAN_FLY) && !(player.isActionAllowed(AllActions.REMOVE_TOKEN))){
            //call flyToken() function from board that removes the token from the current intersection
            // and adds it to the new intersection
            if (game.getBoard().flyToken(player, currentIntersection, newIntersection)){
                // if the function is successfully excuted, set flag to true
                flag = true;
                // check if the new intersection is part of a mill after the token is fly to the new intersection
                game.getBoard().isMill(player, newIntersection);
                // add the mill to the removeMillList, which will be used to remove
                // the mill from the board as the mill is no longer valid at the current intersection
                this.addRemoveMill();
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
        return player + "Flying a token";
    }


    /**
     * A function to add the mill to the removeMillList
     */
    @Override
    public void addRemoveMill() {
        //loop through all the mills on the board
        for (Mill mills: game.getBoard().getMills()){
            //if the mill contains the current intersection, add the mill to the removeMillList
            // so that the mill can be removed from the board after the fly action
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
     * A function to get the Mill list that wanted to remove
     * @return
     */
    public ArrayList<Mill> getRemoveMillList() {
        return removeMillList;
    }
}
