package com.summer.section1.group7.eco_resort.Nazmun.Model;

import java.io.Serializable;

public class InventoryItem implements Serializable {
    private String itemId, name;
    private double quantity, reorderLevel;

    public InventoryItem(String itemId, String name, double quantity, double reorderLevel) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.reorderLevel = reorderLevel;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(double reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}