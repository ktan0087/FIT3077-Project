package Backend;

import java.util.ArrayList;

public class Game {

    public static final int MAX_TOKENS_PLAYER = 9;

    public static final int MIN_TOKENS_PLAYER = 2;

    private Player player1;

    private Player player2;

    private Player currentPlayer;

    private Board board;

    private boolean isGameOver;

//    private GameMode gameMode;

    public Game() {
        player1 = new Player("Player 1", TokenColour.PLAYER_1_WHITE);
        player2 = new Player("Player 2", TokenColour.PLAYER_2_BLACK);
        currentPlayer = player1;
        board = new Board();
        isGameOver = false;
    }

    public void swapPlayers() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
            return currentPlayer;
        }

    private boolean isGameOver() {
        if (player1.getTokensOnBoard() <= MIN_TOKENS_PLAYER || player2.getTokensOnBoard() <= MIN_TOKENS_PLAYER) {
            isGameOver = true;
        }
        return isGameOver;
    }

}
