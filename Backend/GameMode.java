package Backend;

// This class is not yet implemented in the current sprint.
public abstract class GameMode {

    protected Game game;

    public GameMode(Game game) {
        this.game = game;
    }

    public abstract void startGame();
    public abstract void endGame();

    public abstract void displayBoardMove();
    public abstract void displayBoardFly();

    public abstract void displayBoardButton();
}
