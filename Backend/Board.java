package Backend;

import java.util.ArrayList;
import Backend.TokenColour;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public class Board {

    public static final int MAX_LAYERS = 4;
    public static final int MAX_POSITIONS = 9;
    private Intersection[][] intersection = new Intersection[MAX_LAYERS][MAX_POSITIONS];

    ArrayList<Intersection[][]> intersectionList=new ArrayList<>();

    //constructor
    public Board (){
        //Initialise intersection
        for(int i = 1; i < MAX_LAYERS; i++){
            for(int j = 1; j < MAX_POSITIONS; j++){
                //intersectionList.add(Intersection[i][j]);
                intersection[i][j] = makeNewIntersection(i, j);
            }
        }
        //System.out.println(intersection[3][8].getPosition());
    }

    public Intersection getIntersection(int layer, int position){
        return intersection[layer][position];
    }

    public Intersection makeNewIntersection(int layer, int position){
        return new Intersection(layer, position);
    }

    public void placeToken(Player player, Intersection intersection){
        if (intersection.isEmpty()){
            player.placeTokenOnBoard();
            intersection.addToken(new Token(player.getTokenColour()));
        }
    }

    public void moveToken(Player player, Intersection intersection, Intersection otherIntersection){
        if (otherIntersection.isEmpty() && intersection.isAdjacent(otherIntersection)){
            player.loseTokenOnBoard();
            intersection.removeToken();
        }
    }

    public static void main(String[] args) {
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
    }

    public static void resetBoard(){}
    // call isGameOver method to check
    // if true, end game and create new game
}
