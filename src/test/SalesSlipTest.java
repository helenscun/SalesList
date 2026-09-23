package test;

import cornez.SalesItem;
import cornez.SalesSlip;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SalesSlipTest
 * Unit tests for SalesItem and SalesSlip, covering item construction,
 * getters/setters, per-item subtotal calculation, adding items to a
 * slip, computing total sales across multiple items, and the
 * toString() output used for display.
 */
public class SalesSlipTest {

    private SalesSlip slip;

    @BeforeEach
    public void setUp() {
        slip = new SalesSlip();
    }

    // ----- SalesItem tests -----

    @Test
    public void testSalesItemConstructorAndGetters() {
        SalesItem item = new SalesItem("Grimace Shake", 4.59, 1);
        assertEquals("Grimace Shake", item.getName());
        assertEquals(4.59, item.getPrice(), 0.001);
        assertEquals(1, item.getQuantity());
    }

    @Test
    public void testSalesItemSetters() {
        SalesItem item = new SalesItem("Grimace Shake", 4.59, 1);
        item.setName("Spicy Bowl");
        item.setPrice(16.00);
        item.setQuantity(2);
        assertEquals("Spicy Bowl", item.getName());
        assertEquals(16.00, item.getPrice(), 0.001);
        assertEquals(2, item.getQuantity());
    }

    @Test
    public void testCalculateItemTotal() {
        SalesItem item = new SalesItem("Half Pound Roast Beef Arby's", 9.79, 3);
        assertEquals(29.37, item.calculateItemTotal(), 0.001);
    }

    @Test
    public void testCalculateItemTotalWithZeroQuantity() {
        SalesItem item = new SalesItem("Empty", 5.00, 0);
        assertEquals(0.0, item.calculateItemTotal(), 0.001);
    }

    @Test
    public void testSalesItemToStringContainsFields() {
        SalesItem item = new SalesItem("Kourtney Flourless Chocolate Cake", 6.59, 1);
        String result = item.toString();
        assertTrue(result.contains("Kourtney Flourless Chocolate Cake"));
        assertTrue(result.contains("6.59"));
        assertTrue(result.contains("1"));
    }

    // ----- SalesSlip tests -----

    @Test
    public void testNewSlipIsEmpty() {
        assertEquals(0, slip.getItemCount());
        assertEquals(0.0, slip.computeTotalSales(), 0.001);
    }

    @Test
    public void testAddSingleItem() {
        slip.addItem(new SalesItem("Labubu Matcha Dubai Chocolate", 15.99, 1));
        assertEquals(1, slip.getItemCount());
        assertEquals(15.99, slip.computeTotalSales(), 0.001);
    }

    @Test
    public void testAddItemConvenienceOverload() {
        slip.addItem("Spicy Bowl", 16.00, 1);
        assertEquals(1, slip.getItemCount());
        assertEquals(16.00, slip.computeTotalSales(), 0.001);
    }

    @Test
    public void testComputeTotalSalesAcrossMultipleItems() {
        slip.addItem(new SalesItem("Labubu Matcha Dubai Chocolate", 15.99, 1));
        slip.addItem(new SalesItem("Half Pound Roast Beef Arby's", 9.79, 1));
        slip.addItem(new SalesItem("Kourtney Flourless Chocolate Cake", 6.59, 1));
        slip.addItem(new SalesItem("Grimace Shake", 4.59, 1));
        slip.addItem(new SalesItem("Spicy Bowl", 16.00, 1));

        // 15.99 + 9.79 + 6.59 + 4.59 + 16.00 = 52.96
        assertEquals(52.96, slip.computeTotalSales(), 0.001);
        assertEquals(5, slip.getItemCount());
    }

    @Test
    public void testClearRemovesAllItems() {
        slip.addItem(new SalesItem("Grimace Shake", 4.59, 1));
        slip.addItem(new SalesItem("Spicy Bowl", 16.00, 1));
        slip.clear();
        assertEquals(0, slip.getItemCount());
        assertEquals(0.0, slip.computeTotalSales(), 0.001);
    }

    @Test
    public void testSlipToStringListsEachItem() {
        slip.addItem(new SalesItem("Kourtney Flourless Chocolate Cake", 6.59, 1));
        slip.addItem(new SalesItem("Grimace Shake", 4.59, 1));
        String result = slip.toString();
        assertTrue(result.contains("Kourtney Flourless Chocolate Cake"));
        assertTrue(result.contains("Grimace Shake"));
        // Two items -> two lines
        assertEquals(2, result.split("\n").length);
    }
}

