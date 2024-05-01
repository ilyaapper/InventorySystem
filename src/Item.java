/* File Item.java
 * Author: Ilya Efremenko
 * Created on: 01 May 2024
 * Purpose: Standard class for each item
 * Notes:
 */

public class Item {
    private String name, notes;
    private int quantity;
    private double price;

    public Item(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = Math.round(price * 100) / 100.0;
        this.notes = "Add notes here";
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        if (quantity / 100 > 1) {
            return quantity + "  " + name;
        }
        else if (quantity / 100 < 1 && quantity / 10 >= 1) {
            return quantity + "    " + name;
        }
        else {
            return quantity + "      " + name;
        }
    }
}
