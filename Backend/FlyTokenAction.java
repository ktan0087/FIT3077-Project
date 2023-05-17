package Backend;

public class FlyTokenAction extends Action{

    /**
     * Private attributes of FlyTokenAction
     */
    private Intersection currentIntersection;
    private Intersection newIntersection;

    /**
     * Constructor for the action class
     *
     * @param player the player performing the action
     * @param game   the game the action is being performed in
     */
    public FlyTokenAction(Player player,Intersection currentIntersection, Intersection newIntersection, Game game) {
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
            //call flyToken() function from board
            if (game.getBoard().flyToken(player, currentIntersection, newIntersection)){
                flag = true;
                game.getBoard().isMill(player, newIntersection);
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
}
