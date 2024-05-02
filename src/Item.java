/* File: Item.java
 * Author: Seamus Daniel Boots
 * Created On: 01 May 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

import java.util.ArrayList;

public class Item {
	String name;
	ArrayList<ItemField> fields;
	public Item(String name) {
		this.name = name;
		this.fields = new ArrayList<>();
	}
	public void addField(ItemField field) {
		this.fields.add(field);
	}
	public Object getFieldValue(String fieldName) {
		for (ItemField field : this.fields) {
			if (field.getName().equalsIgnoreCase(fieldName)) {
				return field.getValue();
			}
		}
		return null;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<ItemField> getFields() {
		return fields;
	}
	@Override
	public String toString() {
		return this.getName();
	}
}
