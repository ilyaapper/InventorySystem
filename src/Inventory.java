/* File: Container.java
 * Author: Seamus Daniel Boots
 * Created On: 26 Apr 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class Inventory extends DefaultMutableTreeNode  {
	
	private String name;
	private ArrayList<Inventory> inventories;
	private ArrayList<Item> items;
	
	public Inventory(String name) {
		super(name);
		this.name = name;
		this.inventories = new ArrayList<>();
		this.items = new ArrayList<>();
	}
	
	public void addItem(Item item) {
		this.items.add(item);
	}
	public void addInv(Inventory inv) {
		this.inventories.add(inv);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Inventory> getInventories() {
		return inventories;
	}
	public ArrayList<Item> getItems() {
		return items;
	}
	
	@Override
	public String toString() {
		return this.name+"A";
	}
}
