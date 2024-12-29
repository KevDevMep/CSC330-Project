import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Inventory
 */
public class Inventory implements Loggable, UserAccessible
{
    protected HashMap<String,Data> inventory;
    public Inventory() {inventory = new HashMap<>();}
    public boolean inInventory(String item) {return inventory.containsKey(item);}
    public void changeStock(String item,int size) {inventory.get(item).setStock(size);}
    public void addItem(String item, Data d) {inventory.put(item, d);}
    public void removeItem(String item) {inventory.remove(item);}
    public void clear() {inventory.clear();}
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
    public void print() {
        for (Map.Entry<String,Data> entry : inventory.entrySet()) {
            System.out.print(entry.getKey() + " = ");
            entry.getValue().print();
        }
    }
    public int size() {return inventory.size();}
    public void addIn(String item, int size) {inventory.get(item).inc(size);}
    public void takeOut(String item, int size) {inventory.get(item).dec(size);}
    @Override
    public boolean hasAccess(String user) {
        if (user.equals("1")) return true;
        return false;
    }
    @Override
    public void log(String message) {
        //FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        try {
            //fileStream = new FileOutputStream("log.txt");
            outFS = new PrintWriter("log.txt");
            outFS.write(message);
            outFS.close();
            //fileStream.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }

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

    public void save(String fileName) {
        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        try {
            fileStream = new FileOutputStream(fileName);
            outFS = new PrintWriter(fileName);
            for (Map.Entry<String,Data> entry : inventory.entrySet()) {
            outFS.print(entry.getKey() + " ");
            outFS.println(entry.getValue());
        }
        outFS.close();
        fileStream.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    public HashMap<String, Data> getInventory() {return inventory;}
}