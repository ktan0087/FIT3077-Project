package Backend;

public class DoNothingAction extends Action{

    public DoNothingAction() {
    }

    @Override
    public boolean execute() {
        return true;
    }

    @Override
    public String menuDescription(Player player) {
        return "Do nothing";
    }
}
