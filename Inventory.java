import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class to manage inventory
 */
public class Inventory implements Loggable, UserAccessible
{
    protected HashMap<String,Data> inventory;

    /**
     * Constructor for a new Inventory object
     */
    public Inventory() {inventory = new HashMap<>();}

    /**
     * Returns true if items is in inventory
     * @param item name of item to search
     * @return true if item is in inventory else false
     */
    public boolean inInventory(String item) {return inventory.containsKey(item);}

    /**
     * Changes the stock of a given item
     * @param item name of item to change stock
     * @param size change in stock size
     */
    public void changeStock(String item,int size) {inventory.get(item).setStock(size);}

    /**
     * Adds item and item data into inventory
     * @param item name of item to add
     * @param d data of the given item
     */
    public void addItem(String item, Data d) {inventory.put(item, d);}

    /**
     * Removes item from inventory
     * @param item name of items to remove
     */
    public void removeItem(String item) {inventory.remove(item);}

    /**
     * Removes all items in inventory
     */
    public void clear() {inventory.clear();}

    /**
     * Calculates and prints out the total stock, total stock added, and total stock removed and prints out the stats of inventory
     */
    public void stats() {
        int tStock = 0, tIn = 0, tOut = 0, tInRate = 0, tOutRate = 0, tBaseLine = 0, tLowerBounds = 0, tUpperBounds = 0, size = inventory.size();
        for (Map.Entry<String,Data> entry : inventory.entrySet()) {
            tStock += entry.getValue().getStock();
            tIn += entry.getValue().getIn();
            tOut += entry.getValue().getOut();
            tInRate += entry.getValue().getInRate();
            tOutRate += entry.getValue().getOutRate();
            tBaseLine += entry.getValue().getBaseLine();
            tLowerBounds += entry.getValue().getLowerBound();
            tUpperBounds += entry.getValue().getUpperBound();
        }
        System.out.println("Size: " + size);
        System.out.println("Totals:");
        System.out.println("Stock: " + tStock + 
        ", diffRate: " + (tInRate - tOutRate) + 
        ", in: " + tIn + 
        ", out: " + tOut + 
        ", inRate: " + tInRate + 
        ", outRate " + tOutRate  + 
        ", baseLine: " +  tBaseLine  + 
        ", lowerBound: " + tLowerBounds  + 
        ", upperBound: " +  tUpperBounds);
        System.out.println("Averages: ");
        System.out.println("Stock: " + tStock/size + 
        ", diffRate: " + (tInRate/size - tOutRate/size) + 
        ", in: " + tIn/size + 
        ", out: " + tOut/size + 
        ", inRate: " + tInRate/size + 
        ", outRate " + tOutRate/size  + 
        ", baseLine: " +  tBaseLine/size  + 
        ", lowerBound: " + tLowerBounds/size  + 
        ", upperBound: " +  tUpperBounds/size);

    }

    /**
     * Prints out inventory, which includes item name and item data
     */
    public void print() {
        for (Map.Entry<String,Data> entry : inventory.entrySet()) {
            System.out.print(entry.getKey() + " = ");
            entry.getValue().print();
        }
    }

    /**
     * Returns the size of inventory
     * @return size of inventory
     */
    public int size() {return inventory.size();}

    /**
     * Increments the quantity of item in inventory
     * @param item name of item to add
     * @param size increment the quantity
     */

    public void addIn(String item, int size) {inventory.get(item).inc(size);}

    /**
     * Decrements the quantity of item in inventory
     * @param item name of item to remove
     * @param size decrement the quantity
     */
    public void takeOut(String item, int size) {inventory.get(item).dec(size);}

    /**
     * Returns true or false based on specified user access
     * @param user check the user access
     * @return if user has access true else false
     */
    @Override
    public boolean hasAccess(String user) {
        if (user.equals("1")) return true;
        return false;
    }

    /**
     * Logs the message to a file, "log.txt"
     * @param message string to be logged
     */
    @Override
    public void log(String message) {
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        try {
            fileStream = new FileOutputStream("log.txt");
            outFS = new PrintWriter("log.txt");
            outFS.println(message);
            outFS.close();
            fileStream.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * Reads from a given file and fills the inventory object
     * with data of that file
     * @param fileName name of file to load data from
     */
    public void load(String fileName) {
        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        String name, location;
        int stock, in, out, inRate, outRate, baseLine, lowerBound, upperBound;
        try {
            fileByteStream = new FileInputStream(fileName);
            inFS = new Scanner(fileByteStream);
            while (inFS.hasNext()) {
                name = inFS.next();
                location = inFS.next();
                stock = inFS.nextInt();
                in = inFS.nextInt();
                out = inFS.nextInt();
                inRate = inFS.nextInt();
                outRate = inFS.nextInt();
                baseLine = inFS.nextInt();
                lowerBound = inFS.nextInt();
                upperBound = inFS.nextInt();
                Data d = new Data(location, stock, in, out, inRate, outRate, baseLine, lowerBound, upperBound);
                inventory.put(name, d);
            }
            inFS.close();
            fileByteStream.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * Writes the contents in inventory object into
     * a given file
     * @param fileName name of file to save data into
     */
    public void save(String fileName) {
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        try {
            fileStream = new FileOutputStream(fileName);
            outFS = new PrintWriter(fileName);
            for (Map.Entry<String,Data> entry : inventory.entrySet()) {
                outFS.println(entry.getValue());
            }
            outFS.close();
            fileStream.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    /**
     * @return inventory
     */
    public HashMap<String, Data> getInventory() {return inventory;}
}