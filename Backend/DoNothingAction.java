package Backend;

public class DoNothingAction extends Action{

    public DoNothingAction() {
    }

    @Override
    public boolean execute(Player player, Board board) {
        return true;
    }

    @Override
    public String menuDescription(Player player) {
        return "Do nothing";
    }
}
