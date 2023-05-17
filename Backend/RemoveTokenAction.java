package Backend;

public class RemoveTokenAction extends Action{

    /**
     * Private attributes of RemoveTokenAction
     */
    private Intersection removeIntersection;

    /**
     * Constructor for the action class
     *
     * @param player the player performing the action
     * @param game   the game the action is being performed in
     */
    public RemoveTokenAction(Player player,Intersection removeIntersection, Game game) {
        super(player, game);
        this.removeIntersection = removeIntersection;
    }

    /**
     * An abstract function to perform the action.
     *
     * @return a boolean value whether the action was successful or not
     */
    @Override
    public boolean execute() {
        boolean flag = false;
        System.out.println(player.getTokenColour());
        for (AllActions allactions: player.getAllowableActions()){
            System.out.println(allactions);

        }
        //check if the player is allowed to remove token
        if (player.isActionAllowed(AllActions.REMOVE_TOKEN)){
            //call removeToken() function from board
            if (game.getBoard().removeToken(player, removeIntersection)){
                flag = true;
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
        return player + "Remove a token";
    }
}
