package Backend;

/**
 * A class to represent the tutorial mode of the game.
 *
 * @see GameMode
 */
public class TutorialMode extends GameMode{

    /**
     * Constructor.
     * Creates a new TutorialMode object with the game object that it is associated with.
     *
     * @param game is the game that is played
     */
    public TutorialMode(Game game) {
        super(game);
    }

    /**
     * This method is used to start the game.
     * Tutorial mode does not have a normal game procedure, so this method does nothing.
     *
     */
    @Override
    public void startGame() {
        return;
    }

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Move phase of tutorial mode.
     *
     */
    @Override
    public void displayBoardMove() {
        game.restartGame();
        // Set up all black tokens on the board
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,3));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,2));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,1));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,7));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,7));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,4));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,5));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,5));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,7));
        game.getOtherPlayer().playTurn(); // Check Player 2's capability after the moves


        // Set up all white tokens on the board
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,3));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,3));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,8));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,4));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,4));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,7));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,5));
        game.getCurrentPlayer().playTurn(); // Check Player 1's capability after the moves
    }

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Fly phase of tutorial mode.
     *
     */
    @Override
    public void displayBoardFly(){
        game.restartGame();
        // Set up all black tokens on the board
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,3));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,6));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,5));
        while(game.getOtherPlayer().getTokensInHand() > 0){
            game.getOtherPlayer().placeTokenOnBoard();
            game.getOtherPlayer().loseTokenOnBoard();
        }
        game.getOtherPlayer().playTurn(); // Check Player 2's capability after the moves

        // Set up all white tokens on the board
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,1));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,6));
        while(game.getCurrentPlayer().getTokensInHand() > 0){
            game.getCurrentPlayer().placeTokenOnBoard();
            game.getCurrentPlayer().loseTokenOnBoard();
        }
        game.getCurrentPlayer().playTurn(); ;// Check Player 1's capability after the moves
    }

    /**
     * This method is specifically used by tutorial mode to setup the board required
     * on the Button showcase phase of tutorial mode.
     *
     */
    @Override
    public void displayBoardButton(){
        game.restartGame();
        // Set up all black tokens on the board
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,3));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,2));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,1));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,4));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,5));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,6));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,5));
        game.getOtherPlayer().playTurn(); // Check Player 2's capability after the moves

        // Set up all white tokens on the board
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,3));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,3));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,8));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,4));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,4));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,7));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,5));
        game.getCurrentPlayer().playTurn(); // Check Player 1's capability after the moves
    }



}
