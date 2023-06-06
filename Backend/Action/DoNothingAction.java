package Backend.Action;

import Backend.Game.Game;
import Backend.Player;

/**
 * A class to represent the action of doing nothing.
 *
 * @see Action
 */
public class DoNothingAction extends Action{

    /**
     * Constructor.
     */
    public DoNothingAction(Player player, Game game) {
        super(player, game);
    }

    /**
     * Perform the Do Nothing Action.
     *
     * @return true
     */
    @Override
    public boolean execute() {
        return true;
    }

    /**
     * A string describing what the action does.
     *
     * @param player The player performing the action.
     * @return a description of the action.
     */
    @Override
    public String menuDescription(Player player) {
        return "Do nothing";
    }
}
