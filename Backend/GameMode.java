package Backend;

/**
 * This class is used to differentiate between different game modes.
 * Other game modes such as tutorial mode has their own implementation of the methods.
 *
 * @see TutorialMode
 */
public abstract class GameMode {

    /**
     * The game that is played
     */
    protected Game game;

    /**
     * Constructor.
     * Creates a new GameMode object with the game object that it is associated with.
     *
     * @param game is the game that is played
     */
    public GameMode(Game game) {
        this.game = game;
    }

    /**
     * This method is used to start the game.
     * Main difference between each game mode
     *
     */
    public abstract void startGame();

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Move phase of tutorial mode.
     *
     */
    public abstract void displayBoardMove();
    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Fly phase of tutorial mode.
     *
     */
    public abstract void displayBoardFly();

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Button showcase phase of tutorial mode.
     *
     */
    public abstract void displayBoardButton();
}
