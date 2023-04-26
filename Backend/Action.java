package Backend;

public abstract class Action {

    public abstract boolean execute(Player player, Board board);

    public abstract String menuDescription(Player player);

}
