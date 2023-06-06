package Backend.Game;

/**
 * This class is used to run the tutorial, which is a child of game.
 * It is a derivative of the game class, and has its own implementation of the methods or attributes.
 * In this case, tutorial mode has extra methods to setup the board for predetermined scenarios
 * that are used to teach the player how to play the game.
 */
public class Tutorial extends Game {

    /**
     * Constructor.
     * Creates a new Tutorial object with the game object that it is associated with.
     * The gameMode is a TutorialMode with this instance of Tutorial as its game.
     *
     */
    public Tutorial() {
        super();
        setGameMode(new TutorialMode(this));
    }

}
