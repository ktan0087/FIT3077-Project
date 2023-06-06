package Backend;

import Backend.Action.AllActions;

public class TutorialMode extends GameMode{

    public TutorialMode(Game game) {
        super(game);
    }
    @Override
    public void startGame() {

    }

    public void displayBoardMove() {
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
        game.getCurrentPlayer().playTurn();
    }

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
        game.getOtherPlayer().playTurn();

        // Set up all white tokens on the board
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(1,1));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(2,6));
        game.getBoard().placeToken(game.getCurrentPlayer(), game.getBoard().getIntersection(3,6));
        while(game.getCurrentPlayer().getTokensInHand() > 0){
            game.getCurrentPlayer().placeTokenOnBoard();
            game.getCurrentPlayer().loseTokenOnBoard();
        }
        game.getCurrentPlayer().playTurn();
    }

    public void displayBoardButton(){
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
        game.getPlayer1().playTurn();
    }



}
