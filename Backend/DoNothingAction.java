package Backend;

public class DoNothingAction extends Action{

    public DoNothingAction() {
    }

    @Override
    public String execute(Player player) {
        return "You do nothing.";
    }

    @Override
    public String menuDescription(Player player) {
        return "Do nothing";
    }
}
