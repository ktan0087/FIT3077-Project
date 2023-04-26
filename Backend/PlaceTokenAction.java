package Backend;

public class PlaceTokenAction extends Action{

    private Player player;
    private Intersection placeIntersection;
    private Game game;

    public PlaceTokenAction(Player player,Intersection placeIntersection, Game game) {
        this.player = player;
        this.placeIntersection = placeIntersection;
        this.game = game;
    }


    @Override
    public boolean execute() {
        boolean flag = false;
        if (player.isActionAllowed(AllActions.PLACE_TOKEN)) {
            if (game.getBoard().placeToken(player, placeIntersection)) {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public String menuDescription(Player player) {
        return player + "Place a token";
    }
}
