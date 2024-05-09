/* File ISController.java
 * Author: Ilya Efremenko & Daniel Seamus Boots
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ISController implements ActionListener, ListSelectionListener, TreeSelectionListener {

    private ISView view; // Main graphics
    private ArrayList<Inventory> invList; // Array of containers
    private SaveFileManager saveFileManager; // Managing saves... duh

    public ISController() {
        this.view = new ISView(this, this, this);
        this.invList = new ArrayList<>();
        this.saveFileManager = new SaveFileManager(this);
    }

    // Adding container
    public void addInventory(Inventory inv) {
        this.invList.add(inv);
    }

    // Reacting to menu items interactions
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getWinLafMI()) {
            view.changeUI(view.getWinLafMI().getText()); // Changing to Windows UI
        }
        else if (e.getSource() == view.getMetalLafMI()) {
            view.changeUI(view.getMetalLafMI().getText()); // Changing to Metal UI
        }
        else if (e.getSource() == view.getDarkLafMI()) {
            view.changeUI(view.getDarkLafMI().getText()); // Changing to Dark UI
        }
        else if (e.getSource() == view.getUserManualMI()) {
            new UserManualController();                  // Opening user manual
        }
        else if (e.getSource() == view.getAddMI()) {
            new ItemAddView(this);               // Opening item add dialogue
        }
        else if (e.getSource() == view.getOpenMI()) { // open a save file
            this.saveFileManager.openSaveFile();
            constructTree(this.invList, view.getTreeRoot());
            view.showTree();
        }
        else if (e.getSource() == view.getSaveAsMI()) { // save a save file
            this.saveFileManager.saveSaveFile();
        }
        else if (e.getSource() == view.getAddMI()) {
            //
        }
        else if (e.getSource() == view.getDeleteMI()) {
            view.delSelItem();
        }
        else if (e.getSource() == view.getConfirmMI()) {
            Item selItem = view.getCurrentlySelItem();
            selItem.setField("Quantity", view.getQuantity());
            selItem.setField("Price", view.getPrice());
            selItem.setField("Notes", view.getNotes());
        }
    }

    // Constructing navigating tree
    private void constructTree(ArrayList<Inventory> invList,
                               DefaultMutableTreeNode treeRoot) {
        for (Inventory inv : invList) {
            treeRoot.add(inv);
            if (!inv.getInventories().isEmpty()) {
                constructTree(inv.getInventories(), inv);
            }
        }
    }

    // Reacting to tree interactions
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        if (!view.isTreeSelectionEmpty()) {
            // disable the item fields
            view.disableItemFields();

            Inventory selInv;
            try {
                selInv = view.getCurrentlySelInv();
            } catch (ClassCastException exc) {
                // root node which isn't an inventory was clicked
                return;
            }

            // Update the selected container
            view.setSelContName(selInv.getName());
            view.clearCurrentItemList();
            for (Item item : selInv.getItems()) {
                view.addToCurrentItemList(item);
            }

            // clear the last selected item's fields
            view.clearCurrentItemFields();
        }
    }

    // Reacting to list interactions
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (view.getCurrentlySelItem() != null) {
            Item selItem = view.getCurrentlySelItem();
            view.setSelItemName(selItem.getName());
            view.enableItemFields();
            view.setQuantity((Integer)selItem.getFieldValue("Quantity"));
            view.setPrice((Float)selItem.getFieldValue("Price"));
            view.setNotes((String)selItem.getFieldValue("Notes"));
        } else {
            view.disableItemFields();
        }
    }

    public ArrayList<Inventory> getInvList() {
        return this.invList;
    }
}
