package Backend;

/**
 * This class represents the action of moving a token from one intersection to another
 */
public class MoveTokenAction extends Action{

    /**
     * Private attributes of MoveTokenAction
     */
    private Intersection currentIntersection;
    private Intersection newIntersection;

    /**
     * Constructor
     * @param player = player who is moving the token
     * @param currentIntersection = current intersection of the token
     * @param newIntersection = new intersection of the token
     * @param game = current game
     */
    public MoveTokenAction(Player player,Intersection currentIntersection, Intersection newIntersection,Game game){
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
            if (game.getBoard().moveToken(player, currentIntersection, newIntersection)){
                flag = true;
                game.getBoard().isMill(player, newIntersection);
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
}
