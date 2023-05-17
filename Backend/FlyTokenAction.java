package Backend;

import java.util.ArrayList;

public class FlyTokenAction extends Action implements CanRemoveMill{

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


    @Override
    public void addRemoveMill() {
        for (Mill mills: game.getBoard().getMills()){
            if(mills.getIntersection().contains(currentIntersection)){
                removeMillList.add(mills);
            }
        }
        for (Mill mills: removeMillList){
            game.getBoard().getMills().remove(mills);
        }
    }

    public ArrayList<Mill> getRemoveMillList() {
        return removeMillList;
    }
}
