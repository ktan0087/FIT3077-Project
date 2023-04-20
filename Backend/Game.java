package Backend;

import java.util.ArrayList;

public class Game {

    public static final int MAX_TOKENS_PLAYER = 9;

    public static final int MIN_TOKENS_PLAYER = 2;

    private Player player1;

    private Player player2;

    private ArrayList<Player> players = new ArrayList<Player>();

    private Board board;

    private boolean isGameOver;

//    private GameMode gameMode;

    public Game() {
        player1 = new Player("Player 1", TokenColour.PLAYER_1_WHITE);
        player2 = new Player("Player 2", TokenColour.PLAYER_2_BLACK);
        players.add(player1);
        players.add(player2);
        board = new Board();
        isGameOver = false;
    }

    private boolean isGameOver() {
        for (Player player : players){
            if (player.getTokensOnBoard() <= MIN_TOKENS_PLAYER) {
                isGameOver = true;
            }
        }
        return isGameOver;
    }



}
