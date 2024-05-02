/* File ISController.java
 * Author: Ilya Efremenko
 * Created on: 26 Apr 2024
 * Purpose:
 * Notes:
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ISController implements ActionListener, ListSelectionListener {

    public ISView view;
    
    private ArrayList<Inventory> invList;
    private Inventory selectedInventory;
    
    private SaveFileManager saveFileManager;
    
    public ISController() {
        this.view = new ISView(this, this);
        this.invList = new ArrayList<>();
        this.saveFileManager = new SaveFileManager();
    }

    public void addInventory(Inventory inv) {
        this.invList.add(inv);
    }
    
    public void addItem(Item item) {
        // TODO
        this.selectedInventory.addItem(item);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.openMI) {
            this.saveFileManager.openSaveFile(this);
            constructTree(this.invList, view.getTreeRoot());
        }
        else if (e.getSource() == view.winLafMI) {
            view.changeUI(view.winLafMI.getText());
        }
        else if (e.getSource() == view.metalLafMI) {
            view.changeUI(view.metalLafMI.getText());
        }
        else if (e.getSource() == view.darkLafMI) {
            view.changeUI(view.darkLafMI.getText());
        }
        view.update(view.getGraphics());
    }
    
    public ArrayList<Inventory> getInvList() {
        return invList;
    }
    
    private void constructTree(ArrayList<Inventory> invList, DefaultMutableTreeNode treeRoot) {
        for (Inventory inv : invList) {
            treeRoot.add(inv);
            if (!inv.getInventories().isEmpty()) {
                constructTree(inv.getInventories(), inv);
            }
        }
        
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // this is like an action listener for the item list
        if (view.itemList.getSelectedValue() != null) {
            Item selItem = view.itemList.getSelectedValue();
            view.quantitySpinner.setValue(selItem.getFieldValue("Quantity"));
            view.priceSpinner.setValue(selItem.getFieldValue("Price"));
            view.itemNotes.setText((String)selItem.getFieldValue("Notes"));
        }
    }
}
