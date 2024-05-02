/* File: Field.java
 * Author: Seamus Daniel Boots & Ilya Efremenko
 * Created On: 26 Apr 2024
 * Copyright: Copyright 2024 Seamus Daniel Boots
 * Purpose:
 */

public class ItemField {

    private String name; // Field name
    private ItemFieldType type; // Field type
    private Object value; // Arbitrary variable

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

    // Getting name
    public String getName() {
        return name;
    }

    // Setting name
    public void setName(String name) {
        this.name = name;
    }

    // Getting type
    public ItemFieldType getType() {
        return type;
    }

    // Setting type
    public void setType(ItemFieldType type) {
        this.type = type;
    }

    // Getting value
    public Object getValue() {
        return value;
    }

    // Setting value
    public void setValue(Object value) {
        this.value = value;
    }

    // Printing
    @Override
    public String toString() {
        return String.format("%s : %s", this.name, this.value);
    }
}