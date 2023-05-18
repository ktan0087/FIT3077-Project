package Backend.Board;

import Backend.Action.AllActions;
import Backend.Player;
import Backend.Token.Token;

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
    private ArrayList<Mill> mills = new ArrayList<>();
    private ArrayList<Intersection> adjacentIntersection = new ArrayList<>();
    private ArrayList<Intersection> adjacentIntersectionList = new ArrayList<>();

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

    public ArrayList<Mill> getMills() {
        return mills;
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
            //remove token from the origin intersection
            intersection.removeToken();
            //add token to the new intersection
            otherIntersection.addToken(new Token(player.getTokenColour()));
            return true;
        }
        return false;
    }

    public boolean flyToken(Player player, Intersection intersection, Intersection otherIntersection){
        // check if the other intersection is occupied
        if (otherIntersection.isEmpty()){
            //call loseTokenOnBoard() function from player
            //to reduce the number of tokens on board
//            player.loseTokenOnBoard();
            //remove token from the origin intersection
            intersection.removeToken();
            //add token to the new intersection
            otherIntersection.addToken(new Token(player.getTokenColour()));
            return true;
        }
        return false;
    }

    public boolean removeToken(Player player, Intersection intersection){
        for (Mill mill : mills){
            if (mill.getIntersection().contains(intersection)){
                return false;
            }
        }
        //check if the intersection is occupied
        if (!intersection.isEmpty()){
            if(intersection.getToken().getTokenColour() != player.getTokenColour()){
                intersection.removeToken();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Intersection> getAdjacentIntersection(Intersection currentIntersection) {
        Intersection adjacent1 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent2 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent3 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        Intersection adjacent4 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition());
        adjacentIntersection.clear();

        if (adjacent2.getPosition() - 1<= 0){
            int i = adjacent2.getPosition() + 7;
            adjacent2 = this.getIntersection(currentIntersection.getLayer(), i);
        }
        else{
            adjacent2 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() - 1);
        }
        if (adjacent4.getPosition() - 2 <= 0){
            int j = adjacent4.getPosition() + 6;
            adjacent4 = this.getIntersection(currentIntersection.getLayer(), j);
        }
        else{
            adjacent4 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() - 2);
        }
        if (adjacent3.getPosition() + 2 > 8){
            int i = adjacent3.getPosition() - 6;
            adjacent3 = this.getIntersection(currentIntersection.getLayer(), i);
        }
        else{
            adjacent3 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() + 2);
        }
        if (adjacent1.getPosition() + 1> 8){
            int j = adjacent1.getPosition() - 7;
            adjacent1 = this.getIntersection(currentIntersection.getLayer(), j);
        }
        else{
            adjacent1 = this.getIntersection(currentIntersection.getLayer(), currentIntersection.getPosition() + 1);
        }

        if (currentIntersection.getPosition() % 2 == 1) {
            adjacentIntersection.add(adjacent1);
            adjacentIntersection.add(adjacent3);
            adjacentIntersection.add(adjacent4);
            adjacentIntersection.add(adjacent2);
        }
        else{
            if (currentIntersection.getLayer() == 1) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 1][currentIntersection.getPosition()]);
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 2][currentIntersection.getPosition()]);
            }
            else if (currentIntersection.getLayer() == 2) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 1][currentIntersection.getPosition()]);
                adjacentIntersection.add(intersection[currentIntersection.getLayer() + 1][currentIntersection.getPosition()]);
            }
            else if (currentIntersection.getLayer() == 3) {
                adjacentIntersection.add(adjacent2);
                adjacentIntersection.add(adjacent1);
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 1][currentIntersection.getPosition()]);
                adjacentIntersection.add(intersection[currentIntersection.getLayer() - 2][currentIntersection.getPosition()]);
            }
        }
        return adjacentIntersection;
    }

    public boolean isMill(Player player, Intersection currentIntersection){
        boolean flag = false;
        if (!currentIntersection.isEmpty()) {
            if (!this.getAdjacentIntersection(currentIntersection).get(0).isEmpty() && !this.getAdjacentIntersection(currentIntersection).get(1).isEmpty()){
                if (currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(0).getToken().getTokenColour() &&
                        currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(1).getToken().getTokenColour()) {
                    mills.add(new Mill(currentIntersection, this.getAdjacentIntersection(currentIntersection).get(0), this.getAdjacentIntersection(currentIntersection).get(1), player));
                    player.addAllowableAction(AllActions.REMOVE_TOKEN);
                    flag = true;
                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
                }
            }
            if (!this.getAdjacentIntersection(currentIntersection).get(2).isEmpty() && !this.getAdjacentIntersection(currentIntersection).get(3).isEmpty()){
                if (currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(2).getToken().getTokenColour() &&
                        currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(3).getToken().getTokenColour()) {
                    mills.add(new Mill(currentIntersection, this.getAdjacentIntersection(currentIntersection).get(2), this.getAdjacentIntersection(currentIntersection).get(3), player));
                    player.addAllowableAction(AllActions.REMOVE_TOKEN);
                    flag = true;
                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
                }
            }
        }        System.out.println("Mills: "+ mills.toString());
        return flag;
    }



//    public boolean isMill(Player player, Intersection currentIntersection) {
//        boolean flag = false;
//        adjacentIntersectionList = this.getAdjacentIntersection(currentIntersection);
//
//        for (int i = 0; i < adjacentIntersectionList.size(); i+=2) {
//            if (!adjacentIntersectionList.get(i).isEmpty() && !adjacentIntersectionList.get(i+1).isEmpty()) {
//                if (currentIntersection.getToken().getTokenColour() == adjacentIntersectionList.get(i).getToken().getTokenColour() &&
//                        currentIntersection.getToken().getTokenColour() == adjacentIntersectionList.get(i+1).getToken().getTokenColour()) {
//                    mills.add(new Mill(currentIntersection, adjacentIntersectionList.get(i), adjacentIntersectionList.get(i+1), player));
//                    player.addAllowableAction(AllActions.REMOVE_TOKEN);
//                    flag = true;
//                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
//                }
//            }
//        }
//        System.out.println("Mills: "+ mills.toString());
//        return flag;
//    }

}
    //remove token from mill
    //remove mill object from mills arraylist
    //when the player move token from mill

//    /**
//     * Function to check if the intersection is working
//     */
//    public static void main(String[] args) {
//        Player p1 = new Player("KT", TokenColour.PLAYER_1_WHITE);
//        Player p2 = new Player("ZW", TokenColour.PLAYER_2_BLACK);
//        Game g = new Game();
//        Board b = new Board();
//
//        b.getAdjacentIntersection(b.intersection[1][5]);
//        PlaceTokenAction action1 = new PlaceTokenAction(p1, b.intersection[1][1], g);
//        System.out.println(action1.execute());
//
//        //b.placeToken(p1, b.intersection[1][1]);
//        System.out.println(b.intersection[1][1].toString());
//        b.placeToken(p2, b.intersection[1][2]);
//        b.placeToken(p1, b.intersection[1][3]);
//        System.out.println(b.intersection[1][1].isEmpty());
//        System.out.println((b.intersection[1][1].getToken().getTokenColour()));
//        System.out.println((b.intersection[1][2].getToken().getTokenColour()));


