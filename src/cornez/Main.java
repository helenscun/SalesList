package cornez;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Main
 * Controller class for the Sales List application. Builds the Swing
 * GUI (layout, fields, button, list display), wires up the "Add Item
 * to the Sales List" button event, and delegates all data storage and
 * calculation to a single SalesSlip instance (which in turn holds
 * SalesItem objects).
 */
public class Main extends JFrame {

    private JTextField itemField;
    private JTextField costField;
    private JTextField quantityField;
    private JButton addButton;
    private JTextArea salesListArea;
    private JTextField totalSalesField;

    private final SalesSlip salesSlip;

    public Main() {
        super("Sales List");
        salesSlip = new SalesSlip();

        buildUI();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(480, 420);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Lays out every component of the window: the title bar, the
     * item/cost/quantity entry form, the add button, the scrolling
     * sales list display, and the total sales field.
     */
    private void buildUI() {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        setContentPane(root);

        // ----- Title -----
        JLabel titleLabel = new JLabel("Sales List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.PLAIN, 22));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        root.add(titleLabel, BorderLayout.NORTH);

        // ----- Center panel: form + button + list -----
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(buildFormPanel());
        center.add(Box.createVerticalStrut(10));
        center.add(buildAddButtonPanel());
        center.add(Box.createVerticalStrut(10));
        center.add(buildSalesListPanel());

        root.add(center, BorderLayout.CENTER);

        // ----- Bottom: total sales -----
        root.add(buildTotalSalesPanel(), BorderLayout.SOUTH);
    }

    private JPanel buildFormPanel() {
        JPanel form = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        itemField = new JTextField(20);
        costField = new JTextField(20);
        quantityField = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        form.add(new JLabel("Item:"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        form.add(itemField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(new JLabel("Cost: $"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        form.add(costField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        form.add(new JLabel("Quantity"), gbc);
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        form.add(quantityField, gbc);

        form.setAlignmentX(Component.LEFT_ALIGNMENT);
        return form;
    }

    private JPanel buildAddButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add Item to the Sales List");
        addButton.addActionListener(new AddItemListener());
        panel.add(addButton);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    private JPanel buildSalesListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        salesListArea = new JTextArea();
        salesListArea.setEditable(false);
        salesListArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 13));
        JScrollPane scrollPane = new JScrollPane(salesListArea);
        scrollPane.setPreferredSize(new Dimension(430, 150));
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return panel;
    }

    private JPanel buildTotalSalesPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(new JLabel("Total Sales:"));
        totalSalesField = new JTextField(10);
        totalSalesField.setEditable(false);
        totalSalesField.setText(String.format("$%.2f", 0.0));
        panel.add(totalSalesField);
        return panel;
    }

    /**
     * Handles clicks on "Add Item to the Sales List": reads and
     * validates the three input fields, builds a SalesItem, adds it
     * to the SalesSlip, then refreshes the list display and total.
     */
    private class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = itemField.getText().trim();

            if (name.isEmpty()) {
                showError("Please enter an item name.");
                return;
            }

            double price;
            try {
                price = Double.parseDouble(costField.getText().trim());
            } catch (NumberFormatException ex) {
                showError("Please enter a valid cost (e.g. 1.99).");
                return;
            }
            if (price < 0 || price >= 100) {
                showError("Cost must be between $0 and $99.99.");
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityField.getText().trim());
            } catch (NumberFormatException ex) {
                showError("Please enter a valid whole-number quantity.");
                return;
            }
            if (quantity <= 0) {
                showError("Quantity must be a positive whole number.");
                return;
            }

            salesSlip.addItem(new SalesItem(name, price, quantity));
            refreshDisplay();
            clearForm();
        }
    }

    /** Re-renders the sales list text area and the total sales field. */
    private void refreshDisplay() {
        salesListArea.setText(salesSlip.toString());
        totalSalesField.setText(String.format("$%.2f", salesSlip.computeTotalSales()));
    }

    /** Clears the entry fields and returns focus to the item field. */
    private void clearForm() {
        itemField.setText("");
        costField.setText("");
        quantityField.setText("");
        itemField.requestFocusInWindow();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Invalid Input", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}

