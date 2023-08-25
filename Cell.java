import java.util.ArrayList; // import just the ArrayList class
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

    private boolean mitotic;
    private int hayFlickLimit;
    private int numDivisions;

    //--------------------------------------------------
    //Instance Variables: Growth "Factors"
    //--------------------------------------------------
    private  HashMap<String,Double> resources = new HashMap<String , Double>();
    private  HashMap<String,Double> energy = new HashMap<String , Double>();

    //--------------------------------------------------
    //Signal Molecules
    //--------------------------------------------------


    private HashSet<Boolean> gFac = new HashSet<Boolean>();

    public Cell (){
        this(0,0,0,0);
    }

    public Cell (int strength, int x, int y, int id) {
        this.strength = strength;
        this.x = x;
        this.y = y;
        this.id= id;

        resources.put("Phosphorous", 1.0);
        resources.put("Nitrogen", 1.0);
        resources.put("Oxygen", 1.0);

        energy.put("glucose", 1.0);
        energy.put("ATP", 1.0);
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

    public ArrayList<Cell> interactNeighbors (ArrayList<Cell> neighbors) {
        return null;
    }

    //--------------------------------------------------
    //Energy Methods
    //--------------------------------------------------

    private double energyCheck() {
        return energy.get("ATP");
    }

    private boolean makeEnergy(int numATP) {
        double ATP = energyCheck();
        double ox = resources.get("Oxygen");
        double phos = resources.get("Phosphorous");
        if (energy.get("glucose")>0.1 && phos - 0.1 *numATP >  0 && ox - 0.1*numATP > 0 && ATP<1-0.1*numATP){
            energy.replace("ATP", ATP +  0.1*numATP);
            resources.replace("Phosphorous", phos -  0.1*numATP);
            resources.replace("Oxygen", phos -  0.1*numATP);
            return true;
        } else {
            return false;
        }
    }

    private boolean useEnergy(double enUsed) {
        double ATP = energyCheck();
        if (ATP-enUsed>0){
            energy.replace("ATP", ATP -  enUsed);
            return true;
        } else {
            return false;
        }
    }

    //--------------------------------------------------
    //Division Methods (not Required)
    //--------------------------------------------------

    public boolean setDivState (boolean mitStat, int hayLim, int numDiv){

        if ((numDiv >= hayLim && mitStat == true) || (numDiv <= hayLim && mitStat == false)) {
            return false;
        } else {
            this.mitotic = mitStat;
            this.hayFlickLimit = hayLim;
            this.numDivisions = numDiv;
        }

        return true;
    }

    public void divOff (boolean mitStat, boolean senStat, int hayLim, int numDiv){
        this.setDivState(false,10,0);
    }

    public void recognizeSignal(Boolean signal) {
        gFac.add(signal);
    }

    public Boolean divideTime (){
        int gFacPos = 0;
        if (numDivisions<hayFlickLimit && mitotic && this.energyCheck() > 0.5){
            for (Boolean i: gFac){
                if (i) gFacPos++;
            }
            if (gFacPos > gFac.size()) {
                System.out.println("Dividing....");
                //growth action
            }
            return true;
        } else {
            return false;
        }
    }

}