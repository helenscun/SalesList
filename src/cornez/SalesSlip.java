package cornez;

import java.util.ArrayList;
import java.util.List;

/**
 * SalesSlip
 * Models the entire sales list (the "slip") as a collection of
 * SalesItem objects. Backed by an ArrayList so items can be added
 * dynamically as the user enters them. Provides a method to compute
 * total sales across every item on the slip, and a toString() that
 * builds the full, multi-line text of the sales list.
 */
public class SalesSlip {

    private List<SalesItem> items;

    /**
     * Creates a new, empty SalesSlip.
     */
    public SalesSlip() {
        items = new ArrayList<>();
    }

    /**
     * Adds a new item to the sales slip.
     * @param item the SalesItem to add
     */
    public void addItem(SalesItem item) {
        items.add(item);
    }

    /**
     * Convenience overload: builds a SalesItem from its parts and
     * adds it to the slip.
     * @param name     item name
     * @param price    item unit price
     * @param quantity item quantity
     */
    public void addItem(String name, double price, int quantity) {
        items.add(new SalesItem(name, price, quantity));
    }

    /**
     * @return the list of SalesItem objects currently on the slip
     */
    public List<SalesItem> getItems() {
        return items;
    }

    /**
     * @return the number of items currently on the slip
     */
    public int getItemCount() {
        return items.size();
    }

    /**
     * Computes the total sales for every item currently on the slip.
     * @return the sum of each item's price * quantity
     */
    public double computeTotalSales() {
        double total = 0.0;
        for (SalesItem item : items) {
            total += item.calculateItemTotal();
        }
        return total;
    }

    /**
     * Removes every item from the slip and resets it to empty.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Builds the complete, multi-line text representation of the
     * sales list, one formatted line per item.
     * @return the full sales list as a string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (SalesItem item : items) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }
}

