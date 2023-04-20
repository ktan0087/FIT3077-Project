package Backend;

public enum AllActions {
    DO_NOTHING(new DoNothingAction());

    private final Action action;

    AllActions(Action action) {
        this.action = action;
    }

    public Action getAction() {
        return action;
    }

}
