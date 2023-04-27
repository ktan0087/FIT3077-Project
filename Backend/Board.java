package Backend;

import java.util.ArrayList;

/**
 * This class represents the board of the game, it contains a 2D array of intersections.
 */
public class Board {
    /**
     * Private attributes of Board
     */
    public static final int MAX_LAYERS = 4;
    public static final int MAX_POSITIONS = 9;
    private Intersection[][] intersection = new Intersection[MAX_LAYERS][MAX_POSITIONS];
    //ArrayList<Intersection[][]> intersectionList=new ArrayList<>();

    /**
     * Constructor
     * Function to initialise Board
     */
    public Board (){
        //Loop through the layers and positions to create the intersections
        for(int i = 1; i < MAX_LAYERS; i++){
            for(int j = 1; j < MAX_POSITIONS; j++){
                intersection[i][j] = makeNewIntersection(i, j);
            }
        }
    }

    /**
     * Getter function to get the intersection at the input layer and position
     * @param layer = x-coordinate of Intersection
     * @param position = y-coordinate of Intersection
     * @return the intersection at the input layer and position
     */
    public Intersection getIntersection(int layer, int position){
        return intersection[layer][position];
    }

    /**
     * Function to create a new intersection
     * @param layer = x-coordinate of Intersection
     * @param position = y-coordinate of Intersection
     * @return the new intersection
     */
    public Intersection makeNewIntersection(int layer, int position){
        return new Intersection(layer, position);
    }

    /**
     * Function to place token on the intersection given by the player
     * @return true if token is placed successfully,
     *         false if the intersection is occupied
     */
    public boolean placeToken(Player player, Intersection intersection){
        //check if the intersection is occupied
        if (intersection.isEmpty()){
            //call placeTokenOnBoard() function from player
            //to reduce the number of tokens on player's hand
            player.placeTokenOnBoard();
            //add token to the intersection
            intersection.addToken(new Token(player.getTokenColour()));
            return true;
        }
        return false;
    }

    /**
     * Function to move token from one intersection to another
     * @param player = player who is moving the token
     * @param intersection = origin intersection of the token
     * @param otherIntersection = intersection where the token is moving to
     * @return true if token is moved successfully,
     *         false if the other intersection is occupied or not adjacent
     */
    public boolean moveToken(Player player, Intersection intersection, Intersection otherIntersection){
        // check if the other intersection is occupied and is adjacent to the origin intersection
        if (otherIntersection.isEmpty() && intersection.isAdjacent(otherIntersection)){
            //call loseTokenOnBoard() function from player
            //to reduce the number of tokens on board
            player.loseTokenOnBoard();
            //remove token from the origin intersection
            intersection.removeToken();
            return true;
        }
        return false;
    }

    /**
     * Function to reset the board when the game is over
     * (Not implemented yet)
     */
    public static void resetBoard(){}
    // call isGameOver method to check
    // if true, end game and create new game

    /**
     * Function to check if the intersection is working
     */
//    public static void main(String[] args) {
//        Player p1 = new Player("KT", TokenColour.PLAYER_1_WHITE);
//        Player p2 = new Player("ZW", TokenColour.PLAYER_2_BLACK);
//        Board b = new Board();
//        PlaceTokenAction action1 = new PlaceTokenAction(p1, b.intersection[1][1], b);
//        System.out.println(action1.execute());
//
//        //b.placeToken(p1, b.intersection[1][1]);
//        System.out.println(b.intersection[1][1].isEmpty());
//        System.out.println((b.intersection[1][1].getToken().getTokenColour()));
//        //System.out.println((b.intersection[1][2].getToken().getTokenColour()));
//    }

}
