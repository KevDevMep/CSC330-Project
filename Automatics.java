import java.util.Map;

/**
 * Class to perform automations on inventory
 */

public class Automatics extends Inventory
{
    int maxCapacity;

    /**
     * Parameterized constructor
     * @param maxCapacity max capacity of inventory
     */
    Automatics(int maxCapacity) {
        super();
        this.maxCapacity = maxCapacity;
    }

    /**
     * Parameterized constructor
     * @param inv other inventory
     * @param maxCapacity max capacity of inventory
     */
    Automatics(Inventory inv, int maxCapacity) {
        this.inventory = inv.inventory;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Default constructor
     */
    Automatics() {
        super();
        this.maxCapacity = 0;
    }

    /**
     * @param maxCapacity maxCapacity to set
     */
    public void setMaxCapacity(int maxCapacity) {this.maxCapacity = maxCapacity;}

    /**
     * @return maxCapacity
     */
    public int getMaxCapacity() {return maxCapacity;}

    /**
     * Checks to see if the total stock in inventory exceeds the maxCapacity,
     * if it does return false.
     * @return false if total stock in inventory is greater than maxCapacity else true
     */
    public Boolean inCapacity() {
        int totalStock = 0;
        for (Map.Entry<String,Data> entry : inventory.entrySet()) totalStock += entry.getValue().getStock();
        if (totalStock > maxCapacity) return false;
        return true;
    }

    /**
     * Iterates the items in inventory and invokes the level method
     * @see Data#level()
     */
    public void levelOff() {
        for (Map.Entry<String,Data> entry : inventory.entrySet()) entry.getValue().level();
    }

    /**
     * Iterates over a specified amount of time(s), for all items
     * in inventory, if the total stock of the inventory exceeds
     * maxCapacity, invoke levelOff(). If total stock is in capacity
     * check if stock of item is within bounds, if not, invoke level().
     * Updates the stock levels with tick()
     * @param time number of time(s) for the automation
     * @see #levelOff()
     * @see Data#inBounds()
     * @see Data#level()
     * @see Data#tick()
     */
    public void simulate(int time) {
        for (int i = 0; i < time; i++) {
            if (!inCapacity()) levelOff();
            for (Map.Entry<String,Data> entry : inventory.entrySet()) {
                if (!entry.getValue().inBounds()) entry.getValue().level();
                entry.getValue().tick();
            }
        }
    }
}