/**
 * Class to update/change the items in inventory
 */

public class Data {
    String location;
    int stock;
    int in;
    int out;
    int inRate;
    int outRate;
    int baseLine;
    int lowerBound;
    int upperBound;

    /**
     * Parameterized constructor
     * @param location location of item
     * @param stock stock of item
     * @param in item amount in inventory
     * @param out item amount taken out inventory
     * @param inRate the inRate of item
     * @param outRate the outRate of item
     * @param baseLine baseLine of item
     * @param lowerBound the lowerBound of item
     * @param upperBound the upperBound of item
     */
    public Data(String location, int stock, int in, int out, int inRate, int outRate, int baseLine, int lowerBound, int upperBound) {
        this.location = location;
        this.stock = stock;
        this.in = in;
        this.out = out;
        this.inRate = inRate;
        this.outRate = outRate;
        this.baseLine = baseLine;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    /**
     * Default constructor
     */
    public Data() {
        this.location = "";
        this.stock = 0;
        this.in = 0;
        this.out = 0;
        this.inRate = 0;
        this.outRate = 0;
        this.baseLine = 0;
        this.lowerBound = 0;
        this.upperBound = 1;
    }

    /**
     * Checks if item is in stock
     * @return true if items is in stock else false
     */
    public boolean inStock() {return stock != 0;}

    /**
     * Updates the stock based on the inRate and outRate
     */
    public void tick() {
        inc(inRate);
        dec(outRate);
    }

    /**
     * Returns a string concatenation of item data
     * @return the values of stock, in, out, inRate, outRate, baseLine, lowerBound, upperBound
     */
    public String toString() {
        return(location + " " + stock + " " + in + " " + out + " " + inRate + " " + outRate  + " " +  baseLine  + " " + lowerBound  + " " +  upperBound);
    }

    /**
     * Print the stock, difference in rates, in, out, inRate, outRate, baseLine, lowerBound, and upperBound
     */
    public void print() {
        System.out.println("Stock: " + stock + ", diffRate: " + (inRate - outRate) + ", location: " + location + ", in: " + in + ", out: " + out + ", inRate: " + inRate + ", outRate " + outRate  + ", baseLine: " +  baseLine  + ", lowerBound: " + lowerBound  + ", upperBound: " +  upperBound);
    }

    /**
     * Increments the quantity of stock and in
     * @param size quantity to add to item in and stock
     */
    public void inc(int size) {
        in += size;
        stock += size;
    }

    /**
     * Decrements the quantity of stock and in
     * @param size quantity to remove to item in and stock
     */
    public void dec(int size) {
        out += size;
        stock -= size;
    }

    /**
     * Returns true if stock is within bounds from upperBound and lowerBound
     * @return true if stock is less than upperBound and greater than lowerBound else false
     */
    public boolean inBounds() {return stock < upperBound && stock > lowerBound;}

    /**
     * Increases and decreases the stock to meet baseLine,
     * if stock is greater than baseLine decrement by the
     * difference in stock and baseLine; else increment.
     */
    public void level() {
        if (stock > baseLine) dec(stock - baseLine);
        else inc(baseLine - stock);
    }

    /**
     * @return stock
     */
    public int getStock() {return stock;}

    /**
     * @return in
     */
    public int getIn() {return in;}

    /**
     * @return out
     */
    public int getOut() {return out;}

    /**
     * @return baseLine
     */
    public int getBaseLine() {return baseLine;}

    /**
     * @return inRate
     */
    public int getInRate() {return inRate;}

    /**
     * @return Location
     */
    public String getLocation() {return location;}

    /**
     * @return lowerBound
     */
    public int getLowerBound() {return lowerBound;}

    /**
     * @return outRate
     */
    public int getOutRate() {return outRate;}

    /**
     * @return upperBound
     */
    public int getUpperBound() {return upperBound;}

    /**
     * @param stock stock to set
     */
    public void setStock(int stock) {this.stock = stock;}

    /**
     * @param baseLine baseLine to set
     */
    public void setBaseLine(int baseLine) {this.baseLine = baseLine;}

    /**
     * @param in in to set
     * @param out out to set
     */
    public void setStats(int in, int out) {
        this.in = in;
        this.out = out;
    }

    /**
     * Resets the tracking of in and out
     */
    public void resetStats() {
        in = 0;
        out = 0;
    }

    /**
     * @param inRate inRate to set
     */
    public void setInRate(int inRate) {this.inRate = inRate;}

    /**
     * @param location location to set
     */
    public void setLocation(String location) {this.location = location;}

    /**
     * @param lowerBound lowerBound to set
     */
    public void setLowerBound(int lowerBound) {this.lowerBound = lowerBound;}

    /**
     * @param outRate outRate to set
     */
    public void setOutRate(int outRate) {this.outRate = outRate;}

    /**
     * @param upperBound upperBound to set
     */
    public void setUpperBound(int upperBound) {this.upperBound = upperBound;}
}