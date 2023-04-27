package Backend;

/**
 * An abstract class to represent the actions that can be performed by a player
 *
 * @see ActionList
 */
public abstract class Action {

    /**
     * An abstract function to perform the action.
     *
     * @return a boolean value whether the action was successful or not
     */
    public abstract boolean execute();

    /**
     * An abstract function to describe what the action does.
     *
     * @param player The player performing the action.
     * @return a description of the action.
     */
    public abstract String menuDescription(Player player);

}
