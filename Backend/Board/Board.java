package Backend.Board;

import Backend.Action.AllActions;
import Backend.Action.PlaceTokenAction;
import Backend.Game;
import Backend.Player;
import Backend.Token.Token;
import Backend.Token.TokenColour;

import java.util.ArrayList;

/**
 * This class represents the board of the game, it contains a 2D array of intersections.
 * The board is made up of 3 layers and 8 positions.
 * The board can have multiple mills store in the mills Arraylist
 *
 * @see Backend.Board.Intersection
 * @see Backend.Board.Mill
 * @see Backend.Action.MoveTokenAction
 * @see Backend.Action.RemoveTokenAction
 * @see Backend.Action.PlaceTokenAction
 * @see Backend.Action.FlyTokenAction
 */
public class Board {
    /**
     * The maximum number of layers on the board
     */
    public static final int MAX_LAYERS = 4;
    /**
     * The maximum number of positions on the board
     */
    public static final int MAX_POSITIONS = 9;
    /**
     * The 2D array of intersections
     */
    private Intersection[][] intersection = new Intersection[MAX_LAYERS][MAX_POSITIONS];
    /**
     * The list of mills on the board
     */
    private ArrayList<Mill> mills = new ArrayList<>();
    /**
     * The list of adjacent intersections
     */
    private ArrayList<Intersection> adjacentIntersection = new ArrayList<>();

    /**
     * Constructor
     * Function to initialise Board by looping through the layers and positions to create the intersections.
     * The board is made up of 3 layers and 8 positions.
     *
     *            It looks like this:  1--------2--------3
     *                                 |  1     2     3  |
     *                                 |     1  2  3     |
     *                                 8  8  8     4  4  4
     *                                 |     7  6  5     |
     *                                 |   7    6     5  |
     *                                 7--------6--------5
     *
     */
    public Board (){
        //Loop through the layers and positions to create the intersections
        for(int i = 1; i < MAX_LAYERS; i++){
            for(int j = 1; j < MAX_POSITIONS; j++){
                //create new intersections
                intersection[i][j] = makeNewIntersection(i, j);
            }
        }
    }

    /**
     * Getter function to get the intersection at the input layer and position
     *
     * @param layer = x-coordinate of Intersection
     * @param position = y-coordinate of Intersection
     * @return the intersection at the input layer and position
     */
    public Intersection getIntersection(int layer, int position){
        return intersection[layer][position];
    }

    /**
     * Function to create a new intersection at the input layer and position
     *
     * @param layer = x-coordinate of Intersection
     * @param position = y-coordinate of Intersection
     * @return the new intersection
     */
    public Intersection makeNewIntersection(int layer, int position){
        return new Intersection(layer, position);
    }

    /**
     * Function to get all the Mills on board
     *
     * @return the mill list
     */
    public ArrayList<Mill> getMills() {
        return mills;
    }

    /**
     * Function to get all the empty intersections on board
     *
     * @return list of all empty intersections
     */
    public ArrayList<Intersection> getEmptyIntersection(){
        ArrayList<Intersection> emptyIntersection = new ArrayList<>();
        // Loop through the layers and positions to get the empty intersections
        for(int i = 1; i < MAX_LAYERS; i++){
            for(int j = 1; j < MAX_POSITIONS; j++){
                //check if intersection is empty
                if(intersection[i][j].isEmpty()){
                    //add empty intersection to the emptyIntersection list
                    emptyIntersection.add(intersection[i][j]);
                }
            }
        }
        return emptyIntersection;
    }

    /**
     * Function to get all the tokens' intersection by player on board
     *
     * @param player
     * @return list of all tokens by player on board
     */
    public ArrayList<Intersection> getPlayerTokensOnBoardIntersection(Player player){
        ArrayList<Intersection> tokensOnBoardIntersection = new ArrayList<>();
        // Loop through the layers and positions to get the tokens by player
        for(int i = 1; i < MAX_LAYERS; i++){
            for(int j = 1; j < MAX_POSITIONS; j++){
                //check if intersection is not empty and the token color is input player's token color
                if(!intersection[i][j].isEmpty() && intersection[i][j].getToken().getTokenColour() == player.getTokenColour()){
                    //add intersection to the tokensOnBoardIntersection list
                    tokensOnBoardIntersection.add(intersection[i][j]);
                }
            }
        }
        return tokensOnBoardIntersection;
    }

