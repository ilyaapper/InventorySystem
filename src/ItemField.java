/* File: Field.java
 * Author: Seamus Daniel Boots
 * Created On: 26 Apr 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

public class ItemField {
	
	private String name;
	private ItemFieldType type;
	private Object value;
	
	public ItemField(String name, ItemFieldType type) {
		this(name, type, switch (type) {
			case INT -> 0;
			case DEC -> 0.0f;
			case BOOL -> false;
			case STRING -> "";
		});
	}
	
	public ItemField(String name, ItemFieldType type, Object value) {
		this.name = name;
		this.type = type;
		this.value = value;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ItemFieldType getType() {
		return type;
	}
	
	public void setType(ItemFieldType type) {
		this.type = type;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("%s : %s", this.name, this.value);
	}
}
