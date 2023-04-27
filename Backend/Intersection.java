package Backend;

public class Intersection {
    /**
     * Private attributes of Intersection
     */
    private int layer;
    private int position;
    private Token token = null;

    /**
     * Constructor
     * Function to initialise Intersection
     * @param layer = x-coordinate of Intersection
     * @param pos = y-coordinate of Intersection
     */
    public Intersection(int layer, int pos){
        this.layer = layer;
        this.position = pos;
    }

    /**
     * Getter function to get the layer of the Intersection
     * @return current layer
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Getter function to get the position of Intersection
     * @return current position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Function to set token on the intersection
     * @param token
     */
    public void addToken(Token token){
        this.token = token;
    }

    /**
     * Function to remove token from intersection
     */
    public void removeToken(){
        this.token = null;
    }

    /**
     * Getter function to get the token on that intersection
     * @return token on the intersection
     */
    public Token getToken() {
        return token;
    }

    /**
     * Function to check if the two intersection is next to each other
     * @param other = Intersection used to compared to current intersection
     * @return true if the Intersections are adjacent to the other intersection
     *         false if the Intersections are not adjacent to the other intersection
     */
    public boolean isAdjacent(Intersection other){
        boolean flag = false;
        //get the difference between the layer and position of the two intersections
        int dx = Math.abs(this.layer - other.layer);
        int dy = Math.abs(this.position - other.position);
        //check if the difference is 1 or 7
        if (dx+dy == 1 || dx+dy == 7) {
            flag = true;
        }
        else if (dx != 0 && position % 2 == 1){
            if (this.position == other.position)
                flag = true;
        }
        return flag;
    }

    /**
     * Function that check if the intersection is empty
     * @return true if intersection is empty
     *         false if the intersection is not empty
     */
    public boolean isEmpty(){
        boolean flag = true;
        //check if the intersection has a token
        if (this.token != null){
            flag = false;
        }
        return flag;
    }
}
