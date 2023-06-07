package Backend.Game;

/**
 * This class is a subclass of GameMode. It is used to display the boards for the tutorial mode.
 * Tutorial mode is one of the game mode in our game. It is used to teach the player how to play the game.
 * It has 3 phases: Move, Fly and Place showcase and each phase has its own board setup.
 *
 * @see GameMode
 * @see NormalMode
 * @see Game
 */
public class TutorialMode extends GameMode {

    /**
     * Constructor.
     * Creates a new TutorialMode object with the game object that it is associated with.
     *
     * @param game is the game that is played
     */
    public TutorialMode(Game game) {
        super(game);
    }
    @Override
    public void startGame() {

    }

    /**
     * This method is used to setup the board for the Move phase of tutorial mode.
     * It sets up the board with the required tokens and calls the playTurn method of the current player.
     */
    public void displayBoardMove() {
        // Restart the game and create a new board
        game.restartGame();
        // Set up all black tokens on the board
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,3));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,2));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,1));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,7));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,4));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,5));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,5));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,7));
        // call playTurn method of the current player to keep track on the capabilities of player
        game.getOtherPlayer().playTurn();


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
        // call playTurn method of the current player to keep track on the capabilities of player
        game.getCurrentPlayer().playTurn();
    }

    public void displayBoardFly(){
        // Restart the game and create a new board
        game.restartGame();
        // Set up all black tokens on the board
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,3));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(2,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(3,8));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,6));
        game.getBoard().placeToken(game.getOtherPlayer(), game.getBoard().getIntersection(1,5));
        // check if there is any token in the hand of the player
        while(game.getOtherPlayer().getTokensInHand() > 0){
            // to update the number of tokens in hand of the player
            game.getOtherPlayer().placeTokenOnBoard();
            game.getOtherPlayer().loseTokenOnBoard();
        }
        // call playTurn method of the current player to keep track on the capabilities of player
        game.getOtherPlayer().playTurn();

        // Set up all white tokens on the board
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,1));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,6));
        // check if there is any token in the hand of the player
        while(game.getCurrentPlayer().getTokensInHand() > 0){
            // to update the number of tokens in hand of the player
            game.getCurrentPlayer().placeTokenOnBoard();
            game.getCurrentPlayer().loseTokenOnBoard();
        }
        // call playTurn method of the current player to keep track on the capabilities of player
        game.getCurrentPlayer().playTurn();
    }

    public void displayBoardButton(){
        // Restart the game and create a new board
        game.restartGame();
        // Set up all black tokens on the board
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(1,3));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(2,2));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(3,1));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(2,8));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(3,8));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(2,4));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(3,5));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(3,6));
        game.getBoard().placeToken(game.getPlayer2(), game.getBoard().getIntersection(2,5));
        // call playTurn method of the current player to keep track on the capabilities of player
        game.getPlayer2().playTurn();

        // Set up all white tokens on the board
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(2,3));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(3,3));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(1,8));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(3,4));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(1,4));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(3,7));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(2,6));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(1,6));
        game.getBoard().placeToken(game.getPlayer1(), game.getBoard().getIntersection(1,5));
        // call playTurn method of the current player to keep track on the capabilities of player
        game.getPlayer1().playTurn();
    }
}
