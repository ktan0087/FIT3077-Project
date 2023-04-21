package Backend;

public class PlaceTokenAction extends Action{

    private Player player;
    private int coordinateX;

    private int coordinateY;

    private Board board;

    public PlaceTokenAction(Player player, int coordinateX, int coordinateY, Board board) {
        this.player = player;
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.board = board;
    }



    @Override
    public String execute(Player player) {

        return "";
    }

    @Override
    public String menuDescription(Player player) {
        return "Place a token";
    }
}
