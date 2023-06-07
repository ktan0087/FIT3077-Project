package Backend.Action;

import Backend.Player;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a list of allowable actions for a player.
 *
 * @see Player
 * @see AllActions
 */
public class ActionList {

    /**
     * The list of allowable actions for this player
     */
    private final Set<AllActions> actionSet = new HashSet<AllActions>();


    /**
     * A function to check if the player is allowed the inputed action.
     *
     * @param actions the action to be checked
     * @return a boolean value whether the action is allowed or not
     */
    public boolean allowedActions(AllActions actions){
        return actionSet.contains(actions);
    }

    /**
     * A function to add an action to the list of allowable actions for this player.
     *
     * @param actions the action to be added
     */
    public void addAction(AllActions actions){
        if(!allowedActions(actions)) {      // If the action is not already in the list
            actionSet.add(actions);         // Add the action to the list
        }
    }

    /**
     * A function to remove an action from the list of allowable actions for this player.
     *
     * @param actions the action to be removed
     */
    public void removeAction(AllActions actions){
        if(allowedActions(actions)) {       // If the action is in the list
            actionSet.remove(actions);      // Remove the action from the list
        }
    }

    /**
     * A function to retrieve the list of allowable actions for this player.
     *
     * @return the list of allowable actions for this player
     */
    public List<AllActions> actionList (){
        return List.copyOf(actionSet);
    }

}
