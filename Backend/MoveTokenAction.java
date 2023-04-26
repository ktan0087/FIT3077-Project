package Backend;

public class MoveTokenAction extends Action{
    private Player player;
    private Intersection currentIntersection;
    private Intersection newIntersection;
    private Board board;

    public MoveTokenAction(Player player,Intersection currentIntersection, Intersection newIntersection,Board board){
        this.player = player;
        this.currentIntersection = currentIntersection;
        this.newIntersection = newIntersection;
        this.board = board;
    }

    @Override
    public boolean execute(Player player, Board board) {
        boolean flag = false;
        if (player.isActionAllowed(AllActions.MOVE_TOKEN)){
            board.moveToken(player, currentIntersection, newIntersection);
            flag = true;
        }
        return flag;
    }

    @Override
    public String menuDescription(Player player) {
        return player + "moved a token to " + "(" + newIntersection.getLayer() + "," + newIntersection.getPosition() + ")";
    }
}
