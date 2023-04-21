package Backend;

public class Intersection {

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
     * @return
     */
    public int getLayer() {
        return layer;
    }

    /**
     * Getter function to get the position of Intersection
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * Function to check if the two intersection is next to each other
     * @param other = Intersection used to compared to current intersection
     * @return true if the Intersections are next to each other
     */
    public boolean isAdjacent(Intersection other){
        boolean flag = false;
        int dx = Math.abs(this.layer - other.layer);
        int dy = Math.abs(this.position - other.position);

        if (dx+dy == 1 || dx+dy == 7){
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
     */
    public boolean isEmpty(){
        boolean flag = true;

        if (this.token != null){
            flag = false;
        }
        return flag;
    }
}
