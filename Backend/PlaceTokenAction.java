package Backend;

public class PlaceTokenAction extends Action{

    private Player player;
    private Intersection placeIntersection;
    private Board board;

    public PlaceTokenAction(Player player,Intersection placeIntersection, Board board) {
        this.player = player;
        this.placeIntersection = placeIntersection;
        this.board = board;
    }

    @Override
    public boolean execute() {
        boolean flag = false;
        if (player.isActionAllowed(AllActions.PLACE_TOKEN)){
            board.placeToken(player, placeIntersection);
            flag = true;
        }
        return flag;
    }

    @Override
    public String menuDescription(Player player) {
        return player + "Place a token";
    }
}
