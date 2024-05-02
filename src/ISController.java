/* File ISController.java
 * Author: Ilya Efremenko & Daniel Seamus Boots
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ISController implements ActionListener, ListSelectionListener {

    private ISView view; // Main graphics
    private ArrayList<Inventory> invList; // Array of containers
    private Inventory selectedInventory; // Selected container
    private SaveFileManager saveFileManager; // Managing saves... duh

    public ISController() {
        this.view = new ISView(this, this);
        this.invList = new ArrayList<>();
        this.saveFileManager = new SaveFileManager();
    }

    // Adding container
    public void addInventory(Inventory inv) {
        this.invList.add(inv);
    }

    // Adding item
    public void addItem(Item item) {
        // TODO
        this.selectedInventory.addItem(item);
    }

    // Reacting to menu items interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==view.getWinLafMI()) {
            view.changeUI(view.getWinLafMI().getText()); // Changing to Windows UI
        }
        else if (e.getSource()==view.getMetalLafMI()) {
            view.changeUI(view.getMetalLafMI().getText()); // Changing to Metal UI
        }
        else if (e.getSource()==view.getDarkLafMI()) {
            view.changeUI(view.getDarkLafMI().getText()); // Changing to Dark UI
        }
        else if (e.getSource()==view.getUserManualMI()) {
            view.openManual(this);                  // Opening user manual
        }
        else if (e.getSource()==view.getAddMI()) {
            new ItemAddView(this);                  // Opening item add dialogue
        }
        else if (e.getSource() == view.getOpenMI()) {
            this.saveFileManager.openSaveFile(this);
            constructTree(this.invList, view.getTreeRoot());
        }
    }

    // Constructing navigating tree
    private void constructTree(ArrayList<Inventory> invList, DefaultMutableTreeNode treeRoot) {
        for (Inventory inv : invList) {
            treeRoot.add(inv);
            if (!inv.getInventories().isEmpty()) {
                constructTree(inv.getInventories(), inv);
            }
        }
    }

    // Reacting to list interactions
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (view.getItemList().getSelectedValue() != null) {
            Item selItem = view.getItemList().getSelectedValue();
            view.getQuantitySpinner().setValue(selItem.getFieldValue("Quantity"));
            view.getPriceSpinner().setValue(selItem.getFieldValue("Price"));
            view.getItemNotes().setText((String)selItem.getFieldValue("Notes"));
        }
    }
}
