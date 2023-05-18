package Backend;

import Backend.Board.Board;
import Backend.Token.Token;
import Backend.Token.TokenColour;

/**
 * A class to represent the game of Nine Men's Morris.
 * A game can have two players, a board, and a current player.
 *
 * @see Player
 * @see Board
 * @see Token
 * @see TokenColour
 */
public class Game {

    /**
     * A constant tp indicate the maximum number of tokens a player can have in a game
     */
    public static final int MAX_TOKENS_PLAYER = 9;

    /**
     * A constant to indicate the minimum number of tokens a player can have in a game before they lose.
     */
    public static final int MIN_TOKENS_PLAYER = 2;

    /**
     * The first player of the game
     */
    private Player player1;

    /**
     * The second player of the game
     */
    private Player player2;

    /**
     * The current player of the game
     */
    private Player currentPlayer;

    /**
     * The board of the game which the Nine Men's Morris is played on
     */
    private Board board;

    /**
     * A boolean to indicate if the game is over or not
     */
    private boolean isGameOver;

//    // not yet implemented in this sprint
//    private GameMode gameMode;

    /**
     * Constructor.
     * Creates a new game with two players, a board, and a current player.
     * The first current player is always player 1.
     * The state of the game is not over.
     *
     */
    public Game() {
        player1 = new Player("Player 1", TokenColour.PLAYER_1_WHITE);
        player2 = new Player("Player 2", TokenColour.PLAYER_2_BLACK);
        currentPlayer = player1;
        board = new Board();
        isGameOver = false;
    }

    /**
     * A function to end a player's turn.
     * The function checks if the game has ended, swaps the current player,
     * and runs playTurn() on each player which checks their current allowed actions.
     */
    public void endTurn(){
        isGameOver();           // check if the game is over
        swapPlayers();          // swap the current player
        player1.playTurn();     // check player 1's allowable actions
        player2.playTurn();     // check player 2's allowable actions
    }

    /**
     * A function to swap the current player.
     * If the current player is player 1, the current player is set to player 2.
     * If the current player is player 2, the current player is set to player 1.
     *
     */
    public void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;        // swap to player 2 if current player is player 1
        } else {
            currentPlayer = player1;        // swap to player 1 if current player is player 2
        }
    }

    /**
     * Getter for the board in the game.
     *
     * @return the board of the game
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Getter for the current player in the game.
     *
     * @return the current player of the game
     */
    public Player getCurrentPlayer() {
            return currentPlayer;
    }

    public Player getOtherPlayer() {
        if (currentPlayer == player1) {
            return player2;
        } else {
            return player1;
        }
    }

    /**
     * A function to check if the game is over.
     * The game is over if one of the players only has 2 tokens left on the board.
     *
     * @return A boolean to indicate if the game is over or not
     */
    public boolean isGameOver() {

        if (player1.getTokensInHand() == 0 && player2.getTokensInHand() == 0){
            // check if the game is over by checking if one of the players only has 2 tokens left on the board
            if (player1.getTokensOnBoard() <= MIN_TOKENS_PLAYER || player2.getTokensOnBoard() <= MIN_TOKENS_PLAYER) {
                isGameOver = true;
            }
        }
        return isGameOver;      // return the boolean to indicate if the game is over or not
    }

    public Player getWinner(){
        // check if the game is over by checking if one of the players only has 2 tokens left on the board
        if (player1.getTokensOnBoard() <= MIN_TOKENS_PLAYER) {
            return player2;
        } else if (player2.getTokensOnBoard() <= MIN_TOKENS_PLAYER) {
            return player1;
        } else {
            return null;
        }
    }

    public void restartGame(){
        player1 = new Player("Player 1", TokenColour.PLAYER_1_WHITE);
        player2 = new Player("Player 2", TokenColour.PLAYER_2_BLACK);
        currentPlayer = player1;
        board = new Board();
        isGameOver = false;
    }

}
