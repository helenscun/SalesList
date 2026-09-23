package cornez;

/**
 * SalesItem
 * Models a single item on the sales list: its name, unit price, and
 * quantity purchased. Provides getters/setters and a calculation
 * method for the item's subtotal (price * quantity), plus a
 * toString() that formats the item the way it is displayed on the
 * sales list (name, price, quantity).
 */
public class SalesItem {

    private String name;
    private double price;
    private int quantity;

    /**
     * Creates a new SalesItem.
     * @param name     the name of the item
     * @param price    the unit price of the item (assumed less than $100)
     * @param quantity the quantity purchased
     */
    public SalesItem(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // ----- Getters -----

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // ----- Setters -----

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Calculates the subtotal for this item: price * quantity.
     * @return the item's subtotal
     */
    public double calculateItemTotal() {
        return price * quantity;
    }

    /**
     * Builds a formatted string representing this item, matching the
     * layout used in the sales list display:
     * name (left-aligned), price (as currency), quantity.
     * @return the formatted item string
     */
    @Override
    public String toString() {
        return String.format("%-16s$%6.2f  %4d", name, price, quantity);
    }
}

