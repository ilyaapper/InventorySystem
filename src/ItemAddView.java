/* File ItemAddView.java
 * Author: Ilya Efremenko & Daniel Seamus Boots
 * Created on: 01 May 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


@SuppressWarnings({"FieldMayBeFinal", "unused"}) // Suppressing the annoying "may be final" warnings
public class ItemAddView extends JFrame {
    private ImageIcon icon;
    private JComboBox<String> selectionBox;
    private JLabel newLabel, nameLabel, qtyLabel, priceLabel;
    private JTextField nameField, qtyField, priceField;

    public ItemAddView (ActionListener listener) {
        super("Add Item");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Closing this window but not others
        this.setSize(300, 200);
        this.setMinimumSize(new Dimension(100, 50));
        this.setLayout(new GridLayout(4, 2));
        icon = new ImageIcon("ISIcon.png");
        this.setIconImage(icon.getImage());

        // Selecting type, name, quantity, and price
        this.add(newLabel = new JLabel("New:"));
        String[] creationChoices = {"Item", "Container"};
        this.add(selectionBox = new JComboBox<>(creationChoices));
        this.add(nameLabel = new JLabel("Name:"));
        this.add(nameField = new JTextField());
        this.add(qtyLabel = new JLabel("Quantity:"));
        this.add(qtyField = new JTextField());
        this.add(priceLabel = new JLabel("Price:"));
        this.add(priceField = new JTextField());
        this.setVisible(true);
    }

    // Another million of getters btw
    public ImageIcon getIcon() {
        return icon;
    }

    public JComboBox<String> getSelectionBox() {
        return selectionBox;
    }

    public JLabel getNewLabel() {
        return newLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getQtyLabel() {
        return qtyLabel;
    }

    public JLabel getPriceLabel() {
        return priceLabel;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public JTextField getQtyField() {
        return qtyField;
    }

    public JTextField getPriceField() {
        return priceField;
    }
}
