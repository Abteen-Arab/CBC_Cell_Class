import java.util.ArrayList; // import just the ArrayList class

public class Cell {

    //--------------------------------------------------
    //Instance Variables (Required)
    //--------------------------------------------------

    private int strength;
    private int x, y;
    private int id;

    //--------------------------------------------------
    //Instance Variables (Add-on)
    //--------------------------------------------------

    private boolean cellType;
    private boolean mitotic;
    private int hayFlickLimit;
    private int numDivisions;

    public Cell (){
        this(0,0,0,0);
    }

    public Cell (int strength, int x, int y, int id) {
        this.strength = strength;
        this.x = x;
        this.y = y;
        this.id= id;
    }

    //--------------------------------------------------
    //Mutator Methods (Required)
    //--------------------------------------------------

    public int getStrength() {
        return strength;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getID() {
        return id;
    }

    //--------------------------------------------------
    //Accessor Methods (Required)
    //--------------------------------------------------
    public void setStrength(int strength){
        this.strength = strength;
    }

    public void setX(int X){
        this.x = X;
    }

    public void setY(int Y){
        this.y = Y;
    }

    public void setID(int ID){
        this.id = ID;
    }

    //--------------------------------------------------
    //Required Method (not Required)
    //--------------------------------------------------

    public ArrayList<Cell>interactNeighbors (ArrayList<Cell> neighbors) {
        return null;
    }

    //--------------------------------------------------
    //Division Methods (not Required)
    //--------------------------------------------------

    public boolean setDivState (boolean CT, boolean mitStat, int hayLim, int numDiv){
        if ((numDiv >= hayLim && mitStat == true) || (numDiv <= hayLim && mitStat == false)) {
            return false;
        } else {
            this.cellType = CT;
            this.mitotic = mitStat;
            this.hayFlickLimit = hayLim;
            this.numDivisions = numDiv;
        }

        return true;
    }

    public void divOff (boolean CT, boolean mitStat, boolean senStat, int hayLim, int numDiv){
        this.setDivState(false,false,0,0);
    }

}