    /**
     * Function to place token on the empty intersection on board, add the token to the intersection
     *
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
     * Function to move token from one intersection to another, by removing the token from the origin intersection
     * and adding the token to the new intersection
     *
     * @param player = player who is moving the token
     * @param intersection = origin intersection of the token
     * @param otherIntersection = intersection where the token is moving to
     * @return true if token is moved successfully,
     *         false if the other intersection is occupied or not adjacent
     */
    public boolean moveToken(Player player, Intersection intersection, Intersection otherIntersection){
        // check if the other intersection is occupied and is adjacent to the origin intersection
        if (otherIntersection.isEmpty() && intersection.isAdjacent(otherIntersection)){
            //remove token from the origin intersection
            intersection.removeToken();
            //add token to the new intersection
            otherIntersection.addToken(new Token(player.getTokenColour()));
            return true;
        }
        return false;
    }

    /**
     * Function to fly token from one intersection to another, by removing the token from the origin intersection
     * and adding the token to the new intersection
     *
     * @param player = player who is moving the token
     * @param intersection = origin intersection of the token
     * @param otherIntersection = intersection where the token is moving to
     * @return true if token is moved successfully,
     *        false if the other intersection is occupied
     */
    public boolean flyToken(Player player, Intersection intersection, Intersection otherIntersection){
        // check if the other intersection is occupied
        if (otherIntersection.isEmpty()){
            //remove token from the origin intersection
            intersection.removeToken();
            //add token to the new intersection
            otherIntersection.addToken(new Token(player.getTokenColour()));
            return true;
        }
        return false;
    }

    /**
     * Function to remove token from the intersection, when there is mill formed
     *
     * @param player = player who is removing the token
     * @param intersection = intersection where the token is removed from
     * @return true if token is removed successfully,
     *        false if the intersection is occupied by the player's token or is part of a mill
     */
    public boolean removeToken(Player player, Intersection intersection){
        //loop through the mills to check if the intersection is part of a mill
        for (Mill mill : mills){
            //check if the intersection is part of a mill
            if (mill.getIntersection().contains(intersection)){
                return false;
            }
        }
        //check if the intersection is occupied
        if (!intersection.isEmpty()){
            //check if the intersection is occupied by the player's token
            if(intersection.getToken().getTokenColour() != player.getTokenColour()){
                //remove token from the intersection
                intersection.removeToken();
                return true;
            }
        }
        return false;
    }

