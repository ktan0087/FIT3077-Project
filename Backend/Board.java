package Backend;

public class Board {

    public static final int MAX_LAYERS = 3;
    public static final int MAX_POSITIONS = 8;
    private Intersection[][] intersection = new Intersection[MAX_LAYERS][MAX_POSITIONS];


    public static void resetBoard(){}
    // call isGameOver method to check
    // if true, end game and create new game
}
