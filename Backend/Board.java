package Backend;

import java.util.ArrayList;
import java.util.Arrays;

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
            player.loseTokenOnBoard();
            //remove token from the origin intersection
            intersection.removeToken();
            //add token to the new intersection
            otherIntersection.addToken(new Token(player.getTokenColour()));
            return true;
        }
        return false;
    }

    public boolean removeToken(Player player, Intersection intersection){
        //check if the intersection is occupied
        if (!intersection.isEmpty()){
            intersection.removeToken();
            player.loseTokenOnBoard();
            return true;
        }
        return false;
    }

    public ArrayList<Intersection> getAdjacentIntersection(Intersection currentIntersection) {
        //Intersection adjacent1 = this.intersection[currentIntersection.getLayer()][currentIntersection.getLayer()];
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
        System.out.println("New" + adjacentIntersection.toString() + "\n");
        return adjacentIntersection;
    }

    public void isMill(Player player, Intersection currentIntersection){
        if (!currentIntersection.isEmpty()) {
            if (!this.getAdjacentIntersection(currentIntersection).get(0).isEmpty() && !this.getAdjacentIntersection(currentIntersection).get(1).isEmpty()){
                if (currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(0).getToken().getTokenColour() &&
                        currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(1).getToken().getTokenColour()) {
                    mills.add(new Mill(currentIntersection, this.getAdjacentIntersection(currentIntersection).get(0), this.getAdjacentIntersection(currentIntersection).get(1), player));
                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
                }
            }
            if (!this.getAdjacentIntersection(currentIntersection).get(2).isEmpty() && !this.getAdjacentIntersection(currentIntersection).get(3).isEmpty()){
                if (currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(2).getToken().getTokenColour() &&
                        currentIntersection.getToken().getTokenColour() == this.getAdjacentIntersection(currentIntersection).get(3).getToken().getTokenColour()) {
                    mills.add(new Mill(currentIntersection, this.getAdjacentIntersection(currentIntersection).get(2), this.getAdjacentIntersection(currentIntersection).get(3), player));
                    System.out.println("----------------------------"+ player.getName() + " Mill formed");
                }
            }
        }
        System.out.println("Mills: "+ mills.toString());
    }

    //remove token from mill
    //remove mill object from mills arraylist
    //when the player move token from mill

    /**
     * Function to check if the intersection is working
     */
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
    }

    //    public void createMill(){
//        mills.add(0, new Mill(intersection[1][1], intersection[1][2], intersection[1][3]));
//        mills.add(1, new Mill(intersection[1][3], intersection[1][4], intersection[1][5]));
//        mills.add(2, new Mill(intersection[1][5], intersection[1][6], intersection[1][7]));
//        mills.add(3, new Mill(intersection[1][7], intersection[1][8], intersection[1][1]));
//        mills.add(4, new Mill(intersection[2][1], intersection[2][2], intersection[2][3]));
//        mills.add(5, new Mill(intersection[2][3], intersection[2][4], intersection[2][5]));
//        mills.add(6, new Mill(intersection[2][5], intersection[2][6], intersection[2][7]));
//        mills.add(7, new Mill(intersection[2][7], intersection[2][8], intersection[2][1]));
//        mills.add(8, new Mill(intersection[3][1], intersection[3][2], intersection[3][3]));
//        mills.add(9, new Mill(intersection[3][3], intersection[3][4], intersection[3][5]));
//        mills.add(10, new Mill(intersection[3][5], intersection[3][6], intersection[3][7]));
//        mills.add(11, new Mill(intersection[3][7], intersection[3][8], intersection[3][1]));
//        mills.add(12, new Mill(intersection[1][2], intersection[2][2], intersection[3][2]));
//        mills.add(13, new Mill(intersection[1][4], intersection[2][4], intersection[3][4]));
//        mills.add(14, new Mill(intersection[1][6], intersection[2][6], intersection[3][6]));
//        mills.add(15, new Mill(intersection[1][8], intersection[2][8], intersection[3][8]));
//    }

//    public void isMill(Player player, Intersection currentIntersection) {
//        for (Mill mill : mills) {
//            if (mill.getIntersection().contains(currentIntersection)) {
//                if (mill.getIntersection().indexOf(currentIntersection) == 0) {
//                    if (!mill.getIntersection().get(1).isEmpty() && !mill.getIntersection().get(2).isEmpty()) {
//                        if (currentIntersection.getToken().getTokenColour() == mill.getIntersection().get(1).getToken().getTokenColour() &&
//                                currentIntersection.getToken().getTokenColour() == mill.getIntersection().get(2).getToken().getTokenColour()) {
//                            System.out.println(player.getName() + " Mill formed");
//                        }
//                    }
//                }
//                else if (mill.getIntersection().indexOf(currentIntersection) == 1){
//                    if (!mill.getIntersection().get(0).isEmpty() && !mill.getIntersection().get(2).isEmpty()) {
//                        if (currentIntersection.getToken().getTokenColour() == mill.getIntersection().get(0).getToken().getTokenColour() &&
//                                currentIntersection.getToken().getTokenColour() == mill.getIntersection().get(2).getToken().getTokenColour()) {
//                            System.out.println(player.getName() +" Mill formed");
//                        }
//                    }
//
//                }
//                else if (mill.getIntersection().indexOf(currentIntersection) == 2) {
//                    if (!mill.getIntersection().get(0).isEmpty() && !mill.getIntersection().get(1).isEmpty()) {
//                        if (currentIntersection.getToken().getTokenColour() == mill.getIntersection().get(0).getToken().getTokenColour() &&
//                                currentIntersection.getToken().getTokenColour() == mill.getIntersection().get(1).getToken().getTokenColour()) {
//                            System.out.println(player.getName() +" Mill formed");
//                        }
//                    }
//                }
//            }
//        }
//    }

