package com.summer.section1.group7.eco_resort.Nazmun.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WasteEntry {

    private final StringProperty itemName;
    private final StringProperty quantity;
    private final StringProperty reason;

    public WasteEntry(String itemName, String quantity, String reason) {
        this.itemName = new SimpleStringProperty(itemName);
        this.quantity = new SimpleStringProperty(quantity);
        this.reason = new SimpleStringProperty(reason);
    }

    public String getItemName() { return itemName.get(); }
    public void setItemName(String v) { itemName.set(v); }
    public StringProperty itemNameProperty() { return itemName; }

    public String getQuantity() { return quantity.get(); }
    public void setQuantity(String v) { quantity.set(v); }
    public StringProperty quantityProperty() { return quantity; }

    public String getReason() { return reason.get(); }
    public void setReason(String v) { reason.set(v); }
    public StringProperty reasonProperty() { return reason; }
}