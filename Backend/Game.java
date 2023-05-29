package Backend;

import Backend.Action.AllActions;
import Backend.Board.Board;
import Backend.Board.Intersection;
import Backend.Board.Mill;
import Backend.Token.Token;
import Backend.Token.TokenColour;

import java.util.ArrayList;

import static Backend.Board.Board.MAX_LAYERS;
import static Backend.Board.Board.MAX_POSITIONS;

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
     * A constant to indicate the maximum number of tokens a player can have in a game.
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

    private ArrayList<Intersection> adjacentIntersectionList;
    private ArrayList<Intersection> possibleIntersectionList;
    private ArrayList<Token> possibleTokenList;

    // not yet implemented in this sprint
    private GameMode gameMode;

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
        gameMode = new TutorialMode(this);
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
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
        // check if players has placed all their tokens on the board
        if (player1.getTokensInHand() == 0 && player2.getTokensInHand() == 0){
            // check if the game is over by checking if one of the players only has 2 tokens left on the board
            // or if one of the players has no more possible moves left, the game is over
            if ((player1.getTokensOnBoard() <= MIN_TOKENS_PLAYER || player2.getTokensOnBoard() <= MIN_TOKENS_PLAYER) ||
                    !(this.checkPossibleMove(player1) || !(this.checkPossibleMove(player2)))) {
                isGameOver = true;
            }
        }
        return isGameOver;      // return the boolean to indicate if the game is over or not
    }

    /**
     * A function to get the winner of the game.
     * If a player has no more possible moves, the other player is the winner.
     * Else, the winner of the game is the player with the most tokens on the board.
     *
     * @return the winner of the game
     */
    public Player getWinner(){
        //Check both the players if they have any possible moves left
        if (!this.checkPossibleMove(player1)){
            return player2;
        }
        else if(!this.checkPossibleMove(player2)){
            return player1;
        }
        //If both players have possible moves, the winner is the player with the most tokens on the board
        else if(player1.getTokensOnBoard() < player2.getTokensOnBoard()) {
            return player2;
        }
        else if (player2.getTokensOnBoard() < player1.getTokensOnBoard()) {
            return player1;
        } else {
            return null;
        }
    }

    /**
     * A function to restart the game. (Similar to a constructor)
     * The function creates a new game with two players, a board, and a current player.
     * The first current player is always player 1.
     * The state of the game is not over.
     *
     */
    public void restartGame(){
        player1 = new Player("Player 1", TokenColour.PLAYER_1_WHITE);
        player2 = new Player("Player 2", TokenColour.PLAYER_2_BLACK);
        currentPlayer = player1;
        board = new Board();
        isGameOver = false;
    }

    /**
     * A function to check if a player has any possible moves.
     * If a player still has tokens in hand, the player has possible moves. Thus, function returns true.
     *
     * The function loops through the layers and positions of the board to check if the player has any possible moves.
     * If the player has a token on the board, the function checks if the token has any adjacent empty intersections.
     * If the player has no possible moves, the function returns false.
     *
     * @param player the player to check if they have any possible moves
     * @return a boolean to indicate if the player has any possible moves
     */
    public boolean checkPossibleMove(Player player) {
        boolean flag = false;
        // If a player still has tokens in hand or the player can fly, the player has possible moves.
        // Thus, function returns true.
        if (player.getTokensInHand()>0 || player.isActionAllowed(AllActions.FLY_TOKEN)) {
            return true;
        }
        //Loop through all the layers of the board
        for (int i = 1; i < MAX_LAYERS; i++) {
            adjacentIntersectionList = null;    //Reset the adjacent intersection list one every loop on the layers
            //Loop through all the positions of the layer
            for (int j = 1; j < MAX_POSITIONS; j++) {
                // check if the intersection is not empty and the token is the player's token
                if (!getBoard().getIntersection(i, j).isEmpty() && getBoard().getIntersection(i, j).getToken().getTokenColour() == player.getTokenColour()) {
                    // get the adjacent intersections of the token
                    adjacentIntersectionList = board.getAdjacentIntersectionSmall(getBoard().getIntersection(i,j));
                }
                if (adjacentIntersectionList != null) {
                    // check if the token has any adjacent intersections that are empty
                    for (Intersection intersection : adjacentIntersectionList) {
                        if (intersection.isEmpty()) {  // if the intersection is empty, the player has possible moves
                            flag = true;
                        }
                    }
                }
            }
        }
        return flag;
    }
    /**
     * A function to check if any of the other's token can be removed.
     * The function loops through the layers and positions of the board to check if the player has any token on board
     * and add them into intersections list.
     * The function loops through all the mills on the board to check if the player has any token in a mill and add them
     * into millIntersections list.
     * If the size of intersections list and the size of millIntersections list are the same, the opponent has no possible
     * tokens to be removed. Thus, function returns false.
     * If the size of the lists are not the same, the function returns true
     *
     * @param player the player to check if they have any possible moves
     * @return a boolean to indicate if the player has any possible moves
     */
    public boolean checkPossibleRemove(Player player){
        boolean flag = true;
        ArrayList<Intersection> intersections = new ArrayList<>();
        ArrayList<Intersection> millIntersections = new ArrayList<>();
        //Loop through all the layers of the board
        for(int i = 1; i < MAX_LAYERS; i++){
            //Loop through all the positions of the layer
            for(int j = 1; j < MAX_POSITIONS; j++){
                //Check if the intersection is not empty and the token is the player's token
                if(!board.getIntersection(i,j).isEmpty() && board.getIntersection(i,j).getToken().getTokenColour() == player.getTokenColour()){
                    intersections.add(board.getIntersection(i, j)); //Add the intersection of the Token into intersections list
                }
            }
        }
        //Loop through all the mills of the board
        for (Mill mill : board.getMills()) {
            //Check if the mill is the player's mill
            if (mill.getPlayer()==player) {
                //Loop through all the intersections of the mill and add them into millIntersections list if they
                //are not already in the list.
                for (Intersection intersection : mill.getIntersection()) {
                    if(!millIntersections.contains(intersection)){
                        millIntersections.add(intersection);
                    }
                }
            }
        }
        //Check if the size of intersections list is the same as the size of millIntersection list
        //If they are the same, it means that all the player's tokens are in mills and cannot be removed.
        if(intersections.size() == millIntersections.size()){
            flag = false;
        }
        return flag;
    }

    /**
     * A function to get the possible intersections for the player to place a token.
     * @param currentIntersection the current intersection of the token
     * @param player current player
     * @return an array list of possible intersections for the player select
     */
    public ArrayList<Intersection> getPossibleIntersectionList(Intersection currentIntersection, Player player) {
        // check if player has any possible moves
        if (this.checkPossibleMove(player)) {
            // check if the player can fly or place a token
            if (player.isActionAllowed(AllActions.FLY_TOKEN) || player.isActionAllowed(AllActions.PLACE_TOKEN)) {
                possibleIntersectionList =  board.getEmptyIntersection();
            }
            // check if the player can move a token
            else if (player.isActionAllowed(AllActions.MOVE_TOKEN)) {
                possibleIntersectionList = board.getAdjacentIntersectionSmall(currentIntersection);
            }
        }
        return possibleIntersectionList;
    }

    /**
     * A function to get the possible tokens for the player to remove.
     * @param player current player
     * @return an array list of possible tokens of the opponent for the player to remove
     */
    public ArrayList<Token> getPossibleTokenList(Player player) {
        // check if player can remove a token
        if (player == player1 && player.isActionAllowed(AllActions.REMOVE_TOKEN)) {
            possibleTokenList = board.getPlayerTokensOnBoard(player2);
        }
        else{
            possibleTokenList = board.getPlayerTokensOnBoard(player1);
        }
        return possibleTokenList;
    }
}
