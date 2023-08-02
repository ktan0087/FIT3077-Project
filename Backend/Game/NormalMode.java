package Backend.Game;

/**
 * This is a subclass of GameMode. It is a normal mode of the game.
 * The normal mode is the default mode of the game, therefore
 * it does not have any special implementation of the methods.
 */
public class NormalMode extends GameMode {

    /**
     * Constructor.
     * Creates a new NormalMode object with the game object that it is associated with.
     *
     * @param game is the game that is played
     */
    public NormalMode(Game game) {
        super(game);
    }

    /**
     * This method is used to start the game.
     */
    @Override
    public void startGame() {
    }

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Button showcase phase of tutorial mode.
     *
     */
    @Override
    public void displayBoardButton() {
    }

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Move phase of tutorial mode.
     *
     */
    @Override
    public void displayBoardMove() {
    }

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Fly phase of tutorial mode.
     *
     */
    @Override
    public void displayBoardFly() {
    }
}
