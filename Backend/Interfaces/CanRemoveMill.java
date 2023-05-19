package Backend.Interfaces;

import Backend.Board.Mill;

import java.util.ArrayList;

/**
 * This interface is used to remove mill after action is performed
 *
 * @see Backend.Action.MoveTokenAction
 * @see Backend.Action.RemoveTokenAction
 */
public interface CanRemoveMill {
    /**
     * removeMillList is a list of mills that are to be removed
     */
    ArrayList<Mill> removeMillList = new ArrayList<>();

    /**
     * Function to add mill to the list of mills to be removed
     */
    void addRemoveMill();

    /**
     * Function to get the list of mills to be removed
     * @return the list of mills to be removed
     */
    ArrayList<Mill> getRemoveMillList();
}
