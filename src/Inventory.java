/* File: Container.java
 * Author: Seamus Daniel Boots & Ilya Efremenko
 * Created On: 26 Apr 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;


public class Inventory extends DefaultMutableTreeNode  {

    private String name; // Inventory name
    private ArrayList<Inventory> inventories; // Array of sub containers
    private ArrayList<Item> items; // Array of contained items

    // Initializing container
    public Inventory(String name) {
        super(name);
        this.name = name;
        this.inventories = new ArrayList<>();
        this.items = new ArrayList<>();
    }

    // Adding item to container
    public void addItem(Item item) {
        this.items.add(item);
    }

    public void delItem(Item item) {
        this.items.remove(item);
    }

    // Adding sub container to container
    public void addInv(Inventory inv) {
        this.inventories.add(inv);
    }

    // Getting sub containers
    public ArrayList<Inventory> getInventories() {
        return inventories;
    }

    // Getting contained items
    public ArrayList<Item> getItems() {
        return items;
    }

    public String getName() {
        return this.name;
    }

    // Printing
    @Override
    public String toString() {
        return this.name;
    }
}