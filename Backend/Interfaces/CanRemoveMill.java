package Backend.Interfaces;

import Backend.Board.Mill;

import java.util.ArrayList;

/**
 * This interface represents the ability to remove a mill
 *
 * @see Backend.Board.Mill
 * @see Backend.Action.MoveTokenAction
 * @see Backend.Action.RemoveTokenAction
 */

public interface CanRemoveMill {
    /**
     * Private attributes of CanRemoveMill
     */
    ArrayList<Mill> removeMillList = new ArrayList<>();

    /**
     * Function to add mill to the removeMillList
     */
    void addRemoveMill();

    /**
     * Getter function to get the removeMillList
     * @return
     */
    ArrayList<Mill> getRemoveMillList();
}
