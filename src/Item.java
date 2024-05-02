/* File Item.java
 * Author: Ilya Efremenko & Daniel Seamus Boots
 * Created on: 01 May 2024
 * Purpose: Standard class for each item
 * Notes:
 */

import java.util.ArrayList;


public class Item {
    private String name;
    private ArrayList<ItemField> fields;

    public Item(String name) {
        this.name = name;                   // Item name
        this.fields = new ArrayList<>();    // Array of standard & custom fields
    }

    // Adding custom field
    public void addField(ItemField field) {
        this.fields.add(field);
    }

    // Getting field's value
    public Object getFieldValue(String fieldName) {
        for (ItemField field : this.fields) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field.getValue();
            }
        }
        return null;
    }

    // Getting name
    public String getName() {
        return name;
    }

    // Setting name
    public void setName(String name) {
        this.name = name;
    }

    // Getting fields
    public ArrayList<ItemField> getFields() {
        return fields;
    }

    // Printing
    @Override
    public String toString() {
        return this.getName();
    }
}