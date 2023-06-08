package Backend.Action;

import Backend.Game.Game;
import Backend.Player;

/**
 * An abstract class to represent the actions that can be performed by a player
 *
 * @see ActionList
 */
public abstract class Action {

    /**
     * The player performing the action
     */
    protected Player player;

    /**
     * The game the action is being performed in
     */
    protected Game game;
    /**
     * Constructor for the action class
     *
     * @param player the player performing the action
     * @param game the game the action is being performed in
     */
    public Action(Player player, Game game){
        this.player = player;
        this.game = game;
    }

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