    /**
     * Function to get adjacent intersection to create mills
     * The board is made up of 3 layers and 8 positions.
     *
     *            It looks like this:  1--------2--------3
     *                                 |  1     2     3  |
     *                                 |     1  2  3     |
     *                                 8  8  8     4  4  4
     *                                 |     7  6  5     |
     *                                 |   7    6     5  |
     *                                 7--------6--------5
     *
     * Considering few scenarios, where the positions of intersections are out of bound
     *      1. If the current intersection is 1, then currentIntersection-1 is <= 0, so we add 7 to
     *         get the adjacent intersection which is 8.
     *      2. If the current intersection is 8, then currentIntersection+1 is 9, so we subtract 7 to
     *         get the adjacent intersection which is 1.
     *      3. If the current intersection is 2, then currentIntersection-2 is <= 0, so we add 6 to get
     *         the adjacent intersection which is 8.
     *      4. If the current intersection is 7, then currentIntersection+2 is 9, so we subtract 6 to
     *         get the adjacent intersection which is 1.
     *
     * Consider the scenario for layer now,
     *      1. If the current intersection's position is odd, just add the adjacent intersection to the list
     *      2. If the current intersection's position is even, then check if the layer is 1, 2 or 3
     *         * mill can be formed vertically, with the same position on different layers
     *           -> if layer is 1 (outermost layer)
     *              - adding 1 and 2 to the current intersection's layer to take the adjacent intersection
     *           -> if layer is 2 (middle layer)
     *              - adding 1 and minus 1 to the current intersection's layer to take the adjacent intersection
     *           -> if layer is 3 (innermost layer)
     *              - minus 1 and 2 to the current intersection's layer to take the adjacent intersection
     *
     * @param currentIntersection = current intersection
     * @return ArrayList of adjacent intersections
     */
    public ArrayList<Intersection> getAdjacentIntersection(Intersection currentIntersection) {
        //variables to store adjacent intersections for further operations
        Intersection adjacent1 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent2 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent3 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent4 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        //clear the arraylist
        adjacentIntersection.clear();

        //check if the current intersection - 1 is out of position
        if (adjacent2.getPosition() - 1<= 0){
            //add 7 to the position to get the adjacent intersection
            int i = adjacent2.getPosition() + 7;
            adjacent2 = this.getIntersection(currentIntersection.getLayer(), i);
        }
        else{
            //if is not out of index, then do normal operation
            adjacent2 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() - 1);
        }
        //check if the current intersection - 2 is out of position
        if (adjacent4.getPosition() - 2 <= 0){
            //add 6 to the position to get the adjacent intersection
            int j = adjacent4.getPosition() + 6;
            adjacent4 = this.getIntersection(currentIntersection.getLayer(), j);
        }
        else{
            //if is not out of index, then do normal operation
            adjacent4 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() - 2);
        }
        //check if the current intersection + 2 is out of position
        if (adjacent3.getPosition() + 2 > 8){
            //minus 6 to the position to get the adjacent intersection
            int i = adjacent3.getPosition() - 6;
            adjacent3 = this.getIntersection(currentIntersection.getLayer(), i);
        }
        else{
            //if is not out of index, then do normal operation
            adjacent3 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() + 2);
        }
        //check if the current intersection + 1 is out of position
        if (adjacent1.getPosition() + 1> 8){
            //minus 7 to the position to get the adjacent intersection
            int j = adjacent1.getPosition() - 7;
            adjacent1 = this.getIntersection(currentIntersection.getLayer(), j);
        }
        else{
            //if is not out of index, then do normal operation
            adjacent1 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() + 1);
        }

        //check if the current intersection's position is odd
        if (currentIntersection.getPosition() % 2 == 1) {
            adjacentIntersection.add(adjacent1);
            adjacentIntersection.add(adjacent3);
            adjacentIntersection.add(adjacent4);
            adjacentIntersection.add(adjacent2);
        }
        else{
            //check if the current intersection's position is even and layer is 1
            if (currentIntersection.getLayer() == 1) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                //add 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 1][currentIntersection.getPosition()]);
                //add 2 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 2][currentIntersection.getPosition()]);
            }
            //check if the current intersection's position is even and layer is 2
            else if (currentIntersection.getLayer() == 2) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                //minus 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 1][currentIntersection.getPosition()]);
                //add 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 1][currentIntersection.getPosition()]);
            }
            ////add 7 to the position to get the adjacent intersection
            else if (currentIntersection.getLayer() == 3) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                //minus 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 1][currentIntersection.getPosition()]);
                //minus 2 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 2][currentIntersection.getPosition()]);
            }
        }
        //return the adjacent intersection of current intersection
        return adjacentIntersection;
    }

    /**
     * Function to get adjacent intersection for checking possible moves, such as move and fly token.
     * This function is used for drawing hints on the adjacent intersections based on the current intersection
     * for the player to move or fly token. Similar to function above, consider different cases for different
     * layers and positions.
     *
     * @param currentIntersection
     * @return ArrayList of adjacent intersections
     */
    public ArrayList<Intersection> getAdjacentIntersectionSmall(Intersection currentIntersection){
        //variables to store adjacent intersections for further operations
        Intersection adjacent1 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent2 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        //clear the arraylist
        adjacentIntersection.clear();

        //check if the current intersection + 1 is out of position
        if (adjacent1.getPosition() + 1> 8){
            //minus 7 to the position to get the adjacent intersection
            int j = adjacent1.getPosition() - 7;
            adjacent1 = this.getIntersection(currentIntersection.getLayer(), j);
        }
        else{
            //if is not out of index, then do normal operation
            adjacent1 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() + 1);
        }
        //check if the current intersection - 1 is out of position
        if (adjacent2.getPosition() - 1<= 0){
            //add 7 to the position to get the adjacent intersection
            int i = adjacent2.getPosition() + 7;
            adjacent2 = this.getIntersection(currentIntersection.getLayer(), i);
        }
        else{
            //if is not out of index, then do normal operation
            adjacent2 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() - 1);
        }

        //check if the current intersection's position is odd
        if (currentIntersection.getPosition() % 2 == 1) {
            adjacentIntersection.add(adjacent1);
            adjacentIntersection.add(adjacent2);
        }
        else{
            //check if the current intersection's position is even and layer is 1
            if (currentIntersection.getLayer() == 1) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                //add 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 1][currentIntersection.getPosition()]);
            }
            //check if the current intersection's position is even and layer is 2
            else if (currentIntersection.getLayer() == 2) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                //minus 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 1][currentIntersection.getPosition()]);
                //add 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 1][currentIntersection.getPosition()]);
            }
            //add 7 to the position to get the adjacent intersection
            else if (currentIntersection.getLayer() == 3) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                //minus 1 to the layer to get the adjacent intersection
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 1][currentIntersection.getPosition()]);
            }
        }
        //return the adjacent intersection of current intersection
        return adjacentIntersection;
    }

    /**
     * Function to check if the mill is formed after player has performed an action.
     * If the mill is formed, after the player has performed an action, add the Mill object to the mill arraylist.
     * then the player will be able to remove the opponent's token.
     *
     * @param player = current player
     * @param currentIntersection = current intersection
     * @return true if mill is formed, false if mill is not formed
     */
    public boolean isMill(Player player, Intersection currentIntersection){
        boolean flag = false;
        //check if the current intersection is not empty
        if (!currentIntersection.isEmpty()) {
            //check if the current intersection's can form a mill in the horizontal direction
            if (!this.getAdjacentIntersection(currentIntersection).get(0).isEmpty() && !this.getAdjacentIntersection(currentIntersection).get(1).isEmpty()){
                //check if the intersections needed to form mill are of same color
                if (currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(0).getToken().getTokenColour() &&
                        currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(1).getToken().getTokenColour()) {
                    //add the mill to the mill arraylist
                    mills.add(new Mill(currentIntersection, this.getAdjacentIntersection(currentIntersection).get(0), this.getAdjacentIntersection(currentIntersection).get(1), player));
                    //add the REMOVE_TOKEN ability to the player
                    player.addAllowableAction(AllActions.REMOVE_TOKEN);
                    flag = true;
                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
                }
            }
            //check if the current intersection's can form a mill in the vertical direction
            if (!this.getAdjacentIntersection(currentIntersection).get(2).isEmpty() && !this.getAdjacentIntersection(currentIntersection).get(3).isEmpty()){
                //check if the intersections needed to form mill are of same color
                if (currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(2).getToken().getTokenColour() &&
                        currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(3).getToken().getTokenColour()) {
                    //add the mill to the mill arraylist
                    mills.add(new Mill(currentIntersection, this.getAdjacentIntersection(currentIntersection).get(2), this.getAdjacentIntersection(currentIntersection).get(3), player));
                    //add the REMOVE_TOKEN ability to the player
                    player.addAllowableAction(AllActions.REMOVE_TOKEN);
                    flag = true;
                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
                }
            }
        }        System.out.println("Mills: "+ mills.toString());
        return flag;
    }
}