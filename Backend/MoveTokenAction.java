package Backend;

public class MoveTokenAction extends Action{

    private Intersection currentIntersection;
    private Intersection newIntersection;

    public MoveTokenAction(Player player,Intersection currentIntersection, Intersection newIntersection,Game game){
        super(player,game);
        this.player = player;
        this.currentIntersection = currentIntersection;
        this.newIntersection = newIntersection;
        this.game = game;
    }

    @Override
    public boolean execute() {
        boolean flag = false;
        if (player.isActionAllowed(AllActions.MOVE_TOKEN)){
            if (game.getBoard().moveToken(player, currentIntersection, newIntersection)){
                flag = true;
            }
        }
        return flag;
    }


    @Override
    public String menuDescription(Player player) {
        return player + "moved a token to " + "(" + newIntersection.getLayer() + "," + newIntersection.getPosition() + ")";
    }
}
