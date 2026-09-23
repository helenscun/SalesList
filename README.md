# SalesList
Assignment3 for WEEK 3 GitHub Intro and JUnit Testing

A simple Java Swing desktop application for recording sales. Users enter an
item's name, cost, and quantity, add it to a running sales list, and see the
total sales update automatically.

## Features

- Add items to a sales list via a simple form (Item, Cost, Quantity)
- Running, scrollable display of every item added
- Automatic calculation of each item's subtotal and the overall total sales
- Input validation (non-empty item name, cost under $100, positive whole-number quantity)

## Project Structure

```
src/
├── main/java/saleslist/
│   ├── SalesItem.java   # Model: name, price, quantity for one item
│   ├── SalesSlip.java   # Model: the list of SalesItem objects + total calculation
│   └── Main.java        # Controller: builds the Swing GUI and wires up events
└── test/java/saleslist/test/
    └── SalesSlipTest.java  # JUnit 5 unit tests for SalesItem and SalesSlip
```

### Classes

| Class | Responsibility |
|---|---|
| `SalesItem` | Models a single sales entry: name, price, quantity. Provides getters/setters, `calculateItemTotal()`, and a `toString()` formatted for display. |
| `SalesSlip` | Holds the full list of `SalesItem` objects in an `ArrayList`. Provides `addItem()`, `computeTotalSales()`, `getItemCount()`, `clear()`, and a `toString()` that renders the whole list. |
| `Main` | The application's controller and entry point. Builds the Swing window, handles the "Add Item to the Sales List" button click, validates input, and keeps the display in sync with the `SalesSlip`. |

## Requirements

- Java 21 (JDK)
- JUnit 5 (Jupiter) — only needed to run the tests, not the application itself
- Eclipse (or any Java IDE) if you want to build/run it graphically

## Running the Application

**From an IDE (e.g. Eclipse):**
Run `Main.java` — it has a standard `public static void main(String[] args)` entry point.

**From the command line:**
```bash
cd src/main/java
javac saleslist/*.java
java saleslist.Main
```

## Running the Tests

`SalesSlipTest.java` uses JUnit 5. In Eclipse, add JUnit via
**Build Path → Add Libraries → JUnit → JUnit 5**, then right-click the test
file and choose **Run As → JUnit Test**.

## Note on `module-info.java`

If your project uses the Java Platform Module System (i.e. it has a
`module-info.java`), any JDK or third-party module you use must be declared
explicitly:

```java
module saleslist {
    requires java.desktop;              // needed for Swing/AWT (Main.java)
    requires static org.junit.jupiter.api; // needed for the test class only
}
```

For a project this size, it's simplest to skip the module system entirely —
just delete `module-info.java` if you have one, and everything falls back to
the classpath, which requires no `requires` declarations at all.

## Example

| Item | Cost | Qty |
|---|---|---|
| Labubu Matcha Dubai Chocolate | $15.99 | 1 |
| Half Pound Roast Beef Arby's | $9.79 | 1 |
| Kourtney Flourless Chocolate Cake | $6.59 | 1 |
| Grimace Shake | $4.59 | 1 |
| Spicy Bowl | $16.00 | 1 |

**Total Sales: $52.96**

[<img width="460" height="667" alt="image" src="https://github.com/user-attachments/assets/878a3d4b-49bf-4dfa-8784-bad2e98cf1e4" />](https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2spb63oGJbt52X5XJU9oGosYSdfplhCAEw9Ty1uVCD7jXGuvEBE8qhEw&s=10)

